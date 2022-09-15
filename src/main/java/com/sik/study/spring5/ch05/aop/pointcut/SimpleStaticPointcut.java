package com.sik.study.spring5.ch05.aop.pointcut;

import com.sik.study.spring5.ch05.aop.GoodGuitarist;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return ("sing".equals(method.getName()));
    }

    @Override
    public ClassFilter getClassFilter() {
        // return clazz -> (clazz ==GoodGuitarist.class);

        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return (clazz == GoodGuitarist.class);
            }
        };
    }
}
