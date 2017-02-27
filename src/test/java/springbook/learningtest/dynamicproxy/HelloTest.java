package springbook.learningtest.dynamicproxy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

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
	
	@Test
	public void proxyFactoryBean() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		pfBean.addAdvice(new UppercaseAdvice());
		
		Hello proxiedHello = (Hello) pfBean.getObject();
		
		assertThat(proxiedHello.sayHello("JHS"), is("HELLO JHS"));
		assertThat(proxiedHello.sayHi("JHS"), is("HI JHS"));
		assertThat(proxiedHello.sayThankYou("JHS"), is("THANK YOU JHS"));
	}
	
	static class UppercaseAdvice implements MethodInterceptor {

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			String ret = (String) invocation.proceed();
			return ret.toUpperCase();
		}
		
	}

}