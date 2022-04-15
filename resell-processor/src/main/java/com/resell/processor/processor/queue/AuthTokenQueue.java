package com.resell.processor.processor.queue;

import com.resell.processor.processor.config.RabbitListenerConfiguration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.resell.processor.processor.config.RabbitListenerConfiguration.*;

@Configuration
public class AuthTokenQueue {

    @Value("${api.rabbitmq.user.token.exchange}")
    private String nameExchange;

    @Value("${api.rabbitmq.user.token.queue}")
    private String nameQueue;

    @Value("${api.rabbitmq.user.token.routing}")
    private String nameRouting;

    @Value("${api.rabbitmq.user.token.dlq.queue}")
    private String nameDlqQueue;

    @Value("${api.rabbitmq.user.token.dlq.routing}")
    private String nameDlqRouting;

    @Value("${api.rabbitmq.user.token.dlq.delay}")
    private Long timeDlqDelay;

    @Value("${api.rabbitmq.user.token.parkinglot.queue}")
    private String namePklQueue;

    @Value("${api.rabbitmq.user.token.parkinglot.routing}")
    private String namePklRouting;


    @Bean
    public TopicExchange tokenExchange() {
        return new TopicExchange(nameExchange, true, false);
    }

    @Bean
    public Queue tokenQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put(DEAD_LETTER_EXCHANGE_HEADER, nameExchange);
        args.put(DEAD_LETTER_ROUTING_KEY_HEADER, nameDlqRouting);
        return new Queue(nameQueue, true, false, false, args);
    }

    @Bean
    public Binding tokenBinding() {
        return BindingBuilder.bind(tokenQueue()).to(tokenExchange()).with(nameRouting);
    }

    @Bean
    public Queue tokenQueueDLQ() {
        Map<String, Object> args = new HashMap<>();
        args.put(DEAD_LETTER_EXCHANGE_HEADER, nameExchange);
        args.put(DEAD_LETTER_ROUTING_KEY_HEADER, nameRouting);
        args.put(MESSAGE_TTL_HEADER, timeDlqDelay);
        return new Queue(nameDlqQueue, true, false, false, args);
    }

    @Bean
    public Binding tokenBindingDLQ() {
        return BindingBuilder.bind(tokenQueueDLQ()).to(tokenExchange()).with(nameDlqRouting);
    }

    @Bean
    public Queue tokenQueueParkingLot() {
        return new Queue(namePklQueue, true, false, false);
    }

    @Bean
    public Binding tokenBindingParkingLot() {
        return BindingBuilder.bind(tokenQueueParkingLot()).to(tokenExchange()).with(namePklRouting);
    }


}
