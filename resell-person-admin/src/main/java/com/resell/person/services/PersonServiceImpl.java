package com.resell.person.services;

import com.resell.person.dto.CustomerListRequestDTO;
import com.resell.person.dto.PersonDTO;
import com.resell.person.entities.Person;
import com.resell.person.exception.PersonException;
import com.resell.person.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person create(Person object) {
        return personRepository.save(object);
    }

    @Override
    public List<Person> read() {
        return personRepository.findAll();
    }

    @Override
    public Person update(Person object) {
        return personRepository.save(object);
    }

    @Override
    public Boolean delete(Long id) {

        personRepository.deleteById(id);

        return true;
    }

    public Person createPerson(PersonDTO dto){
        Person person = Person.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
        return this.create(person);
    }

    public Person getPerson(Long id) throws PersonException{
        return personRepository.findById(id).orElseThrow(() -> new PersonException("Person_NOT_FOUND"));
    }


}
