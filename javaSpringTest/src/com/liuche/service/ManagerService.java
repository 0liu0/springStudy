package com.liuche.service;

import com.spring.Autowired;
import com.spring.Component;
import com.spring.Scope;

@Component
public class ManagerService {
    @Autowired
    private Service service;
    public void Test01() {
        System.out.println("这是ManagerService的Test类！！！");
        service.test();
    }
}
