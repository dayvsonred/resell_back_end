package com.resell.processor.processor.service;

import com.resell.processor.processor.dto.BilletPaymentDTO;
import com.resell.processor.processor.dto.BilletPaymentRabbitDTO;
import com.resell.processor.processor.integration.ResellPersonAdminIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BilletPaymentService {

    @Autowired
    private final ResellPersonAdminIntegration resellPersonAdminIntegration;


    public void startPayment(BilletPaymentRabbitDTO billetPaymentRabbitDTO){
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", billetPaymentRabbitDTO.getToken());

            log.info("EXEC PAYMENT BILLET"+ billetPaymentRabbitDTO.getHash());

            this.resellPersonAdminIntegration.salesConfirm(
                    httpHeaders,
                    BilletPaymentDTO.builder()
                            .amount(billetPaymentRabbitDTO.getAmount())
                            .barCode(billetPaymentRabbitDTO.getBarCode())
                            .hash(billetPaymentRabbitDTO.getHash())
                            .status(billetPaymentRabbitDTO.getStatus())
                            .build() );
        }catch (Exception e){
            log.error("CRITICAL ERROR PAYMENT BILLET "+ billetPaymentRabbitDTO.getHash());
            throw new RuntimeException("CRITICAL ERROR PAYMENT BILLET "+ billetPaymentRabbitDTO.getHash(), e);
        }
    }
}
