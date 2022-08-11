package org.abhishek.om.restclient;

import org.abhishek.om.model.Person;
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
        assertThat(countOfPersons).isEqualTo(0);
    }

    @Test
    void addOnePersonGetResponseAsObject() {
        Person person = new Person("Kirti", "Shree");
        Person asObject = h2RestClient.addOnePersonGetResponseAsObject(person);
        final long id = asObject.getId();
        System.out.println("id = " + id);
    }

    @Test
    void addOnePersonGetResponseAsJSON() {
        Person person = new Person("Abhikriti", "Choudhary");
        final String response = h2RestClient.addOnePersonGetResponseAsJSON(person);
        System.out.println("response = " + response);

    }
}