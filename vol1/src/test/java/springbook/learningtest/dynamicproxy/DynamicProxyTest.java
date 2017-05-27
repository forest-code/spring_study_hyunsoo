package springbook.learningtest.dynamicproxy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class DynamicProxyTest {

	static class UppercaseAdvice implements MethodInterceptor {

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			String ret = (String) invocation.proceed();
			return ret.toUpperCase();
		}

	}

	@Test
	public void simpleProxy() {
		Hello proxiedHello = (Hello) Proxy.newProxyInstance(
				getClass().getClassLoader(), 
				new Class[] { Hello.class },
				new UppercaseHandler(new HelloTarget()));

		assertThat(proxiedHello.sayHello("hyunsoo"), is("HELLO HYUNSOO"));
		assertThat(proxiedHello.sayHi("hyunsoo"), is("HI HYUNSOO"));
		assertThat(proxiedHello.sayThankYou("hyunsoo"), is("THANK YOU HYUNSOO"));
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

	/* Advice(MethodInterceptor)와 PointCut(Name Pattern)을 모두 이용하는 학습용 테스트 */
	@Test
	public void pointcutAdvisor() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());

		// 포인트컷 : NameNatchMethodPointcut (mappedName 프로퍼티 값을 이용해 메소드의 이름을 비교하여 대상을 선정)
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayH*");

		// Advisor : 포인트컷과 어드바이스를 묶어 함께 추가
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

		Hello proxiedHello = (Hello) pfBean.getObject();

		assertThat(proxiedHello.sayHello("hyunto"), is("HELLO HYUNTO"));
		assertThat(proxiedHello.sayHi("hyunto"), is("HI HYUNTO"));
		assertThat(proxiedHello.sayThankYou("hyunto"), is("Thank You hyunto"));	// 포인트컷에 의해 UpperCase 부가기능이 적용되지 않는다.
	}

	/* Class Filter와 Method Matcher를 모두 사용하는 PointCut을 확인하기 위한 테스 */
	@Test
	public void classNamePointcutAdvisor() {
		// 포인트컷 준비
		NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut() {
			public ClassFilter getClassFilter() {
				return new ClassFilter() {
					public boolean matches(Class<?> clazz) {
						return clazz.getSimpleName().startsWith("HelloT");
					}
				};
			}
		};
		classMethodPointcut.setMappedName("sayH*");
		
		// 테스트
		checkAdviced(new HelloTarget(), classMethodPointcut, true);	// 포인트컷에 적용되는 테스트
		class HelloWorld extends HelloTarget {};
		checkAdviced(new HelloWorld(), classMethodPointcut, false);	// 포인트컷에 적용되지 않는 테스트
		class HelloToby extends HelloTarget {};
		checkAdviced(new HelloToby(), classMethodPointcut, true);	// 포인트컷에 적용되는 테스트
	}
	
	private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(target);
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
		Hello proxiedHello = (Hello) pfBean.getObject();
		
		if (adviced) {
			assertThat(proxiedHello.sayHello("Hyunsoo"), is("HELLO HYUNSOO"));
			assertThat(proxiedHello.sayHi("Hyunsoo"), is("HI HYUNSOO"));
			assertThat(proxiedHello.sayThankYou("Hyunsoo"), is("Thank You Hyunsoo"));
		} else {
			assertThat(proxiedHello.sayHello("Hyunsoo"), is("Hello Hyunsoo"));
			assertThat(proxiedHello.sayHi("Hyunsoo"), is("Hi Hyunsoo"));
			assertThat(proxiedHello.sayThankYou("Hyunsoo"), is("Thank You Hyunsoo"));
		}
	}
}
