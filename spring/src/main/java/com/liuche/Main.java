package com.liuche;

import com.liuche.config.SpringConfig;
import com.liuche.service.Demo;
import com.liuche.service.HelloService;
import com.liuche.service.UserService;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringConfig.class);
        applicationContext.refresh();

//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition(); // BeanDefinitionBuilder创造一个bean对象
//        beanDefinition.setBeanClass(HelloService.class);
//        applicationContext.registerBeanDefinition("helloService",beanDefinition);

//        Demo demo = (Demo) applicationContext.getBean("demo");
        UserService userService = (UserService) applicationContext.getBean("userService");
//        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
//        demo.test01();
        userService.test01();
//        helloService.test01();
        System.out.println("================");
        HelloService helloService = (HelloService) applicationContext.getBean("shasha");
        helloService.test01();
    }
}
