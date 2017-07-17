package springbook.learningtest.spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.learningtest.spring.ioc.bean.AnnotatedHello;
import springbook.learningtest.spring.ioc.bean.AnnotatedHelloConfig;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by hyunsoo0813 on 2017. 7. 14..
 */
public class AnnotatedHelloTest {

    @Test
    public void simpleBeanScanning() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("springbook.learningtest.spring.ioc.bean");
//        AnnotatedHello hello = ctx.getBean("annotatedHello", AnnotatedHello.class);
        AnnotatedHello hello = ctx.getBean(AnnotatedHello.class);

        assertNotNull(hello);
    }

    @Test
    public void configurationAnnotation() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotatedHelloConfig.class);
        AnnotatedHello hello = ctx.getBean("annotatedHello", AnnotatedHello.class);
        assertNotNull(hello);

        AnnotatedHelloConfig config = ctx.getBean("annotatedHelloConfig", AnnotatedHelloConfig.class);
        assertNotNull(config);

        assertThat(config.annotatedHello(), is(sameInstance(hello)));
    }

}
