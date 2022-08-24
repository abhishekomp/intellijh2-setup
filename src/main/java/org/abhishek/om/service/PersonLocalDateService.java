package org.abhishek.om.service;

import org.abhishek.om.model.PersonLocalDate;
import org.abhishek.om.repository.PersonLocalDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sca820 on 16 aug., 2022
 */
@Service
public class PersonLocalDateService {
    @Autowired
    PersonLocalDateRepository personLocalDateRepository;

    public PersonLocalDate addPerson(PersonLocalDate person) {
        final PersonLocalDate addedPerson = personLocalDateRepository.save(person);
        return addedPerson;
    }

    public List<PersonLocalDate> getAllPersons() {
        final List<PersonLocalDate> personList = personLocalDateRepository.findAll();
        return personList;
    }
}
