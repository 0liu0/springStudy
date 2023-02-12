package com.spring;

import javax.swing.*;

// TODO 用来描述spring中每个bean的类型
public class BeanDefinition {
    private Class type; // class的类型
    private String scope; // bean的作用域
    private boolean isLazy; // bean是否为懒加载

    public BeanDefinition() {
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "type=" + type +
                ", scope='" + scope + '\'' +
                ", isLazy=" + isLazy +
                '}';
    }
}
