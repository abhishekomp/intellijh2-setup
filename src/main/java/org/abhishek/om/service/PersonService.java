package org.abhishek.om.service;

import org.abhishek.om.model.Person;
import org.abhishek.om.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    public void removePerson(String id) {
        personRepository.deleteById(Long.parseLong(id));
    }
}
