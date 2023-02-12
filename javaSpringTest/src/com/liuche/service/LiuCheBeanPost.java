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
            Object proxyInstance = Proxy.newProxyInstance(LiuCheBeanPost.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("这就是切面逻辑");
                    return method.invoke(bean, args);
                }
            });

            return proxyInstance; // 返回这个动态代理对象
        }
//        System.out.println("初始化后"+bean.getClass());
        return null;
    }
}
