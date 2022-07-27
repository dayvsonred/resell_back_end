package com.resell.processor.processor.integration;

import com.resell.processor.processor.dto.BilletPaymentDTO;
import com.resell.processor.processor.dto.getnet.AuthGetNetRequestDTO;
import com.resell.processor.processor.dto.oauth.UserTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name = "resell-person", path = "/billet")
public interface ResellPersonAdminIntegration {

    @PostMapping( value = "/confirm", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    BilletPaymentDTO salesConfirm(@RequestHeader HttpHeaders headers,
                                  @RequestBody BilletPaymentDTO dto
    );

}
