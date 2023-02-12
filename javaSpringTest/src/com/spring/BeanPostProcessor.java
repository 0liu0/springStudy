package com.spring;

public interface BeanPostProcessor {
    default Object postProcessBeforeInitialization(Object bean,String name){
        return bean;
    }
    default Object postProcessAfterInitialization(Object bean,String name){
        return bean;
    }
}
