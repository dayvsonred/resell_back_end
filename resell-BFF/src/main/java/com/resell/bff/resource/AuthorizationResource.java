package com.resell.bff.resource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "authorization")
@RestController
@RequestMapping("/resell-bff/authorization")
public class AuthorizationResource {

//    @Autowired
//    AuthorizationService authorizationService;
//
//    @Operation(summary = "Get Token JWT")
//    @GetMapping(value = "/jwt", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<JwtDTO> accessValid(){
//        return ResponseEntity.ok(authorizationService.accessValid());
//    }
}
