package com.resell.processor.processor.service;

import com.resell.processor.processor.dto.oauth.UserSessionRedisDTO;
import com.resell.processor.processor.dto.oauth.UserTokenDTO;
import com.resell.processor.processor.entities.session.UserSessionRedis;
import com.resell.processor.processor.repositories.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionUserTokenService {


    private final UserSessionRepository userSessionRepository;
    private final OauthService oauthService;


    public void validTokenSession(UserSessionRedisDTO userSessionRedisDTO) throws Exception {
        try {
            this.saveTokenUserRedis(userSessionRedisDTO);
        }catch (Exception be) {
            log.error(be.getMessage(), be);
        }
    }


    public void saveTokenUserRedis(UserSessionRedisDTO userSessionRedisDTO) throws Exception {

        System.out.println("save token in REDIS");
        System.out.println(userSessionRedisDTO.getToken());

        UserTokenDTO userTokenDTO = oauthService.getUserData(userSessionRedisDTO.getToken());

        UserSessionRedisDTO sessionRedisDTO = this.findEmail(userTokenDTO.getEmail());

        if( isNull(sessionRedisDTO) ){
            UserSessionRedis userSessionRedis =  this.userSessionRepository.save(UserSessionRedis.builder()
                    .id(userTokenDTO.getEmail())
                    .token(userSessionRedisDTO.getToken())
                    .cpf(userSessionRedisDTO.getCpf())
                    .email(userTokenDTO.getEmail())
                    .userID(userTokenDTO.getId().toString())
                    .session(userSessionRedisDTO.getSession())
                    .ttl(7200l)
                    .build());
        }

        System.out.println("New session Saved in redis");
    }

    public UserSessionRedisDTO find(String idUser) throws Exception {

        System.out.println("Find In redis ");

        UserSessionRedisDTO userSessionRedisDTO = this.userSessionRepository.findById(idUser)
                .map(obj -> new UserSessionRedisDTO(obj))
                .orElseThrow(() -> new Exception("UserSessionRedis_NOT_FOUND"));

        System.out.println("FINIS GET REDIS");
        return userSessionRedisDTO;
    }


    public UserSessionRedisDTO findEmail(String email) throws Exception {

        System.out.println("Find In redis ");

        UserSessionRedisDTO userSessionRedisDTO = this.userSessionRepository.findByEmail(email)
                .stream().findFirst()
                .map(obj -> new UserSessionRedisDTO(obj))
                .orElse(null);
        System.out.println("FINIS GET REDIS");
        return userSessionRedisDTO;
    }




}
