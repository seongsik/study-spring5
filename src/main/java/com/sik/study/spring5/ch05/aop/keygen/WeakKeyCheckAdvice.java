package com.sik.study.spring5.ch05.aop.keygen;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class WeakKeyCheckAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if((target instanceof KeyGenerator) && ("getKey".equals(method.getName()))) {
            long key = ((Long) returnValue).longValue();

            if(key == KeyGenerator.WEAK_KEY) {
                throw new SecurityException("Generated Weak Key. ");
            }
        }
    }
}
