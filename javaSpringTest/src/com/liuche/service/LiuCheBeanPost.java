package com.liuche.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class LiuCheBeanPost implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String name) {
        if (name.equals("userService")){
            // 实现动态代理

            return Proxy.newProxyInstance(LiuCheBeanPost.class.getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                System.out.println("这就是切面逻辑");
                return method.invoke(bean, args);
            }); // 返回这个动态代理对象
        }
//        System.out.println("初始化后"+bean.getClass());
        return bean;
    }
}
