package org.abhishek.om.jenkins;

import java.util.Map;

/**
 * Created by sca820 on 03 sep., 2022
 */
public class JenkinsInvoker {
    public static void main(String[] args) {
        String username = "abhisk";
        String password = "abhisk";
        Jenkins jenkins = new Jenkins(username, password);
        String jobName = "/job/TestFreestyle";

        //add("cmdlineparams", "-Dtest", "optionaldemoone.DiscountServiceTest")
        Map<String, Object> parameters = new Parameters()
                .add("cmdlineparams", "-Dgroups", "Rel1,Rel4")
                .build();
//        Map<String, Object> parameters = new Parameters()
//                .add("inputValue", "Hello Abhishek")
//                .build();
//        Map<String, Object> parameters = new Parameters()
//                .add("inputValue", "-Dgroups", "Rel1")
//                .add("cmdlineparams", "-DinParameter", "Abhikriti")
//                .build();
        System.out.println("parameters = " + parameters);
        int buildNumber = jenkins.startJob(jobName, parameters);
        System.out.println("buildNumber = " + buildNumber);
        String consoleUrl = "http://localhost:8080" + jobName + "/" + buildNumber + "/console";
        System.out.println(">>>>>>>>>>>>consoleUrl = " + consoleUrl);
        jenkins.waitFor(jobName, buildNumber);
        boolean actual = jenkins.isJobSuccessful(jobName, buildNumber);
        System.out.println(">>>>>>>>>>>>actual = " + actual);
    }

    public void invokeJenkinsJob(String userName, String password, String jenkinsHost,
                                 String jobName, Map<String, Object> parametersMap) {
        Jenkins jenkins = new Jenkins(userName, password, jenkinsHost);
        int buildNumber = jenkins.startJob(jobName, parametersMap);
        System.out.println("buildNumber = " + buildNumber);
        String consoleUrl = "http://localhost:8080" + jobName + "/" + buildNumber + "/console";
        System.out.println(">>>>>>>>>>>>consoleUrl = " + consoleUrl);
//        jenkins.waitFor(jobName, buildNumber);
//        boolean actual = jenkins.isJobSuccessful(jobName, buildNumber);
//        System.out.println(">>>>>>>>>>>>actual = " + actual);
    }
}
