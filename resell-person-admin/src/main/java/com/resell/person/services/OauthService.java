package com.resell.person.services;

import com.resell.person.dto.oauth.UserTokenDTO;
import com.resell.person.integration.OauthIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class OauthService {

    private final OauthIntegration oauthIntegration;

    public UserTokenDTO getUserData(String token){
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", token);
            UserTokenDTO userTokenDTO = oauthIntegration.findUserByToken(httpHeaders);

            /**
             * fazer find dos dados do usuario
             * add cach com redis
             * */

            return userTokenDTO;

        }catch (Exception e){
            log.error("ERROR GET USER BY TOKEN");
            throw new RuntimeException("ERROR GET USER BY TOKEN", e);
        }
    }
}
