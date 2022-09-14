package com.sik.study.spring5.ch05.aop.profiling;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(invocation.getMethod().getName());

        Object returnValue = invocation.proceed();

        sw.stop();
        dumpInfo(invocation, sw.getTotalTimeMillis());
        return returnValue;
    }

    private void dumpInfo(MethodInvocation invocation, long totalTimeMillis) {
        Method m = invocation.getMethod();
        Object target = invocation.getThis();
        Object args = invocation.getArguments();

        System.out.println("Method : " + m.getName());
        System.out.println("Object Type : " + target.getClass().getName());
        System.out.println("Arguments : " + args);

        System.out.println("\n");
        System.out.println("Spend Time : " + totalTimeMillis + " ms");
    }


}
