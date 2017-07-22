package springbook.learningtest.spring.ioc.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hyunsoo0813 on 2017. 7. 23..
 */
@Configuration
public class SimpleConfig {

    @Autowired
    private Hello hello;

    @Bean
    public Hello hello() {
        return new Hello();
    }

}
