package com.resell.processor.processor.integration;

import com.resell.processor.processor.dto.getnet.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "getnet", url = "https://api-sandbox.getnet.com.br/")
public interface GetNetPaymentsIntegration {

    @PostMapping(value = "/auth/oauth/v2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    AuthGetNetDTO token(
            @RequestHeader HttpHeaders headers,
            @RequestParam("scope") String scope,
            @RequestParam("grant_type") String grant_type,
            @RequestBody AuthGetNetRequestDTO authGetNetRequestDTO
    );

    @PostMapping(value = "v1/tokens/card", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    TokenCardDTO tokenCard(
            @RequestHeader HttpHeaders headers,
            @RequestBody TokenCardRequestDTO tokenCardRequestDTO
    );

    @PostMapping(value = "v1/payments/credit", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    PaymentsCreditResponseDTO paymentsCredit(
            @RequestHeader HttpHeaders headers,
            @RequestBody PaymentsCreditRequestDTO paymentsCreditRequestDTO
    );


}
