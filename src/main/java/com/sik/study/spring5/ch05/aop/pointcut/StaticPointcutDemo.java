package com.sik.study.spring5.ch05.aop.pointcut;

import com.sik.study.spring5.ch02.common.Singer;
import com.sik.study.spring5.ch05.aop.GoodGuitarist;
import com.sik.study.spring5.ch05.aop.GreatGuitarist;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticPointcutDemo {
    public static void main(String[] args) {
        GoodGuitarist goodGuitarist = new GoodGuitarist();
        GreatGuitarist greatGuitarist = new GreatGuitarist();

        Singer proxy1;
        Singer proxy2;

        Pointcut pc = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(goodGuitarist);
        proxy1 = (Singer) pf.getProxy();

        pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(greatGuitarist);
        proxy2 = (Singer) pf.getProxy();

        proxy1.sing();
        proxy2.sing();
    }

    /*
> Task :StaticPointcutDemo.main()
>> Invoking sing
Who says I can't be free
>> Done
I shot the sheriff
    */
}
