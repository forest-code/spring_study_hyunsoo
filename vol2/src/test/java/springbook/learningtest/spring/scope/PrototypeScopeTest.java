package springbook.learningtest.spring.scope;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hyunsoo0813 on 2017. 7. 18..
 */
public class PrototypeScopeTest {

    @Scope("prototype")
    static class PrototypeBean {

    }

    static class PrototypeClientBean {
        @Autowired
        private PrototypeBean bean1;

        @Autowired
        private PrototypeBean bean2;
    }

    @Test
    public void prototypeScope() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, PrototypeClientBean.class);
        Set<PrototypeBean> bean = new HashSet<>();

        bean.add(ac.getBean(PrototypeBean.class));
        assertThat(bean.size(), is(1));

        bean.add(ac.getBean(PrototypeBean.class));
        assertThat(bean.size(), is(2));

        bean.add(ac.getBean(PrototypeClientBean.class).bean1);
        assertThat(bean.size(), is(3));

        bean.add(ac.getBean(PrototypeClientBean.class).bean2);
        assertThat(bean.size(), is(4));
    }

}
