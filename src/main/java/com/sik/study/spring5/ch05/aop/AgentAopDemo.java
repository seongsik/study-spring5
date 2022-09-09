package com.sik.study.spring5.ch05.aop;

import org.springframework.aop.framework.ProxyFactory;

public class AgentAopDemo {

    public static void main(String[] args) {
        Agent target = new Agent();

        // ProxyFactory 클래스를 사용해 대상 객체의 프록시를 생성과 동시에 위빙.
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new AgentDecorator());     // 어드바이스 전달
        pf.setTarget(target);                   // 위빙 대상을 지정
        Agent proxy = (Agent) pf.getProxy();    // 프록시 객체 생성

        proxy.speak();                          // 프록시 객체에서 speak 호출
    }
}
