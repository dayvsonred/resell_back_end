package com.resell.processor.processor.consumer;

import com.resell.processor.processor.dto.UserSessionRedisDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthTokenConsumer {

    @RabbitListener(queues = {"${api.rabbitmq.user.token.queue}"})
    public void receiveCreatedAccountEvent(UserSessionRedisDTO userSessionRedisDTO) {
        try {

            System.out.println("**********************************************************");
            System.out.println("**********************************************************");
            System.out.println("**********************************************************");
            System.out.println("RabbitListener +++++++++");
            System.out.println("**********************************************************");

        } catch (Exception be) {
            log.error(be.getMessage(), be);
        }
    }
}
