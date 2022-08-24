package org.abhishek.om.repository;

import org.abhishek.om.model.PersonWithDateOfBirth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sca820 on 16 aug., 2022
 */
@Repository
public interface PersonWithDateOfBirthRepository extends JpaRepository<PersonWithDateOfBirth, Long> {
}
