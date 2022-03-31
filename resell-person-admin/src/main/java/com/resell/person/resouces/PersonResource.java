package com.resell.person.resouces;

import com.resell.person.dto.CustomerListRequestDTO;
import com.resell.person.dto.PersonDTO;
import com.resell.person.entities.Person;
import com.resell.person.services.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonResource {

    @Autowired
    private PersonServiceImpl personServiceImpl;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Person> creat(@RequestBody PersonDTO dto) throws Exception {
        return ResponseEntity.ok(this.personServiceImpl.createPerson(dto));
    }
}
