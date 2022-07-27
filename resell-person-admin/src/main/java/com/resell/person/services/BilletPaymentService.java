package com.resell.person.services;


import com.resell.person.dto.BilletPaymentDTO;
import com.resell.person.dto.oauth.UserTokenDTO;
import com.resell.person.entities.BilletPayment;
import com.resell.person.exception.CustomerException;
import com.resell.person.pruducer.BilletPaymentProducer;
import com.resell.person.repositories.BilletPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

import static org.apache.commons.lang.BooleanUtils.isFalse;
import static java.util.Objects.isNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class BilletPaymentService {

    @Autowired
    private BilletPaymentRepository billetPaymentRepository;
    @Autowired
    private OauthService oauthService;
    @Autowired
    private PersonServiceImpl personServiceImpl;
    @Autowired
    private PersonCreditService personCreditService;

    private final BilletPaymentProducer billetPaymentProducer;

    public BilletPayment create(BilletPayment obj) {
        return this.billetPaymentRepository.save(obj);
    }

    /**
     * salva no bd o boleto
     * status START
     * fack validar codigo do boleto ----------------------------------
     * validar saldo do cliente valor do boleto
     * enviar para rabit
     * processo
     * (
     * vai pegar o obje
     * debitar valor depois pagar
     * )
     * */
    public BilletPaymentDTO payment(String token, BilletPaymentDTO obj) throws CustomerException {
        try {
            UserTokenDTO userTokenDTO = this.oauthService.getUserData(token);

            BilletPayment billetPaymentStatus = this.findBilletStatus(obj.getBarCode());

            if(isFalse(isNull(this.findBilletStatus(obj.getBarCode()).getId()))){
                return BilletPaymentDTO.builder()
                        .hash(billetPaymentStatus.getHash())
                        .barCode(billetPaymentStatus.getBarCode())
                        .amount(billetPaymentStatus.getAmount())
                        .status(billetPaymentStatus.getStatus())
                        .build();
            }

            /*
            * find billet in BD
            * if true return status
            * **/

            BigDecimal amountTotal  = new BigDecimal(this.getValueBillet(obj.getBarCode()));
            BigDecimal percentHundred = new BigDecimal(100);
            BigDecimal percentTem = new BigDecimal(10);
            BigDecimal cache =  amountTotal.divide(percentHundred);
            cache = cache.divide(percentTem);

            BilletPayment billetPayment =  this.billetPaymentRepository.save(BilletPayment.builder()
                    .amount(obj.getAmount())
                    .barCode(obj.getBarCode())
                    .saleDate(LocalDate.now())
                    .hash(UUID.randomUUID().toString())
                    .person( this.personServiceImpl.getPerson( userTokenDTO.getId() ))
                    .cacheBack(cache)
                    .status("START")
                    .build());


            /**
             * valid credit person
             * */
            if(isFalse(this.personCreditService.creditForPayment(userTokenDTO, this.getValueBillet(obj.getBarCode())))){
                this.upStatusPaymentInsufficientFunds(billetPayment);
                return BilletPaymentDTO.builder()
                        .hash(billetPayment.getHash())
                        .barCode(billetPayment.getBarCode())
                        .amount(billetPayment.getAmount())
                        .status("INSUFFICIENT_FUNDS")
                        .build();
            }

            this.billetPaymentProducer.send(token, billetPayment);

            return BilletPaymentDTO.builder()
                    .hash(billetPayment.getHash())
                    .barCode(billetPayment.getBarCode())
                    .amount(billetPayment.getAmount())
                    .status("START")
                    .build();

        }catch (Exception e){
            log.error("PAYMENT ERROR", e);
            throw new CustomerException("PAYMENT ERROR" , e);
        }
    }

    private BigInteger getValueBillet(String codeBar) throws CustomerException {
        try {
            if(codeBar.length() <= 10){
                throw new CustomerException("COD BAR INVALID LESS LENGTH OR INVALID");
            }
            return new BigInteger(codeBar.substring(codeBar.length()-10));
        }catch (Exception e){
         log.error("COD BAR INVALID LESS LENGTH OR INVALID", e);
         throw new CustomerException("COD BAR INVALID LESS LENGTH OR INVALID");
        }
    }

    private void upStatusPaymentInsufficientFunds(BilletPayment billetPayment){
        try {
            billetPayment.setStatus("INSUFFICIENT_FUNDS");
            this.billetPaymentRepository.saveAndFlush(billetPayment);
        }catch (Exception e){
            log.info("CRITICAL ERROR upStatusPaymentInsufficientFunds ", billetPayment.getId());
            throw new RuntimeException("CRITICAL ERROR upStatusPaymentInsufficientFunds "+ billetPayment.getId(), e);
        }
    }

    private BilletPayment findBilletStatus(String barCode){
        try {
            return this.billetPaymentRepository.findByBarCode(barCode).stream()
                    .findFirst().orElse(BilletPayment.builder().build());
        }catch (Exception e){
            log.info("ERROR findBilletStatus ", barCode);
            throw new RuntimeException("ERROR findBilletStatus "+ barCode, e);
        }
    }


    public BilletPaymentDTO salesConfirm(String token, BilletPaymentDTO obj) throws CustomerException {
        try {
            UserTokenDTO userTokenDTO = this.oauthService.getUserData(token);
            BilletPayment billetPayment = this.billetPaymentRepository.findByHash(obj.getHash())
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new CustomerException("CRITICAL ERROR BILLET PAYMENT "+obj.getHash()));
            billetPayment.setAmount(this.getValueBillet(billetPayment.getBarCode()));

            if( this.personCreditService.creditForPayment( userTokenDTO, billetPayment.getAmount() )){
                this.personCreditService.debitPaymentBillet(userTokenDTO.getId(),billetPayment);
                billetPayment.setStatus("PAID_OUT");
            }else{
                billetPayment.setStatus("INSUFFICIENT_FUNDS");
            }
            this.billetPaymentRepository.saveAndFlush(billetPayment);
            return BilletPaymentDTO.builder()
                        .hash(billetPayment.getHash())
                        .amount(billetPayment.getAmount())
                        .status(billetPayment.getStatus())
                        .barCode(billetPayment.getBarCode())
                    .build();
        }catch (Exception e){
            log.info("CRITICAL ERROR salesConfirm ", obj.getHash());
            throw new RuntimeException("CRITICAL ERROR salesConfirm "+ obj.getHash(), e);
        }
    }
}
