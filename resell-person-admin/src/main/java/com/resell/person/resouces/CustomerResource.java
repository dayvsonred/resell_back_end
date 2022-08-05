package com.resell.person.resouces;

import com.resell.person.dto.CustomerDTO;
import com.resell.person.dto.CustomerListRequestDTO;
import com.resell.person.entities.Customer;
import com.resell.person.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> customerList(@RequestBody CustomerListRequestDTO dto) throws Exception {
        return ResponseEntity.ok(this.customerServiceImpl.myCustomers(dto.getPerson_id()));
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> customerInsert(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody CustomerDTO dto) throws Exception {
        return ResponseEntity.ok(this.customerServiceImpl.customerInsert(token, dto));
    }

    @DeleteMapping(value = "/deleting/{customerId}",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> customerDell(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("customerId") Long customerId) throws Exception {
            this.customerServiceImpl.customerDell(token,customerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping( produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> customerEdit(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody CustomerDTO dto) throws Exception {
        return ResponseEntity.ok(this.customerServiceImpl.customerEdit(token, dto));
    }

}
