package com.sik.study.spring5.ch05.aop.pointcut;

import com.sik.study.spring5.ch05.aop.SampleBean;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("Static check for + " + method.getName());
        return ("foo".equals(method.getName()));
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("Dynamic check for + " + method.getName());

        int x = ((Integer) args[0]).intValue();

        // 100 이 아닌 경우에만 어드바이스가 적용
        return (x != 100);
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> (clazz == SampleBean.class);
    }
}
