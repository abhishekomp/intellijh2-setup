package org.abhishek.om.jenkins;

import kong.unirest.HttpResponse;
import kong.unirest.HttpStatus;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.abhishek.om.util.Utils;

import java.util.Map;

/**
 * Created by sca820 on 03 sep., 2022
 */
public class Jenkins extends NonSslVerifyingClient{
    private final String username;
    private final String password;
    private String jenkinsHost = "localhost:8080";

    private boolean isInitialized = false;
    private String crumbRequestField = null;
    private String crumb = null;

    public Jenkins(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Jenkins(String username, String password, String jenkinsHost) {
        this.username = username;
        this.password = password;
        this.jenkinsHost = jenkinsHost;
    }

    public static void main(String[] args) {
        Jenkins j = new Jenkins("abhisk", "abhisk");
        System.setProperty("https.nonProxyHosts", j.jenkinsHost);
        System.setProperty("http.nonProxyHosts", j.jenkinsHost);
        j.init();
        System.out.println("crumb=" + j.crumb);
    }

    private void init() {

        if (!isInitialized) {
            HttpResponse<JsonNode> response = Unirest.get("http://" + jenkinsHost + "/crumbIssuer/api/json")
                    .basicAuth(username, password)
                    .asJson();
            if (response.getStatus() != HttpStatus.OK) {
                String errorMessage = response.getStatus() + " - " + response.getStatusText();
                throw new RuntimeException(errorMessage);
            }

            JsonNode body = response.getBody();
            JSONObject crumbObj = body.getObject();
            crumbRequestField = crumbObj.getString("crumbRequestField");
            this.crumb = crumbObj.getString("crumb");
        }
        isInitialized = true;
    }

    public String handshakeWithJenkins() {
        HttpResponse<JsonNode> response = Unirest.get("http://" + jenkinsHost + "/user/" + username + "/api/json")
                .basicAuth(username, password)
                .asJson();
        if (response.getStatus() != HttpStatus.OK) {
            String errorMessage = response.getStatus() + " - " + response.getStatusText();
            throw new RuntimeException(errorMessage);
        }
        JsonNode body = response.getBody();
        JSONObject userObj = body.getObject();
        System.out.println("Jenkins connection success for user " + username + " = " + userObj.getString("fullName"));
        return "Pass";
    }

    /**
     * Start job given name and argument map.
     *
     * @param jobName   the job name
     * @param arguments the arguments
     * @return the build number
     */
    public int startJob(String jobName, Map<String, Object> arguments) {
        init();

        HttpResponse<JsonNode> response = Unirest
                .post("http://" + jenkinsHost + jobName + "/buildWithParameters")
                .basicAuth(username, password)
                .header(crumbRequestField, crumb)
                .fields(arguments)
                .asJson();

        if (response.getStatus() != HttpStatus.CREATED) {
            String errorMessage = response.getStatus() + " - " + response.getStatusText();
            throw new RuntimeException(errorMessage);
        }

        String locationUrl = response.getHeaders().getFirst("Location");
        return waitForBuildNumber(locationUrl, 5);
    }

    private int waitForBuildNumber(String locationUrl, int minutes) {
        long maxTime = System.currentTimeMillis() + ((long) minutes * 60 * 1000);

        HttpResponse<JsonNode> response;
        JSONObject object;
        int buildNumber = 0;

        while (buildNumber == 0) {
            response = Unirest.get(locationUrl + "api/json")
                    .basicAuth(username, password)
                    .header(crumbRequestField, crumb)
                    .asJson();

            if (response.getStatus() == HttpStatus.OK) {
                object = response.getBody().getObject();
                if (object.has("executable")) {
                    buildNumber = object.getJSONObject("executable").getInt("number");
                }
            }
            if (buildNumber == 0) Utils.sleep(1);

            if (System.currentTimeMillis() > maxTime) {
                throw new JenkinsTimeoutException("Waiting for job number timeout");
            }
        }
        return buildNumber;
    }

    /**
     * Wait for a build to finish for a predetermined amount of time.
     *
     * @param jobName     the job to wait for
     * @param buildNumber the build of the job to wait for
     */
    public void waitFor(String jobName, int buildNumber) {
        waitFor(jobName, buildNumber, 10);
    }

    /**
     * Wait for a build to finish for a given amount of time.
     *
     * @param jobName     the job to wait for
     * @param buildNumber the build of the job to wait for
     * @param timeout     in minutes
     */
    public void waitFor(String jobName, int buildNumber, int timeout) {
        long maxTime = System.currentTimeMillis() + ((long) timeout * 60 * 1000);

        boolean isBuilding = true;
        while (isBuilding) {
            System.out.print(".");
            HttpResponse<JsonNode> job = getJsonNodeHttpResponse(jobName, buildNumber);

            if (job.getStatus() == HttpStatus.OK) {
                JSONObject jobDetails = job.getBody().getObject();
                isBuilding = isBuilding(jobDetails);
                if (isBuilding) {
                    Utils.sleep(1);
                }
            } else {
                Utils.sleep(1);
            }

            if (System.currentTimeMillis() > maxTime) {
                throw new JenkinsTimeoutException("Waiting for " + jobName + " " + buildNumber + " timeout");
            }
        }
        System.out.println();
    }

    private HttpResponse<JsonNode> getJsonNodeHttpResponse(String jobName, int buildNumber) {
        init();
        return Unirest.get("http://" + jenkinsHost + jobName + "/" + buildNumber + "/api/json")
                .basicAuth(username, password)
                .header(crumbRequestField, crumb)
                .asJson();
    }

    boolean isBuilding(JSONObject job) {
        return job.getBoolean("building");
    }

    public boolean isJobSuccessful(String jobName, int buildNumber) {
        HttpResponse<JsonNode> job = getJsonNodeHttpResponse(jobName, buildNumber);

        if (job.getStatus() == HttpStatus.OK) {
            JSONObject jobDetails = job.getBody().getObject();
            return isJobSuccessful(jobDetails);
        }
        return false;
    }

    boolean isJobSuccessful(JSONObject job) {
        String result = job.getString("result");

        return "SUCCESS".equals(result);
    }
}
