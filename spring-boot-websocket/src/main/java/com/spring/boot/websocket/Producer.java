package com.spring.boot.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendMessageTo(String topic, String message) {
		StringBuffer builder = new StringBuffer();
		builder.append("[");
		builder.append(SIMPLE_DATE_FORMAT.format(new Date()));
		builder.append("] ");
		builder.append(message);
		
		this.template.convertAndSend("/topic/" + topic, builder.toString());
	}

}
