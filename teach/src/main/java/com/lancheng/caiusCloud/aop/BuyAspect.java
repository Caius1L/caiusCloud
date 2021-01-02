package com.lancheng.caiusCloud.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BuyAspect {

//    @Pointcut("execution(* com.lancheng.caiusCloud.aop.IBuy.buy(..))")
//    public void point(){}
//
//    @Before("point()")
//    public void before() {
//        System.out.println("前置切面，爷准备买东西了");
//    }
//
//    @After("point()")
//    public void after() {
//        System.out.println("后置切面，爷东西买完了");
//    }
//
//    @Around("point()")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) {
//        try {
//            System.out.println("环绕增强前半部分，我再选，看一看打折分区的游戏");
//            proceedingJoinPoint.proceed();
//            System.out.println("环绕增强后半部分，选完了，选了最后生还者");
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }
//
//    @AfterReturning("point()")
//    public void afterReturn() {
//        System.out.println("付钱");
//    }

    @Pointcut("execution(* com.lancheng.caiusCloud.aop.IBuy.buy(..)) && args(price) && bean(boy)")
    public void args(int price){
    }

    @Around("args(price)")
    public void after(ProceedingJoinPoint proceedingJoinPoint, int price){
        try {
            proceedingJoinPoint.proceed();
            if(price > 50){
                System.out.println("由于价格超过了50，因此赠送一双袜子");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
