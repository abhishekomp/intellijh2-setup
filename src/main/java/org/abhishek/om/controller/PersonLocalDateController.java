package org.abhishek.om.controller;

import org.abhishek.om.model.PersonLocalDate;
import org.abhishek.om.service.PersonLocalDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sca820 on 16 aug., 2022
 */
@RestController
@RequestMapping("/person-service-localDate")
public class PersonLocalDateController {
    @Autowired
    PersonLocalDateService personLocalDateService;

    @PostMapping("addPerson")
    public ResponseEntity<PersonLocalDate> addPerson(@RequestBody PersonLocalDate person) {
        PersonLocalDate addedPerson = personLocalDateService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPerson);
    }

    @GetMapping("getAllPersons")
    public ResponseEntity<List<PersonLocalDate>> getAllPersons() {
        List<PersonLocalDate> personList = personLocalDateService.getAllPersons();
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }
}
