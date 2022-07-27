package com.resell.processor.processor.consumer;

import com.resell.processor.processor.dto.BilletPaymentRabbitDTO;
import com.resell.processor.processor.dto.oauth.UserSessionRedisDTO;
import com.resell.processor.processor.service.BilletPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BilletPaymentConsumer {

    private final BilletPaymentService billetPaymentService;

    @RabbitListener(queues = {"${api.rabbitmq.billet.payment.queue}"})
    public void receiveCreatedAccountEvent(BilletPaymentRabbitDTO billetPaymentRabbitDTO) {
        try {
            this.billetPaymentService.startPayment(billetPaymentRabbitDTO);
        } catch (Exception be) {
            log.error(be.getMessage(), be);
        }
    }
}
