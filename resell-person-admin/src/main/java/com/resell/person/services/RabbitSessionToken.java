package com.resell.person.services;

import com.resell.person.entities.session.UserSessionRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitSessionToken {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Value("${api.rabbitmq.user.token.exchange}")
    private String exchange;

    @Value("${api.rabbitmq.user.token.routingkey}")
    private String routingkey;

    public void send(UserSessionRedis userSessionRedis) {
        try{
            System.out.println("Send for RABBIT : " + userSessionRedis.getSession());
            rabbitTemplate.convertAndSend(exchange, routingkey, userSessionRedis);
        }catch (Exception e) {
            log.error(e.getMessage(), e);

        }
    }
}
