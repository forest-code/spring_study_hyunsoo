package com.spring.boot.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Producer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendTo(String routingKey, String message) {
		log.info("# 전송 >> ...");
		this.rabbitTemplate.convertAndSend(routingKey, message);
	}
}
