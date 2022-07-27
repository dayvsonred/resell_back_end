package com.resell.person.services;


import com.resell.person.dto.oauth.UserTokenDTO;
import com.resell.person.entities.BilletPayment;
import com.resell.person.entities.Person;
import com.resell.person.entities.PersonCredit;
import com.resell.person.repositories.PersonCreditRepository;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.test.FixedSecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PersonCreditService {

    @Autowired
    private PersonCreditRepository personCreditRepository;


    public BigInteger getCredit(Long IdPerson){
        return this.accountBalance(this.personCreditRepository.findAllByPerson(Person.builder().id(IdPerson).build()));
    }

    public Boolean creditForPayment(UserTokenDTO userTokenDTO, BigInteger billetValue) {
        BigInteger credit = this.getCredit(userTokenDTO.getId());
        return credit.compareTo(billetValue) >= 0 ? true : false;
    }


    /**
     * get account balance total
     * */
    private BigInteger accountBalance(List<PersonCredit> listPersonCredit){
        BigInteger balance = BigInteger.ZERO;

        for (PersonCredit item : listPersonCredit){
            log.info(item.getType());
            log.info(item.getCredit().toString());

            if(item.getType().equals("C")){
                balance = balance.add(item.getCredit());
            }else if(item.getType().equals("D")){
                balance.subtract(item.getCredit());
            }
        }
        return balance;
    }


    public void debitPaymentBillet(Long idPerson, BilletPayment billetPayment){
        try{
            this.personCreditRepository.save(PersonCredit.builder()
                            .credit(billetPayment.getAmount())
                            .type("D")
                            .createDate(LocalDateTime.now())
                            .person(Person.builder().id(idPerson).build())
                            .historic("BILLET DEBIT :" +billetPayment.getHash() )
                    .build());
        }catch (Exception e){
            log.error("CRITICAL ERROR PAYMENT BILLET DEBIT");
            throw new RuntimeException("CRITICAL ERROR PAYMENT BILLET DEBIT", e);
        }
    }
}
