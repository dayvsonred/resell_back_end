package com.resell.person.resouces;


import com.resell.person.dto.CustomerDTO;
import com.resell.person.dto.SalesDTO;
import com.resell.person.entities.Customer;
import com.resell.person.entities.Sales;
import com.resell.person.exception.CustomerException;
import com.resell.person.services.PersonServiceImpl;
import com.resell.person.services.SalesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sales")
public class SalesResource {

    @Autowired
    private SalesServiceImpl salesServiceImpl;

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Sales> salesInsert(@RequestBody SalesDTO dto) throws CustomerException {
        return ResponseEntity.ok(this.salesServiceImpl.insert(dto));
    }
}
