package com.assignment.serviceone.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration {

    public static final String LISTENER_METHOD = "receiveMessage";

    @Value("${topic.exchange}")
    public static final String topicExchange = "spring-boot-exchange";

    @Value("${queue.name}")
    public static final String queueName = "spring-boot";

    @Bean
    Queue queue(){

        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange(){

        return new TopicExchange(topicExchange);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange){

        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

}
