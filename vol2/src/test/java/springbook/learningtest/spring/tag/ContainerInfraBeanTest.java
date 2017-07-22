package springbook.learningtest.spring.tag;

import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.learningtest.spring.ioc.bean.SimpleConfig;
import springbook.learningtest.spring.utils.BeanDefinitionUtils;

/**
 * Created by hyunsoo0813 on 2017. 7. 23..
 */
public class ContainerInfraBeanTest {

    @Test
    public void component_scan_태그로_어떤빈이_등록되는지_확인() {
        GenericApplicationContext context = new GenericXmlApplicationContext("HelloGenericApplicationContext.xml");
        SimpleConfig sc = context.getBean(SimpleConfig.class);
        sc.hello().print();
        System.out.println(sc.hello().sayHello());

        BeanDefinitionUtils.printBeanDefinitions(context);
    }
}
