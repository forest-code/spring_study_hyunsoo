package com.spring.boot.jms.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

/**
 * Created by hyunsoo0813 on 2017. 8. 16..
 */
@Slf4j
public class Producer {

    private JmsTemplate jmsTemplate;

    public Producer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendTo(String queue, String message) {
        this.jmsTemplate.convertAndSend(queue, message);
        log.info("# Producer >> 메세지 전송");
    }

}
