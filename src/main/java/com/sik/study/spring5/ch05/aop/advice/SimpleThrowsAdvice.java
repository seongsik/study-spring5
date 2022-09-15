package com.sik.study.spring5.ch05.aop.advice;

import com.sik.study.spring5.ch05.aop.ErrorBean;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleThrowsAdvice implements ThrowsAdvice {
    public static void main(String[] args) {
        ErrorBean errorBean = new ErrorBean();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(errorBean);
        pf.addAdvice(new SimpleThrowsAdvice());

        ErrorBean proxy = (ErrorBean) pf.getProxy();

        try {
            proxy.errorProneMethod();
        } catch (Exception e) {

        }
        try {
            proxy.otherErrorProneMethod();
        } catch (Exception e) {

        }
    }

    public void afterThrowing(Exception e) throws Throwable {
        System.out.println("Generic Exception capture");
    }

    public void afterThrowing(Method method, Object args, Object target, IllegalArgumentException ex) throws Throwable {
        System.out.println("IllegalArgumentException capture");
    }
}
