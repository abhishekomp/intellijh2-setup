package org.abhishek.om.controller;

import org.abhishek.om.model.Person;
import org.abhishek.om.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person-service")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/addPerson")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person addPerson = personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(addPerson);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s", name);
    }

    @GetMapping("/getAllPersons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    public void removePerson(@RequestParam String id) {
        personService.removePerson(id);
    }

}
