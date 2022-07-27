package com.resell.person.repositories;


import com.resell.person.entities.Person;
import com.resell.person.entities.PersonCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonCreditRepository  extends JpaRepository<PersonCredit, Long> {
    List<PersonCredit> findAllByPerson(Person person);
}
