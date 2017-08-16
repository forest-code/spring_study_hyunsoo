package com.spring.boot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

	@RabbitListener(queues = "${myqueue}")
	public void handler(String message) {
		log.info("# 소비기 >> " + message);
	}
}
