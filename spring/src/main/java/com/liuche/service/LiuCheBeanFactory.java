package com.liuche.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("shasha")
public class LiuCheBeanFactory implements FactoryBean { // 用beanFactory创造Bean
    @Override
    public Object getObject() throws Exception {
        return new HelloService();
    }

    @Override
    public Class<?> getObjectType() {
        return HelloService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
