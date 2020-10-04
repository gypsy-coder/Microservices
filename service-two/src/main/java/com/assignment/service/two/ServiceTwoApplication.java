package com.assignment.service.two;

import com.assignment.service.two.config.RabbitConfiguration;
import com.assignment.service.two.config.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ServiceTwoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceTwoApplication.class, args);
	}

}
