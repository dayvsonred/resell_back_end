package com.resell.person.resouces;


import com.resell.person.dto.BilletPaymentDTO;
import com.resell.person.exception.CustomerException;
import com.resell.person.exception.PersonException;
import com.resell.person.services.BilletPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/billet")
public class BilletPaymentResource {

    @Autowired
    private BilletPaymentService billetPaymentService;

    @PostMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BilletPaymentDTO> salesInsert(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody BilletPaymentDTO dto) throws CustomerException {
        return ResponseEntity.ok(billetPaymentService.payment(token, dto));
    }

    @PostMapping(value = "/confirm", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BilletPaymentDTO> salesConfirm(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody BilletPaymentDTO dto) throws CustomerException {
        return ResponseEntity.ok(billetPaymentService.salesConfirm(token, dto));
    }
}
