package com.liuche;

import com.liuche.pojo.Cat;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class Main {
    public static String classpath;
    public static String method;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("javareflect/src/com/liuche/property.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        classpath = properties.get("classpath").toString(); // 获得地址
        method = properties.get("method").toString(); // 获得方法名称
    }

    public static void main(String[] args) throws Exception {
        // test01(); // 快速入门
        // test02(); // Class.forName和classloader的区别
        // test03(); // 常用的一些api
        test04(); // 获取class对象的几种方法

    }

    public static void test01() throws Exception {
        // 通过反射和配置文件获取加载类何方法并使用 TODO 对于某个类对象内存只生成一次
        Class<?> aClass = Class.forName(classpath);
        Class<?> aClass1 = Class.forName(classpath);
        System.out.println("输出的class对象：" + aClass.hashCode());
        System.out.println("输出的class对象：" + aClass1.hashCode());
        // 通过class得到我需要的类
        Object obj = aClass.newInstance(); // 这里不能直接调用里面的方法，因为这个对象还是Object对象
        // TODO 这个方法默认调用的无参构造，如若类没有则会报错
        System.out.println("obj的运行类型：" + obj.getClass());
        // 得到方法
        Method method1 = aClass.getMethod(method);
        method1.invoke(obj);
        // 得到里面的属性 TODO 因为里面所有的属性都是私有的所以获取不到 1.要用Declared字段的方法 2.declaredField.setAccessible(true);
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            System.out.println(declaredField.getName());
        }
    }

    public static void test02() throws Exception {
        /*
            这两种方法都可以用来对类的加载
            TODO 不同点
            1).Class.forName除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。

            2).而classloader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容，只有在newInstance才会去执行static块。

            3).Class.forName(name,initialize,loader)带参数也可控制是否加载static块。并且只有调用了newInstance()方法采用调用构造函数，创建类的对象。

        * */
        // TODO 1.使用classloader
        ClassLoader classLoader = Main.class.getClassLoader();
        Class<?> aClass01 = classLoader.loadClass(classpath);
        System.out.println(aClass01 + " " + aClass01.hashCode());
        System.out.println(classLoader.getResource("com/liuche/pojo/Cat.class"));
        // TODO 2.使用foName
        Class<?> aClass02 = Class.forName(classpath);
        System.out.println(aClass02 + " " + aClass02.hashCode());
    }
    public static void test03() throws Exception {
        // 返回指定类名 name 的 Class对象
        Class<?> cat = Class.forName(classpath);
        System.out.println(cat.getName());
        // 调用缺省构造函数，返回该Class对象的一个实例
        Object obj = cat.newInstance();
        System.out.println(obj.getClass());
        // 返回此class对象的接口
        Class<?>[] interfaces = cat.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getSimpleName());
        }
        // 返回该类的类加载器
        ClassLoader classLoader = Main.class.getClassLoader();
        Class<?> aClass = classLoader.loadClass(classpath);
        System.out.println(aClass);
        // 返回该对象的父类
        Class<?> superclass = cat.getSuperclass();
        System.out.println(superclass.getSimpleName());

    }
    public static void test04() throws Exception {
        // TODO 获取Class类对象的四种方式
            // class只加载一次所以hashcode相同
        Class<?> aClass01 = Class.forName(classpath);
        System.out.println(aClass01.hashCode());

        Class<Cat> aClass02 = Cat.class;
        System.out.println(aClass02.hashCode());

        Cat cat = new Cat();
        Class<? extends Cat> aClass03 = cat.getClass();
        System.out.println(aClass03.hashCode());

        ClassLoader classLoader = Main.class.getClassLoader();
        Class<?> aClass04 = classLoader.loadClass(classpath);
        System.out.println(aClass04.hashCode());
    }
}
