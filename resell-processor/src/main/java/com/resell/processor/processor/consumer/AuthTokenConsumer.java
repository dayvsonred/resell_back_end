package com.resell.processor.processor.consumer;

import com.resell.processor.processor.dto.UserSessionRedisDTO;
import com.resell.processor.processor.service.GetNetPaymentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenConsumer {

    private final GetNetPaymentsService getNetPaymentsService;

    @RabbitListener(queues = {"${api.rabbitmq.user.token.queue}"})
    public void receiveCreatedAccountEvent(UserSessionRedisDTO userSessionRedisDTO) {
        try {

            System.out.println("**********************************************************");
            System.out.println("**********************************************************");
            System.out.println("**********************************************************");
            System.out.println("RabbitListener +++++++++");
            System.out.println("**********************************************************");

            this.getNetPaymentsService.generateAccessToken();

        } catch (Exception be) {
            log.error(be.getMessage(), be);
        }
    }
}
