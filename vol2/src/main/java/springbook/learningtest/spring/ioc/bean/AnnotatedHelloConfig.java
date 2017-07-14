package springbook.learningtest.spring.ioc.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbook.learningtest.spring.ioc.bean.printer.Printer;
import springbook.learningtest.spring.ioc.bean.printer.StringPrinter;

/**
 * Created by hyunsoo0813 on 2017. 7. 14..
 */
@Configuration
public class AnnotatedHelloConfig {

    @Bean
    public AnnotatedHello annotatedHello() {
        return new AnnotatedHello();
    }

    @Bean
    public Hello hello() {
        Hello hello = new Hello();
        hello.setName("spring");
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Hello hello2() {
        Hello hello = new Hello();
        hello.setName("spring2");
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Printer printer() {
        return new StringPrinter();
    }

}
