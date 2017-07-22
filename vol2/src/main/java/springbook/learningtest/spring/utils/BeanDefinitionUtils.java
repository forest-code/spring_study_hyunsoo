package springbook.learningtest.spring.utils;

import org.springframework.context.support.GenericApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunsoo0813 on 2017. 7. 23..
 */
public class BeanDefinitionUtils {

    public static void printBeanDefinitions(GenericApplicationContext genericApplicationContext) {
        List<List<String>> roleBeanInfos = new ArrayList<>();
        roleBeanInfos.add(new ArrayList<>());
        roleBeanInfos.add(new ArrayList<>());
        roleBeanInfos.add(new ArrayList<>());

        for (String name : genericApplicationContext.getBeanDefinitionNames()) {
            int role = genericApplicationContext.getBeanDefinition(name).getRole();
            List<String> beanInfos = roleBeanInfos.get(role);
            beanInfos.add(role + "\t" + name + "\t" + genericApplicationContext.getBean(name).getClass().getName());
        }

        System.out.println("----------------------------------------------------------------------------------------");
        for (List<String> beanInfos : roleBeanInfos) {
            for (String beanInfo : beanInfos) {
                System.out.println(beanInfo);
            }
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
