package com.sik.study.spring5.ch05.aop.composable;

import com.sik.study.spring5.ch02.common.Guitar;
import com.sik.study.spring5.ch05.aop.GrammyGuitarist;
import com.sik.study.spring5.ch05.aop.advice.SimpleBeforeAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class ComposablePointcutDemo {
    public static void main(String[] args) {
        GrammyGuitarist guitarist = new GrammyGuitarist();

        ComposablePointcut pc = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        System.out.println("Test > 1");
        GrammyGuitarist proxy = getProxy(pc, guitarist);
        testInvoke(proxy);
        System.out.println();

        System.out.println("Test > 2");
        pc.union(new TalkMethodMatcher());
        proxy = getProxy(pc, guitarist);
        testInvoke(proxy);
        System.out.println();

        System.out.println("Test > 3");
        pc.union(new RestMethodMatcher());
        proxy = getProxy(pc, guitarist);
        testInvoke(proxy);
        System.out.println();


    }


    private static GrammyGuitarist getProxy(ComposablePointcut pc,
                                            GrammyGuitarist target) {
        Advisor advisor = new DefaultPointcutAdvisor(pc,
                new SimpleBeforeAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        return (GrammyGuitarist) pf.getProxy();
    }

    private static void testInvoke(GrammyGuitarist proxy) {
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.talk();
        proxy.rest();
    }

    private static class SingMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> cls) {
            return (method.getName().startsWith("si"));
        }
    }

    private static class TalkMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> cls) {
            return "talk".equals(method.getName());
        }
    }

    private static class RestMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> cls) {
            return (method.getName().endsWith("st"));
        }
    }
}
