package com.spring.boot.jms.config;

import com.spring.boot.jms.message.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * Created by hyunsoo0813 on 2017. 8. 16..
 */
@Configuration
public class MessagingConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Value("${myqueue}")
    private String queue;

    @Bean
    public DefaultMessageListenerContainer messageListener() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setDestinationName(queue);
        container.setMessageListener(new Consumer());
        return container;
    }
}
