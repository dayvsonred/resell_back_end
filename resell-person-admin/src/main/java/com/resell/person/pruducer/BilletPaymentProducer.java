package com.resell.person.pruducer;

import com.resell.person.dto.BilletPaymentRabbitDTO;
import com.resell.person.dto.oauth.UserSessionRedisDTO;
import com.resell.person.entities.BilletPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BilletPaymentProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${api.rabbitmq.billet.payment.exchange}")
    private String exchange;

    @Value("${api.rabbitmq.billet.payment.routing}")
    private String routing;

    public void send(String token,BilletPayment billetPayment) {
        try{
            rabbitTemplate.convertAndSend(exchange, routing, BilletPaymentRabbitDTO.builder()
                            .status(billetPayment.getStatus())
                            .amount(billetPayment.getAmount())
                            .barCode(billetPayment.getBarCode())
                            .hash(billetPayment.getHash())
                            .token(token)
                    .build());
            log.info("rabbit billet.payment :" + billetPayment.getId() );
        }catch (Exception e ){
            log.error("ERROR SEND MSG RABBIT billet.payment" + billetPayment.getId());
            throw new RuntimeException("ERROR SEND MSG RABBIT billet.payment" + billetPayment.getId(), e);
        }
    }
}
