package com.resell.person.resouces;

import com.resell.person.dto.CustomerDTO;
import com.resell.person.dto.CustomerListRequestDTO;
import com.resell.person.entities.Customer;
import com.resell.person.entities.Person;
import com.resell.person.services.CustomerServiceImpl;
import com.resell.person.services.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> customerList(@RequestBody CustomerListRequestDTO dto) throws Exception {
        return ResponseEntity.ok(this.customerServiceImpl.myCustomers(dto.getPerson_id()));
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> customerInsert(@RequestBody CustomerDTO dto) throws Exception {
        return ResponseEntity.ok(this.customerServiceImpl.customerInsert(dto));
    }

}
