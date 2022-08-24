package org.abhishek.om.service;

import org.abhishek.om.model.PersonWithDateOfBirth;
import org.abhishek.om.repository.PersonWithDateOfBirthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sca820 on 16 aug., 2022
 */
@Service
public class PersonWithDateOfBirthService {
    @Autowired
    PersonWithDateOfBirthRepository personWithDateOfBirthRepository;

    public PersonWithDateOfBirth addPerson(PersonWithDateOfBirth person) {
        final PersonWithDateOfBirth addedPerson = personWithDateOfBirthRepository.save(person);
        return addedPerson;
    }

    public List<PersonWithDateOfBirth> getAllPersons() {
        final List<PersonWithDateOfBirth> personList = personWithDateOfBirthRepository.findAll();
        return personList;
    }
}
