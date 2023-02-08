package com.liuche.stars.impl;

import com.liuche.stars.Star;

public class StarImpl implements Star {
    public static final String name = "蔡徐坤";
    @Override
    public void dance() {
        System.out.println(name+" 在跳舞！！！");
    }

    @Override
    public void sing() {
        System.out.println(name+" 在唱歌！！！");
    }
}
