package springbook.learningtest.spring.ioc.bean;

import org.springframework.stereotype.Component;
import springbook.learningtest.spring.ioc.bean.printer.Printer;

/**
 * Created by hyunto on 2017. 7. 14..
 */
@Component
public class AnnotatedHello {

    String name;

    Printer printer;

    public String sayHello() {
        return "Hello " + this.name;
    }

    public void print() {
        this.printer.print(sayHello());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

}
