package springbook.learningtest.spring.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.AppContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = AppContext.class)
public class BeanInfoTest {

	@Autowired
	DefaultListableBeanFactory bf;
	
	@Test
	public void beans() {
		System.out.println("======== 컨테이너의 빈 등록 정보 확인 =========");
		for (String n : bf.getBeanDefinitionNames()) {
			System.out.println(n + " \t " + bf.getBean(n).getClass().getName());
		}
		System.out.println("====================================");
	}
}
