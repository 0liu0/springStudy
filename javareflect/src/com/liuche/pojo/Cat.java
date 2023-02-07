package com.liuche.pojo;

import com.liuche.interfaces.Act;
import com.liuche.interfaces.Animal;

public class Cat extends Tiger implements Animal, Act {
    static {
        System.out.println("我今天心情真好！！！");
    }
    private String name;
    private int age;

    public Cat() {
        System.out.println("我加载好了");
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("我也加载好了");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void hello() {
        System.out.println("你好，刘帅帅！！！");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
