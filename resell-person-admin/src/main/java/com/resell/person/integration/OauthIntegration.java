package com.resell.person.integration;

import com.resell.person.dto.oauth.UserTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "resell-oauth", path = "/users")
public interface OauthIntegration {

    @GetMapping(value = "/token")
    UserTokenDTO findUserByToken(@RequestHeader HttpHeaders headers);

}
