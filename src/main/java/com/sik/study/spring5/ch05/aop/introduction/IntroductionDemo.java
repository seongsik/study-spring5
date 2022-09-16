package com.sik.study.spring5.ch05.aop.introduction;

import com.sik.study.spring5.ch02.Contact;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public class IntroductionDemo {
    public static void main(String[] args) {
        Contact target = new Contact();
        target.setName("john Mayer");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setOptimize(true);

        Contact proxy = (Contact) pf.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("Is Contact : " + (proxy instanceof Contact));
        System.out.println("Is IsModified : " + (proxy instanceof IsModified));
        System.out.println("Modified ? " + proxyInterface.isModified());

        proxy.setName("john Mayer");
        System.out.println("Modified ? " + proxyInterface.isModified());

        proxy.setName("Eric Clapton");
        System.out.println("Modified ? " + proxyInterface.isModified());

    }
}
