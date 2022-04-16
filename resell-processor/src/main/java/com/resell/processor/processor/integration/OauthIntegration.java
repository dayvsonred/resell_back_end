package com.resell.processor.processor.integration;


import com.resell.processor.processor.dto.oauth.UserTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name = "resell-oauth", path = "/users")
public interface OauthIntegration {

    @GetMapping( value = "/token" )
    UserTokenDTO findUserByToken(@RequestHeader HttpHeaders headers);

}
