package com.resell.oauth.resources;

import com.resell.oauth.dto.UserTokenDTO;
import com.resell.oauth.entities.User;
import com.resell.oauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        try {
            User user = service.findByEmail(email);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/teste")
    public ResponseEntity findByEmail() {
        try {
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping(value = "/token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserTokenDTO> findUserByToken() {
        String UserEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            User user = service.findByEmail(UserEmail);
            return ResponseEntity.ok().body(UserTokenDTO.builder().email(UserEmail).name(user.getName()).id(user.getId()).build());
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
