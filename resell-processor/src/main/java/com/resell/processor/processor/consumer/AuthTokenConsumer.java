package com.resell.processor.processor.consumer;

import com.resell.processor.processor.dto.UserSessionRedisDTO;
import com.resell.processor.processor.service.GetNetPaymentsService;
import com.resell.processor.processor.service.SessionUserTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenConsumer {

    private final SessionUserTokenService sessionUserTokenService;

    @RabbitListener(queues = {"${api.rabbitmq.user.token.queue}"})
    public void receiveCreatedAccountEvent(UserSessionRedisDTO userSessionRedisDTO) {
        try {

            System.out.println("**********************************************************");
            System.out.println("**********************************************************");
            System.out.println("**********************************************************");
            System.out.println("RabbitListener +++++++++");
            System.out.println("**********************************************************");

            this.sessionUserTokenService.validTokenSession(userSessionRedisDTO);

        } catch (Exception be) {
            log.error(be.getMessage(), be);
        }
    }
}
