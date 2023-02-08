package com.liuche;

import com.liuche.annotation.Book;
import com.liuche.annotation.MyTest;
import com.liuche.pojo.Store;
import com.liuche.pojo.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        test01(); // 获取注解里的信息 （快速上手）
        test02(); // 模拟junit框架
    }

    public static void test01() {
        // 得到class对象
        Class<Store> store = Store.class;
        // 判断是否有Book注解
        boolean annotationPresent = store.isAnnotationPresent(Book.class);
        // 如果存在
        if (annotationPresent) {
            // 直接获取该注解对象
            Book annotation = store.getDeclaredAnnotation(Book.class);
            System.out.println("书的名称：" + annotation.name() + "；书的作者：" + Arrays.toString(annotation.authors()) +
                    "；书的价格：" + annotation.price() + "元");
        }
        // 得到所有方法
        Method[] Methods = store.getDeclaredMethods();
        for (Method method : Methods) {
            if (method.isAnnotationPresent(Book.class)) {
                Book annotation = method.getDeclaredAnnotation(Book.class);
                System.out.println("书的名称：" + annotation.name() + "；书的作者：" + Arrays.toString(annotation.authors()) +
                        "；书的价格：" + annotation.price() + "元");
            }
        }
    }

    private static void test02() throws Exception {
        Class<Test> aClass = Test.class;
        Test test = aClass.newInstance();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTest.class)) {
                method.invoke(test);
            }
        }
    }

}
