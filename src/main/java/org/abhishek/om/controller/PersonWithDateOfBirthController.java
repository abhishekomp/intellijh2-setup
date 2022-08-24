package org.abhishek.om.controller;

import org.abhishek.om.model.PersonWithDateOfBirth;
import org.abhishek.om.service.PersonWithDateOfBirthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sca820 on 16 aug., 2022
 */
@RestController
@RequestMapping("/person-with-dob-service")
public class PersonWithDateOfBirthController {
    @Autowired
    PersonWithDateOfBirthService personWithDateOfBirthService;

    @PostMapping("addPerson")
    public ResponseEntity<PersonWithDateOfBirth> addPerson(@RequestBody PersonWithDateOfBirth person) {
        PersonWithDateOfBirth addedPerson = personWithDateOfBirthService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPerson);
    }

    @GetMapping("getAllPersons")
    public ResponseEntity<List<PersonWithDateOfBirth>> getAllPersons() {
        List<PersonWithDateOfBirth> personList = personWithDateOfBirthService.getAllPersons();
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }
}
