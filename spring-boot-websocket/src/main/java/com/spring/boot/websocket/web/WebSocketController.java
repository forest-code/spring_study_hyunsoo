package com.spring.boot.websocket.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.websocket.Producer;

@RestController
public class WebSocketController {
	
	@Autowired
	private Producer producer;
	
	@RequestMapping("/send/{topic}")
	public String sender(@PathVariable String topic, @RequestParam String message) {
		producer.sendMessageTo(topic, message);
		return "OK - 전송됨";
	}

}
