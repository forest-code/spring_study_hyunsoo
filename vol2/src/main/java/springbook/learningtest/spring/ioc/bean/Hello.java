package springbook.learningtest.spring.ioc.bean;

import springbook.learningtest.spring.ioc.bean.printer.Printer;

public class Hello {

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
