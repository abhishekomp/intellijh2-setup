package org.abhishek.om.restclient;

import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import org.abhishek.om.model.PersonLocalDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sca820 on 24 aug., 2022
 */
class H2PersonLocalDateRestClientTest {

    H2PersonLocalDateRestClient h2PersonLocalDateRestClient = new H2PersonLocalDateRestClient();

    @Test
    void addPersonAndGetResultAsJsonNode() {
        PersonLocalDate personLocalDate =
                new PersonLocalDate("Test New", "Test New", LocalDate.of(2020, 04, 04));
        JsonNode jsonNode = h2PersonLocalDateRestClient.addPersonAndGetResultAsJsonNode(personLocalDate);
        JSONObject jsonObject = jsonNode.getObject();
        String id = jsonObject.getString("id");
        System.out.println("id = " + id);
        assertThat(jsonObject.getString("firstName")).isEqualTo("Test New");
    }
}