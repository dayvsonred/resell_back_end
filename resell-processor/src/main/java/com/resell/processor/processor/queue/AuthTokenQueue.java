package com.resell.processor.processor.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthTokenQueue {

    @Value("${api.rabbitmq.user.token.exchange}")
    private String topicExchangeName;

    @Value("${api.rabbitmq.user.token.queue}")
    private String queueName;

    @Bean
    Queue queueToken() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchangeToken() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding bindingToken(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#");
    }

}
