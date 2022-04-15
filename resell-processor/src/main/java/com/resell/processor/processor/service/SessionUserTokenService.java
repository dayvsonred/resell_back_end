package com.resell.processor.processor.service;

import com.resell.processor.processor.dto.UserSessionRedisDTO;
import com.resell.processor.processor.entities.session.UserSessionRedis;
import com.resell.processor.processor.repositories.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionUserTokenService {

    @Autowired
    private final UserSessionRepository userSessionRepository;



    public void validTokenSession(UserSessionRedisDTO userSessionRedisDTO){

        this.saveTokenUserRedis(userSessionRedisDTO);
    }


    public void saveTokenUserRedis(UserSessionRedisDTO userSessionRedisDTO){

        System.out.println("save token in REDIS");

        UserSessionRedis userSessionRedis =  this.userSessionRepository.save(UserSessionRedis.builder()
                        //.id(userSessionRedisDTO.getUserID())
                        .token(userSessionRedisDTO.getToken())
                        .cpf(userSessionRedisDTO.getCpf())
                        .email(userSessionRedisDTO.getEmail())
                        .userID(userSessionRedisDTO.getUserID())
                        .session(userSessionRedisDTO.getSession())
                .build());

        System.out.println("Saved *****************************");
    }

    public UserSessionRedisDTO find(String idUser) throws Exception {

        System.out.println("Find In redis ");

        UserSessionRedisDTO userSessionRedisDTO = this.userSessionRepository.findById(idUser)
                .map(obj -> new UserSessionRedisDTO(obj))
                .orElseThrow(() -> new Exception("UserSessionRedis_NOT_FOUND"));

        System.out.println("FINIS GET REDIS");
        return userSessionRedisDTO;
    }


}
