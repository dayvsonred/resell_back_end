package com.resell.person.services;

import com.resell.person.dto.oauth.UserSessionRedisDTO;
import com.resell.person.pruducer.SessionTokenProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitSessionToken {

    private final SessionTokenProducer sessionTokenProducer;

    public void send(UserSessionRedisDTO userSessionRedis) {
        try{
            this.sessionTokenProducer.send(userSessionRedis);
        }catch (Exception e) {
            log.error(e.getMessage(), e);

        }
    }
}
