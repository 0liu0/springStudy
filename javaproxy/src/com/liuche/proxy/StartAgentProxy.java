package com.liuche.proxy;

import com.liuche.stars.Star;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StartAgentProxy {
    /*
        设计一个方法来返回一个明星的代理对象
    * */
    public static <T> T getStar(T star) {
        /*  newProxyInstance的参数
            ClassLoader loader,
            Class<?>[] interfaces
            InvocationHandler h)
        * */
        return (T) Proxy.newProxyInstance(star.getClass().getClassLoader(), star.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("正在收钱……");
                Object result = method.invoke(star);
                System.out.println("正在收摊……");
                return result;
            }
        });
    }
}
