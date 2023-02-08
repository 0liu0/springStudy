package com.liuche;

import com.liuche.proxy.StartAgentProxy;
import com.liuche.stars.Star;
import com.liuche.stars.impl.StarImpl;

public class Main {
    public static void main(String[] args) {
        Star star = new StarImpl();
        // TODO 直接调用法
        star.dance();
        star.sing();
        // TODO 使用代理类调用方法
        System.out.println("=================================");
        Star s = StartAgentProxy.getStar(star);
        s.sing();
        s.dance();
    }
}
