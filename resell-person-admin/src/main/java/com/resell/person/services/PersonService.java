package com.resell.person.services;

import com.resell.person.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

    Person create(Person person);

    List<Person> read();

    Person update(Person mobileLogs);

    Boolean delete(Long id);
}
