package com.liuche.service;

import com.spring.*;

@Component(Value = "userService")
//@Scope(Value = "prototype")
public class UserService implements InitializingBean,UserAction {
    @Autowired
    private ManagerService managerService;
    @Override
    public void afterPropertiesSet() {
        System.out.println("初始化后执行！！！");
    }

    @Override
    public void Test() {
        System.out.println("这是UserService的Test类！！！");
//        managerService.Test01();
//        System.out.println(managerService.getClass());
    }
    public void Test01() {
        System.out.println("这是UserService的Test类！！！");
        managerService.Test01();
    }
}
