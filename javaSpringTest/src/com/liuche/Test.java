package com.liuche;

import com.liuche.service.Service;
import com.liuche.service.UserAction;
import com.liuche.service.UserService;
import com.spring.LiuCheApplicationContext;


public class Test {
    public static void main(String[] args) throws Exception {
        // 创建单例bean，扫描
        LiuCheApplicationContext applicationContext = new LiuCheApplicationContext(AppConfig.class);
        UserAction userServiceBean = (UserAction) applicationContext.getBean("userService");
        userServiceBean.Test();
//        Service service = (Service) applicationContext.getBean("service");
//        service.test();
    }
}
