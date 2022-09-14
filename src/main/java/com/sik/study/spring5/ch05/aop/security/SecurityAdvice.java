package com.sik.study.spring5.ch05.aop.security;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {

    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo user = securityManager.getLoggedOnUser();

        if(user == null) {
            System.out.println("unauthorized user");
            throw new SecurityException("you must do login");
        } else if("John".equals(user.getUserName())) {
            System.out.println("John user logged in.");
        } else {
            System.out.println(user.getUserName() + " user logged in.");
            throw new SecurityException("NOT HAVE AUTH");
        }
    }
}
