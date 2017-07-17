package springbook.learningtest.spring.ioc;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class AnnotationConfigTest {

	private static class BeanA {
		@Autowired
		BeanB beanB;
	}
	
	private static class BeanB {
		
	}
	
	@Test
	public void simpleAtAutowired() {
		AbstractApplicationContext ac = new AnnotationConfigApplicationContext(BeanA.class, BeanB.class);
		BeanA beanA = ac.getBean(BeanA.class);
		assertNotNull(beanA.beanB);
	}
	
}
