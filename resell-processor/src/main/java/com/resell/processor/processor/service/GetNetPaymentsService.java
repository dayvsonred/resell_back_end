package com.resell.processor.processor.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.resell.processor.processor.dto.getnet.*;
import com.resell.processor.processor.enumeration.AuthorizationHeaderEnum;
import com.resell.processor.processor.integration.GetNetPaymentsIntegration;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.net.http.HttpClient;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetNetPaymentsService {

    @Autowired
    private final GetNetPaymentsIntegration getNetPaymentsIntegration;

    @Value("${integration.getnet.auth.clientId}")    private String clientId;
    @Value("${integration.getnet.auth.clientSecret}")    private String clientSecret;
    @Value("${integration.getnet.auth.scope}")    private String scope;
    @Value("${integration.getnet.auth.grantType}")    private String grantType;


    public void createdPaymentGetNet(){

        PaymentGetNetDTO paymentGetNetDTO = PaymentGetNetDTO.builder()
                .cardNumber("5155901222280001").customerId("customer_21081826").build();

        AuthGetNetDTO authGetNetDTO = this.generateAccessToken();

        TokenCardDTO tokenCardDTO = this.generateTokenCard(authGetNetDTO.getAccess_token(),
                paymentGetNetDTO.getCardNumber(),
                paymentGetNetDTO.getCustomerId()
        );

        PaymentsCreditDTO paymentsCredit = this.paymentsCredit(authGetNetDTO.getAccess_token());


        System.out.println("e sobre isso e ta tudo bem");
    }




    public AuthGetNetDTO generateAccessToken(){
        String authorization = this.generatedAuth();

        return getNetPaymentsIntegration.token(
                AuthorizationHeaderEnum.Authorization.getHeadersAuth(authorization, authorization.length() + scope.length() + grantType.length()),
                scope,
                grantType,
                AuthGetNetRequestDTO.builder().build()
        );
    }

    private String generatedAuth(){
        try{
            StringBuilder clientIdSecret = new StringBuilder().append(clientId).append(":").append(clientSecret);
            return Base64.getEncoder().encodeToString(clientIdSecret.toString().getBytes());
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public TokenCardDTO generateTokenCard(String authorization, String cardNumber, String customerId){

        return getNetPaymentsIntegration.tokenCard(
                AuthorizationHeaderEnum.Authorization.getHeadersCart(authorization),
                TokenCardRequestDTO.builder()
                        .cardNumber(cardNumber)
                        .customerId(customerId)
                        .build()
        );
    }

    public PaymentsCreditDTO paymentsCredit(String authorization){

        return getNetPaymentsIntegration.payments(
                AuthorizationHeaderEnum.Authorization.getHeadersCart(authorization),
                PaymentsRequestDTO.builder()
                        .sellerId("123123123123")
                        .build()
        );
    }
}
