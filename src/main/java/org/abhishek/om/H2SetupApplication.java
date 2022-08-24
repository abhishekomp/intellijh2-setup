package org.abhishek.om;

import org.abhishek.om.model.Person;
import org.abhishek.om.model.PersonLocalDate;
import org.abhishek.om.model.PersonWithDateOfBirth;
import org.abhishek.om.repository.PersonLocalDateRepository;
import org.abhishek.om.repository.PersonRepository;
import org.abhishek.om.repository.PersonWithDateOfBirthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class H2SetupApplication {

	@Autowired
	PersonRepository personRepository;
	@Autowired
	PersonWithDateOfBirthRepository personWithDateOfBirthRepository;
	@Autowired
	PersonLocalDateRepository personLocalDateRepository;

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

		List<PersonWithDateOfBirth> personWithDOBList = Stream.of(
				new PersonWithDateOfBirth("Abhikriti", "Choudhary", "2012-01-01"),
				new PersonWithDateOfBirth("Om", "Prakash", "1990-01-01")
		).collect(toList());
		personWithDateOfBirthRepository.saveAll(personWithDOBList);

		List<PersonLocalDate> personLocalDateList = Stream.of(
				new PersonLocalDate("Abhikriti", "Choudhary", LocalDate.of(2012, 01, 01)),
				new PersonLocalDate("Om", "Prakash", LocalDate.of(2014, 01, 01))
		).collect(toList());
		personLocalDateRepository.saveAll(personLocalDateList);
	}

}
