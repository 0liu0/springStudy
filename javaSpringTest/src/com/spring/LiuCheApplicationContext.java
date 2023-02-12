package com.spring;

import com.liuche.AppConfig;

import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class LiuCheApplicationContext {
    private final Class configClass;
    private Map<String,BeanDefinition> beanMap = new HashMap<>(); // 将bean的类型保存
    private Map<String,Object> singletonBeans = new HashMap<>(); // 保存里面的单例bean
    private List<BeanPostProcessor> BeanPosts = new ArrayList<>(); // 储存BeanPostProcessor对象实例

    public LiuCheApplicationContext(Class appConfigClass) throws Exception {
        this.configClass = appConfigClass;
        // 扫描里面的类并做一些初识化的操作
        scan(this.configClass);
        // 生成单例bean
        for (Map.Entry<String, BeanDefinition> stringBeanDefinitionEntry : beanMap.entrySet()) {
            BeanDefinition value = stringBeanDefinitionEntry.getValue();
            String scope = value.getScope();
            if (scope.equals("singleton")){ // 鉴定为单例bean
                Class<?> aClass = value.getType();
                // 实例化这个单例bean并装入singletonBeans中
                try {
                    singletonBeans.put(stringBeanDefinitionEntry.getKey(),createBean(aClass));
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private void scan(Class configClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 扫描
        if (configClass.isAnnotationPresent(ComponentScan.class)) { // 看看是否有扫描路径
            ComponentScan declaredAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
            // 得到扫描路径
            String path = declaredAnnotation.Value();
            path = path.replace(".", "/");
            // 得到classLoader
            ClassLoader classLoader = LiuCheApplicationContext.class.getClassLoader();
            // 得到path路径下的所有文件
            URL resource = classLoader.getResource(path); // 得到class文件的路径
            // 得到此路径下的所有文件file
            File file = new File(resource.getFile());
            // 拿到这个路径下的所有文件
            if (file.isDirectory()) {
                // 得到所有具有Component注解的类
                for (File f : file.listFiles()) {
                    // 1、得到每一个class的路径
                    String filePath = path.replace("/", ".") + "." + f.getName().replace(".class", "");
                    // 2、再使用classloader加载这个类的class对象
                    Class<?> aClass = classLoader.loadClass(filePath);
                    // 判断是否有component注解
                    if (aClass.isAnnotationPresent(Component.class)) { // 如果有component注解
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setType(aClass);
                        // 如果检测到有类实现了BeanPostProcessor接口
                        if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                            BeanPostProcessor o = (BeanPostProcessor) aClass.newInstance();
                            BeanPosts.add(o);
                        }
                        // 得到bean的注解对象
                        Component annotation = aClass.getDeclaredAnnotation(Component.class);
                        String beanName = annotation.Value();
                        if (Objects.equals(beanName, "")) {
                            beanName = Introspector.decapitalize(aClass.getSimpleName());
                        }
                        // 判断是否有Scope注解 可能这些bean是prototype（多例）
                        if (aClass.isAnnotationPresent(Scope.class) && aClass.getDeclaredAnnotation(Scope.class).Value().equals("prototype")) { // 如果有scope注解且用户设置为多例
                            // 多例
                            beanDefinition.setScope("prototype");
                        } else {
                            // 单例
                            beanDefinition.setScope("singleton");
                        }
                        // 装入beanMap对象便于以后创建bean
                        beanMap.put(beanName,beanDefinition);
                    }
                }
            }
        }
    }

    public Object getBean(String beanName) {
        if (!beanMap.containsKey(beanName)){ // 如果没有该bean的名称则抛出空指针的错误
            throw new NullPointerException();
        }
        // 得到该bean的beanDefinition
        BeanDefinition beanDefinition = beanMap.get(beanName);
        // 得到该bean的scope 看是单例还是多例bean
        if (beanDefinition.getScope().equals("singleton")) {
            Object bean = singletonBeans.get(beanName);
            if (bean==null) { // 如果此时单例池还来不及创建这个bean
                try {
                    bean = createBean(beanDefinition.getType());
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                singletonBeans.put(beanName,bean);
            }
            return bean; // 从单例池获取值
        }else {
            Class<?> beanClass = beanDefinition.getType();
            // 创建并返回一个bean对象
            try {
                return createBean(beanClass);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private Object createBean(Class c) throws InstantiationException, IllegalAccessException {
        // 通过反射得到c的beanName
        Component annotation = (Component) c.getDeclaredAnnotation(Component.class);
        String beanName = annotation.Value();
        if (beanName.equals("")){
            beanName = Introspector.decapitalize(c.getSimpleName());
        }
        Object instance = c.newInstance();
        // 判断这个bean中的一些属性是否有Autowired注解
        for (Field field : c.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true); // 防止这个属性是私有属性（大多数属性都是私有属性）
                field.set(instance,getBean(field.getName())); // 使这个方法拥有这个bean对象
            }
        }
        // 判断是否有initializingBean这个接口 如果有则执行接口中的方法
        if (instance instanceof InitializingBean){
            // 这个类实现了这个接口
            ((InitializingBean) instance).afterPropertiesSet();
        }


        // 执行beanPostProcessor里面的方法
        for (BeanPostProcessor beanPost : BeanPosts) {
            instance = beanPost.postProcessAfterInitialization(instance, beanName);
        }

        return instance;
    }
}
