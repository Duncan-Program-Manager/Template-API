package com.dpm.templateapi.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class userMQConfig {

    @Value("${rabbitmq.queue2}")
    private String queue;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingkey2}")
    private String routingKey;

    private userReciever eventListener;

    public userMQConfig(userReciever eventListener)
    {
        this.eventListener = eventListener;
    }

    @Bean
    Queue virusScannerQueue() {
        return new Queue(queue, true);
    }

    @Bean
    DirectExchange dpmExchange() {
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    @Bean
    Binding startBinding(@Qualifier("virusScannerQueue") Queue queue, @Qualifier("dpmExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("watcherz.events.bank");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue);
        container.setMessageListener(eventListener);
        return container;
    }
}
