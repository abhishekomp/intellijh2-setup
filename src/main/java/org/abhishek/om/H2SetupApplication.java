package org.abhishek.om;

import org.abhishek.om.model.Person;
import org.abhishek.om.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class H2SetupApplication {

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(H2SetupApplication.class, args);
	}

	@PostConstruct
	public void initializeDB() {
		final List<Person> personList = Stream.of(
				new Person("Abhikriti", "Choudhary"),
				new Person("Om", "Prakash")
		).collect(toList());
		personRepository.saveAll(personList);
	}

}
