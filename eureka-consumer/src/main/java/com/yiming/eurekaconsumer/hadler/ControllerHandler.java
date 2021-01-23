package com.yiming.eurekaconsumer.hadler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/20 12:06
 */
@Aspect
//@Component
public class ControllerHandler {

    @Pointcut("execution(public String com.yiming.eurekaconsumer.controller.BalanceController.getLocation(..))")
    public void myPoint() {
    }

    /**
     * 环绕通知是spring中功能最强大的通知
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("myPoint()")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        String name = proceedingJoinPoint.getSignature().getName();
        Object proceed = null;
        try {
            System.out.println("环绕前置通知:" + name + "方法开始，参数是" + Arrays.asList(args));
            //利用反射调用目标方法，就是method.invoke()
            proceed = proceedingJoinPoint.proceed(args);
            System.out.println("环绕返回通知:" + name + "方法返回，返回值是" + proceed);
        } catch (Throwable e) {
            System.out.println("环绕异常通知" + name + "方法出现异常，异常信息是：" + e);
        } finally {
            System.out.println("环绕后置通知" + name + "方法结束");
        }
        return proceed;
    }

}
