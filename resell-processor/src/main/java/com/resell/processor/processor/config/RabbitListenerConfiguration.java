package com.resell.processor.processor.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Configuration
@EnableRabbit
public class RabbitListenerConfiguration {


    public static final String SINGLE_CONSUMER_HEADER = "x-single-active-consumer";
    public static final String DEAD_LETTER_EXCHANGE_HEADER = "x-dead-letter-exchange";
    public static final String DEAD_LETTER_ROUTING_KEY_HEADER = "x-dead-letter-routing-key";
    public static final String MESSAGE_TTL_HEADER = "x-message-ttl";
    public static final String X_DEATH_HEADER = "x-death";
    public static final String X_DEATH_COUNT = "count";

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String user;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtualhost}")
    private String virtualHost;
    @Value("${spring.rabbitmq.thread.min}")
    private Integer minThreads;
    @Value("${spring.rabbitmq.thread.max}")
    private Integer maxThreads;

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(rabbitConnectionFactory());
    }

    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory());
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory concurrentRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory());
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(minThreads);
        factory.setMaxConcurrentConsumers(maxThreads);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory());
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    public static Long getMessageErrorCount(Message message) {
        List<Map<String, ?>> xDeath = (List<Map<String, ?>>) message.getMessageProperties().getHeaders().get(X_DEATH_HEADER);
        Long count = 0L;
        if (nonNull(xDeath)) {
            count = xDeath.stream().mapToLong(map -> (Long) map.get(X_DEATH_COUNT)).sum();
        }
        return count;
    }

    public static void clearError(Message message) {
        message.getMessageProperties().getHeaders().remove(X_DEATH_HEADER);
    }
}

