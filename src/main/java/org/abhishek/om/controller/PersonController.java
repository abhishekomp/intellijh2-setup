package org.abhishek.om.controller;

import org.abhishek.om.model.Person;
import org.abhishek.om.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/getAllPersons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/getPerson/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") Long id) {
        final Person person = personService.getPerson(id);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @PutMapping("/updatePerson/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
        final Person updatedPerson = personService.updatePerson(id, person);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPerson);
    }

    @DeleteMapping("/removePerson/{id}")
    public void removePerson(@PathVariable String id) {
        personService.removePerson(id);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s", name);
    }
}
