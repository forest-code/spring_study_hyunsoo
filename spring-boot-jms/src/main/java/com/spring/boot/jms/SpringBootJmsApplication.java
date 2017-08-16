package com.spring.boot.jms;

import com.spring.boot.jms.message.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@SpringBootApplication
public class SpringBootJmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJmsApplication.class, args);
	}

	@Value("${myqueue}")
	private String queue;

	@Bean
	CommandLineRunner sendMessage(JmsTemplate jmsTemplate) {
		return args -> {
			Producer producer = new Producer(jmsTemplate);
			producer.sendTo(queue, "스프링 부트 시작!");
		};
	}

	@JmsListener(destination = "${myqueue}")
	@SendTo("${myotherqueue}")
	public String simpleConsumer(String message) {
		log.info("### Simple Consumer >> " + message);
		return message + " 와 스프링 메시지를 시작!";
	}

	@JmsListener(destination = "${myotherqueue}")
	public void anotherSimpleConsumer(String message) {
		log.info("### Another Simple Consumer >> " + message);
	}

	@Bean
	CommandLineRunner start(JmsTemplate jmsTemplate) {
		return args -> {
			log.info("### 전송 >> ...");
			jmsTemplate.convertAndSend(queue, "스프링 부트");
		};
	}
}
