package com.sik.study.spring5.ch05.aop.pointcut;

import com.sik.study.spring5.ch02.common.Guitar;
import com.sik.study.spring5.ch05.aop.Guitarist;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        Guitarist guitarist = new Guitarist();

        AnnotationMatchingPointcut pc = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(guitarist);
        pf.addAdvisor(advisor);

        Guitarist proxy = (Guitarist) pf.getProxy();

        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
    }
}

/*
Sing Sing gogo
>> Invoking sing
play : G C G C Am D7
>> Done
zzz
* */
