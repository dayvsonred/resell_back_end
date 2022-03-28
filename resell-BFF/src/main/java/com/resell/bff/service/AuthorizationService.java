package com.resell.bff.service;

import com.resell.bff.dto.JwtDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {


    public JwtDTO accessValid(){
        return JwtDTO.builder()
                .jwt("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
                .access(true)
                .build();
    }

}
