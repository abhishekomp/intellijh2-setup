package org.abhishek.om.restclient;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.abhishek.om.model.Person;
import org.json.JSONArray;


/**
 * Created by sca820 on 11 aug., 2022
 */
public class H2RestClient {

    private final String host = "localhost";
    private final String port = "8080";

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

        HttpResponse<Person> response = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(person)
                .asObject(Person.class);
        //System.out.println("Id of created person: " + response.getBody().getId());
        Person body = response.getBody();
        return body;
    }

    public String addOnePersonGetResponseAsJSON(Person person) {

        String url = "http://" + host + ":" + port + "/person-service/addPerson";

        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(person)
                .asJson();

        JsonNode jsonNode = response.getBody();
        JSONObject jsonObject = jsonNode.getObject();
        final String id = jsonObject.getString("id");
        return id;
    }

    public long addPersonAndGetId(Person person) {
        final Person onePerson = addOnePersonGetResponseAsObject(person);
        return onePerson.getId();
    }
}
