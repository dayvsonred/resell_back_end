package com.resell.processor.processor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resell.processor.processor.dto.getnet.*;
import com.resell.processor.processor.enumeration.AuthorizationHeaderEnum;
import com.resell.processor.processor.integration.GetNetPaymentsIntegration;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.*;

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

        System.out.println("Token : " + authGetNetDTO.getAccess_token());

        TokenCardDTO tokenCardDTO = this.generateTokenCard(authGetNetDTO.getAccess_token(),
                paymentGetNetDTO.getCardNumber(),
                paymentGetNetDTO.getCustomerId()
        );

        System.out.println("CARD Token : " + tokenCardDTO.getNumberToken());

        ObjectMapper mapperNull = new ObjectMapper();

        PaymentsCreditRequestDTO paymentsCreditRequestDTO = PaymentsCreditRequestDTO.builder()
                .seller_id("f8555454-65a5-4ccc-85d2-f6a43f8c213e")
                .amount("1555")
                .order(OrderDTO.builder().order_id("12348").sales_tax(0l).product_type("service").build())
                .customer(CustomerDTO.builder().customer_id("12348").billing_address(BillingAddressDTO.builder().build()).build())
                .device(DeviceDTO.builder().build()) //***/
                .shippings(List.of(ShippingsDTO.builder().address(AddressDTO.builder().build()).build()))
                .sub_merchant(SubMerchantDTO.builder().build())
                .credit(CreditDTO.builder()
                        .delayed(false)
                        .save_card_data(false)
                        .transaction_type("FULL")
                        .number_installments(1l)
                        .card(CardDTO.builder()
                                .number_token(tokenCardDTO.getNumberToken())
                                .cardholder_name("JOAO DA SILVA")
                                .expiration_month("12")
                                .expiration_year("27")
                                .build())
                        .build())
                .build();

        PaymentsCreditResponseDTO paymentsCredit = this.paymentsCredit(authGetNetDTO.getAccess_token(), paymentsCreditRequestDTO);


        System.out.println("************************************");
        System.out.println("e sobre isso e ta tudo bem");
    }




    public AuthGetNetDTO generateAccessToken(){
        String authorization = this.generatedAuth();
        return getNetPaymentsIntegration.token(
                AuthorizationHeaderEnum.Authorization.getHeadersAuth( authorization, authorization.length() + scope.length() + grantType.length() ),
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

        return this.getNetPaymentsIntegration.tokenCard(
                AuthorizationHeaderEnum.Authorization.getHeadersCart(authorization),
                TokenCardRequestDTO.builder()
                        .cardNumber(cardNumber)
                        .customerId(customerId)
                        .build()
        );

//        Collection<String> simulation_guids = response.headers().get("simulation_guid");
//
//
//        System.out.println("************************************");
//
//        return TokenCardDTO.builder().simulation_guid(simulation_guids.iterator().next()).numberToken("501947e11ab9df1ff9512026523c18e1de918c0f651a5323758963af5f148cffa305c7a91ed60901c49cb46be05892e42634851c0a8a0b9eb06d68b448cfb19a").build();
    }

    public PaymentsCreditResponseDTO paymentsCredit(String authorization, PaymentsCreditRequestDTO paymentsCreditRequestDTO){
        try{
            PaymentsCreditResponseDTO dtgsdgsdf =  this.getNetPaymentsIntegration.paymentsCredit(
                    AuthorizationHeaderEnum.Authorization.getHeadersCart(authorization),
                    paymentsCreditRequestDTO
            );
            return dtgsdgsdf;

        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return PaymentsCreditResponseDTO.builder().build();
    }
}
