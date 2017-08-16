package com.spring.boot.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class SpringBootRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitmqApplication.class, args);
	}
	
	@Value("${myqueue}")
	private String queue;
	
	@Bean
	Queue queue() {
		return new Queue(queue, false);
	}
	
	@Autowired
	private Producer producer;
	
	@Bean
	CommandLineRunner sender() {
		return args -> {
			producer.sendTo(queue, "안녕하세요, 여러분!");
		};
	}

	@Scheduled(fixedDelay = 500L)
	public void sendMessages() {
		producer.sendTo(queue, "안녕하세요, 여러분! 지금 시각은 " + new Date());
	}
}
