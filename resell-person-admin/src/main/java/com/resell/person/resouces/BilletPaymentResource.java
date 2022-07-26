package com.resell.person.resouces;


import com.resell.person.dto.SalesDTO;
import com.resell.person.entities.Sales;
import com.resell.person.exception.CustomerException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/bilet")
public class BiletPaymentResource {

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sales> salesInsert(@RequestBody SalesDTO dto) throws CustomerException {
        return ResponseEntity.ok();
    }
}
