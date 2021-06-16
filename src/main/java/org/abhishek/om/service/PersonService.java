package org.abhishek.om.service;

import org.abhishek.om.model.Person;
import org.abhishek.om.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

}
