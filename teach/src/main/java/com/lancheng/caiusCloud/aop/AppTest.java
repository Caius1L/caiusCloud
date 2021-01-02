package com.lancheng.caiusCloud.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Boy boy = context.getBean("boy",Boy.class);
        Girl girl = context.getBean("girl",Girl.class);
        boy.buy(90);
//        girl.buy();
    }
}
