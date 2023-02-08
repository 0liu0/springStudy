package com.liuche.pojo;

import com.liuche.annotation.MyTest;

public class Test {
    public void test01() {
        System.out.println("==========我是test01方法！！！===========");
    }

    @MyTest
    public void test02() {
        System.out.println("==========我是test02方法！！！===========");
    }

    public void test03() {
        System.out.println("==========我是test03方法！！！===========");
    }

    @MyTest
    public void test04() {
        System.out.println("==========我是test04方法！！！===========");
    }

}
