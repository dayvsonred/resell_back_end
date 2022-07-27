package com.resell.processor.processor.queue;

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
public class BilletPaymentQueue {

    @Value("${api.rabbitmq.billet.payment.exchange}")
    private String nameExchange;

    @Value("${api.rabbitmq.billet.payment.queue}")
    private String nameQueue;

    @Value("${api.rabbitmq.billet.payment.routing}")
    private String nameRouting;

    @Value("${api.rabbitmq.billet.payment.dlq.queue}")
    private String nameDlqQueue;

    @Value("${api.rabbitmq.billet.payment.dlq.routing}")
    private String nameDlqRouting;

    @Value("${api.rabbitmq.billet.payment.dlq.delay}")
    private Long timeDlqDelay;

    @Value("${api.rabbitmq.billet.payment.parkinglot.queue}")
    private String namePklQueue;

    @Value("${api.rabbitmq.billet.payment.parkinglot.routing}")
    private String namePklRouting;


    @Bean
    public TopicExchange billetPaymentTokenExchange() {
        return new TopicExchange(nameExchange, true, false);
    }

    @Bean
    public Queue billetPaymentTokenQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put(DEAD_LETTER_EXCHANGE_HEADER, nameExchange);
        args.put(DEAD_LETTER_ROUTING_KEY_HEADER, nameDlqRouting);
        return new Queue(nameQueue, true, false, false, args);
    }

    @Bean
    public Binding billetPaymentTokenBinding() {
        return BindingBuilder.bind(billetPaymentTokenQueue()).to(billetPaymentTokenExchange()).with(nameRouting);
    }

    @Bean
    public Queue billetPaymentTokenQueueDLQ() {
        Map<String, Object> args = new HashMap<>();
        args.put(DEAD_LETTER_EXCHANGE_HEADER, nameExchange);
        args.put(DEAD_LETTER_ROUTING_KEY_HEADER, nameRouting);
        args.put(MESSAGE_TTL_HEADER, timeDlqDelay);
        return new Queue(nameDlqQueue, true, false, false, args);
    }

    @Bean
    public Binding billetPaymentTokenBindingDLQ() {
        return BindingBuilder.bind(billetPaymentTokenQueueDLQ()).to(billetPaymentTokenExchange()).with(nameDlqRouting);
    }

    @Bean
    public Queue billetPaymentTokenQueueParkingLot() {
        return new Queue(namePklQueue, true, false, false);
    }

    @Bean
    public Binding billetPaymentTokenBindingParkingLot() {
        return BindingBuilder.bind(billetPaymentTokenQueueParkingLot()).to(billetPaymentTokenExchange()).with(namePklRouting);
    }


}
