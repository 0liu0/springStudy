package com.liuche;

public class Dad {
    private String name;
    protected String nig;
    public Dad(){
        System.out.println("我是Dad无参构造");
    }

    public Dad(String nig) {
        this.nig = nig;
        System.out.println("我是Dad有参构造");
    }
}
