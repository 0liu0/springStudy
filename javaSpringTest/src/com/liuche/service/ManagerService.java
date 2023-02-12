package com.liuche.service;

import com.spring.Component;
import com.spring.Scope;

@Component
@Scope(Value = "prototype")
public class ManagerService {
    public void Test01() {
        System.out.println("这是ManagerService的Test类！！！");
    }
}
