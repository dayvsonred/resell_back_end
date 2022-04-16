package com.resell.processor.processor.service;


import com.resell.processor.processor.dto.oauth.UserTokenDTO;
import com.resell.processor.processor.integration.OauthIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    @Autowired
    private final OauthIntegration oauthIntegration;

    public UserTokenDTO getUserData(String token){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token);
        httpHeaders.add("Content-Type", "application/javascript");

        UserTokenDTO userTokenDTO = oauthIntegration.findUserByToken(httpHeaders);

        /**
         * fazer find dos dados do usuario
         * add cach com redis
         * */


        return userTokenDTO;
    }
}
