package com.liuche.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UserService implements ApplicationContextAware { // ApplicationContextAware这个接口可以将不是spring容器的类给他这个能力让她使用applicationContext的功能
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public void test01() {
        System.out.println("hello 我是UserService哦！！！");
        Demo demo = (Demo) applicationContext.getBean("demo");
        demo.test01();
    }
}
