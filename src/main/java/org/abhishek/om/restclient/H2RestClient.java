package org.abhishek.om.restclient;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.abhishek.om.model.Person;
import org.abhishek.om.util.UnirestJSONInterceptor;
import org.json.JSONArray;

/**
 * Created by sca820 on 11 aug., 2022
 */
public class H2RestClient {

    private final String host = "localhost";
    private final String port = "8080";

    public static void main(String[] args) {
        H2RestClient client = new H2RestClient();
        //client.getPerson("1");
        //client.getPersonAsJsonNode("2");
        client.removeSinglePerson("5");
    }

    public JsonNode updateSinglePerson(String id, Person person) {
        String url = "http://" + host + ":" + port + "/person-service/updatePerson/{id}";
        final HttpResponse<JsonNode> response = Unirest.put(url)
                .header("content-type", "application/json")
                .header("accept", "application/json")
                .routeParam("id", id)
                .body(person)
                .asJson();
        JsonNode jsonNode = response.getBody();
        return jsonNode;
    }

    public Person getPersonAsPersonClassInstance(String id) {
        String url = "http://" + host + ":" + port + "/person-service/getPerson/{id}";
        final HttpResponse<Person> response = Unirest.get(url)
                .routeParam("id", id)
                .asObject(Person.class);
        final int responseStatus = response.getStatus();
        final Person responseBody = response.getBody();
        return responseBody;
    }

    public JsonNode getPersonAsJsonNode(String id) {
        String url = "http://" + host + ":" + port + "/person-service/getPerson/{id}";
        final HttpResponse<JsonNode> response = Unirest.get(url)
                .routeParam("id", id)
                .asJson();
        final int responseStatus = response.getStatus();
        //System.out.println("responseStatus = " + responseStatus);
        JsonNode responseBody = null;
        if(responseStatus == 200) {
            responseBody = response.getBody();
        }
        return responseBody;
    }

    public void getPerson(String id) {
        //Todo: This method errors out if there are no person record in the table. How to handle this?
        String url = "http://" + host + ":" + port + "/person-service/getPerson/{id}";

        final HttpResponse<JsonNode> response = Unirest.get(url)
                .routeParam("id", id)
                .asJson();

        JsonNode jsonNode = response.getBody();
        JSONObject jsonObject = jsonNode.getObject();
        String firstName = jsonObject.getString("firstName");
        System.out.println("firstName = " + firstName);
    }

    public int getCountOfPersons() {
        String url = "http://" + host + ":" + port + "/person-service/getAllPersons";
        HttpResponse<String> response = Unirest.get(url)
                .asString();

        String body = response.getBody();
        JSONArray objects = new JSONArray(body);
        int count = 0;
        for(int i = 0; i < objects.length(); i++) {
            count++;
        }
        return count;
    }

    public Person addOnePersonGetResponseAsObject(Person person) {
        String url = "http://" + host + ":" + port + "/person-service/addPerson";

        Unirest.config().interceptor(new UnirestJSONInterceptor());
        HttpResponse<Person> response = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(person)
                .asObject(Person.class);
        Unirest.config().reset();
        //System.out.println("Id of created person: " + response.getBody().getId());
        Person body = response.getBody();
        return body;
    }

    public void removeSinglePerson(String id) {
        String url = "http://" + host + ":" + port + "/person-service/removePerson/{id}";
        HttpResponse<JsonNode> response = Unirest.delete(url)
                .routeParam("id", id)
                .header("accept", "application/json")
                .asJson();
        int responseStatus = response.getStatus();
        JsonNode jsonNode = response.getBody();
        System.out.println("responseStatus = " + responseStatus);
        System.out.println("jsonNode = " + jsonNode.toPrettyString());
    }
    public JsonNode addOnePersonGetResponseAsJSON(Person person) {

        String url = "http://" + host + ":" + port + "/person-service/addPerson";
        Unirest.config().interceptor(new UnirestJSONInterceptor());
        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(person)
                .asJson();
        Unirest.config().reset();
        System.out.println("Status of add Person method: " + response.getStatus());
        JsonNode jsonNode = response.getBody();
//        JSONObject jsonObject = jsonNode.getObject();
//        final String id = jsonObject.getString("id");
        return jsonNode;
    }

    public long addPersonAndGetId(Person person) {
        final Person onePerson = addOnePersonGetResponseAsObject(person);
        return onePerson.getId();
    }
}
