package org.abhishek.om.restclient;

import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import org.abhishek.om.model.Person;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sca820 on 11 aug., 2022
 */
class H2RestClientTest {

    H2RestClient h2RestClient = new H2RestClient();

    @Test
    void getCountOfPersons() {
        int countOfPersons = h2RestClient.getCountOfPersons();
        System.out.println("countOfPersons = " + countOfPersons);
        assertThat(countOfPersons).isEqualTo(2);
    }

    @Test
    @Disabled
    void addOnePersonGetResponseAsObject() {
        Person person = new Person("Kirti", "Shree");
        Person createdPerson = h2RestClient.addOnePersonGetResponseAsObject(person);
        final long id = createdPerson.getId();
        System.out.println("Created Person's id = " + id);
    }

    @Test
    void addOnePersonGetResponseAsJSON() {
        Person person = new Person("Abhikriti", "Babuuuuu");
        JsonNode jsonNode = h2RestClient.addOnePersonGetResponseAsJSON(person);
        JSONObject jsonObject = jsonNode.getObject();
        assertThat(jsonObject.getString("firstName")).isEqualTo("Abhikriti");
    }

    @Test
    void getPersonAsPersonClassInstance() {
        final Person person = h2RestClient.getPersonAsPersonClassInstance("3");
        System.out.println(person);
        assertThat(person.getFirstName()).isEqualTo("Abhikriti");
    }

    @Test
    void getPersonAsJsonNode() {
        JsonNode jsonNode = h2RestClient.getPersonAsJsonNode("2");
        JSONObject jsonObject = jsonNode.getObject();
        String firstName = jsonObject.getString("firstName");
        System.out.println(firstName);
        assertThat(firstName).isEqualTo("Om");
    }

    @Test
    void updateSinglePerson() {
        String idToUpdate = "2";
        Person person = new Person("Rita", "Rani");
        JsonNode jsonNode = h2RestClient.updateSinglePerson(idToUpdate, person);
        JSONObject jsonObject = jsonNode.getObject();
        System.out.println(jsonObject.getString("lastName"));
        assertThat(jsonObject.getString("lastName")).isEqualTo("Rani");
    }
}