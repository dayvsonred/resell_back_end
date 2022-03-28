package com.resell.bff.resource;


import com.resell.bff.dto.JwtDTO;
import com.resell.bff.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resell-bff/authorization")
public class AuthorizationResource {

    @Autowired
    AuthorizationService authorizationService;

    ///@ApiOperation("${personal.loan.v1.eligibility}")
    @GetMapping(value = "/jwt", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtDTO> accessValid(){
        return ResponseEntity.ok(authorizationService.accessValid());
    }
}
