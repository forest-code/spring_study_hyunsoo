package com.spring.boot.jms.message;

import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by hyunsoo0813 on 2017. 8. 16..
 */
@Slf4j
public class Consumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            log.info("# Consumer >> " + message.getBody(Object.class));
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

}
