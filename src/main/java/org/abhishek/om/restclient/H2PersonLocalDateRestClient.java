package org.abhishek.om.restclient;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.abhishek.om.model.PersonLocalDate;
import org.abhishek.om.util.UnirestJSONInterceptor;

import java.time.LocalDate;

/**
 * Created by sca820 on 16 aug., 2022
 */
public class H2PersonLocalDateRestClient {
    private final String host = "localhost";
    private final String port = "8080";

    public static void main(String[] args) {
        H2PersonLocalDateRestClient client = new H2PersonLocalDateRestClient();
        PersonLocalDate personLocalDate =
                new PersonLocalDate("Test New", "Test New", LocalDate.of(2016, 04, 04));
        //testing addPersonAndGetResultAsObject
//        PersonLocalDate addedPerson = client.addPersonAndGetResultAsObject(personLocalDate);
//        System.out.println("Id of added person = " + addedPerson.getId());


        // testing addPersonAndGetResultAsJsonNode
        JsonNode jsonNode = client.addPersonAndGetResultAsJsonNode(personLocalDate);
        JSONObject jsonObject = jsonNode.getObject();
        String id = jsonObject.getString("id");
        System.out.println("id = " + id);
    }

    public PersonLocalDate addPersonAndGetResultAsObject(PersonLocalDate personLocalDate) {
        String url = "http://" + host + ":" + port + "/person-service-localDate/addPerson";

        Unirest.config().interceptor(new UnirestJSONInterceptor());
        HttpResponse<PersonLocalDate> response = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(personLocalDate)
                .asObject(PersonLocalDate.class);
        Unirest.config().reset();
        PersonLocalDate addedPerson = response.getBody();
        return addedPerson;
    }

    public JsonNode addPersonAndGetResultAsJsonNode(PersonLocalDate personLocalDate) {
        String url = "http://" + host + ":" + port + "/person-service-localDate/addPerson";

        Unirest.config().interceptor(new UnirestJSONInterceptor());
        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(personLocalDate)
                .asJson();

        Unirest.config().reset();
        JsonNode jsonNode = response.getBody();
        return jsonNode;
    }

}
