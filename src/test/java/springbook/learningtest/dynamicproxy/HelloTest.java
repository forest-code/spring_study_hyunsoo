package springbook.learningtest.dynamicproxy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class HelloTest {

	@Test
	public void simpleProxy() {
		Hello hello = new HelloTarget();
		assertThat(hello.sayHello("hyunsoo"), is("Hello hyunsoo"));
		assertThat(hello.sayHi("hyunsoo"), is("Hi hyunsoo"));
		assertThat(hello.sayThankYou("hyunsoo"), is("Thank You hyunsoo"));
	}
	
	@Test
	public void uppercaseProxy() {
		Hello proxiedHello = new HelloUppercase(new HelloTarget());
		assertThat(proxiedHello.sayHello("hyunto"), is("HELLO HYUNTO"));
		assertThat(proxiedHello.sayHi("hyunto"), is("HI HYUNTO"));
		assertThat(proxiedHello.sayThankYou("hyunto"), is("THANK YOU HYUNTO"));
	}
	
	@Test
	public void uppercaseDynamicProxy() {
		Hello proxiedHello = (Hello)Proxy.newProxyInstance(
				getClass().getClassLoader(), 
				new Class[] { Hello.class }, 
				new UppercaseHandler(new HelloTarget())
			);
		assertThat(proxiedHello.sayHello("hyunto"), is("HELLO HYUNTO"));
		assertThat(proxiedHello.sayHi("hyunto"), is("HI HYUNTO"));
		assertThat(proxiedHello.sayThankYou("hyunto"), is("THANK YOU HYUNTO"));
	}
	
}