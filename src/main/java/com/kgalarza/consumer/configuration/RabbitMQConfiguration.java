package com.kgalarza.consumer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {


    public static final String QUEUE_NAME = "simpleQueue";
    public static final String EXCHANGE_NAME = "simpleExchange";

    @Bean
    public Queue queue(){return new Queue(QUEUE_NAME, true);}

    @Bean
    public DirectExchange exchange(){return new DirectExchange(EXCHANGE_NAME);}

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("routingKey");
    }
}
