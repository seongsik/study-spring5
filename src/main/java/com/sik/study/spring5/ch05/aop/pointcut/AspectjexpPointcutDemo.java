package com.sik.study.spring5.ch05.aop.pointcut;

import com.sik.study.spring5.ch05.aop.Guitarist;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AspectjexpPointcutDemo {
    public static void main(String[] args) {
        Guitarist guitarist = new Guitarist();

        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        // sing 으로 시작하는 모든 메서드에 대해 어드바이스를 적용함.
        pc.setExpression("execution(* sing*(..))");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(guitarist);
        pf.addAdvisor(advisor);

        Guitarist proxy = (Guitarist) pf.getProxy();

        proxy.sing();
    }
}
