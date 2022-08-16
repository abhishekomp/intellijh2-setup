package org.abhishek.om.service;

import org.abhishek.om.model.Person;
import org.abhishek.om.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    private Supplier<? extends Person> dummyPerson;

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    public void removePerson(String id) {
        personRepository.deleteById(Long.parseLong(id));
    }

    public Person getPerson(Long id) {
        //final long personId = Long.parseLong("id");
        final Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElseGet(dummyPerson);
    }

    public Person updatePerson(Long id, Person person) {
        Person toUpdate = new Person(id, person.getFirstName(), person.getLastName());
        Person updatedPerson = personRepository.save(toUpdate);
        return updatedPerson;
    }
}
