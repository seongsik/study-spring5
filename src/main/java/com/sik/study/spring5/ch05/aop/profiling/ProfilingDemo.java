package com.sik.study.spring5.ch05.aop.profiling;

import com.sik.study.spring5.ch05.aop.WorkerBean;
import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {
    public static void main(String[] args) {
        WorkerBean bean = getWorkerBean();
        bean.doSomeWork(100);
    }

    private static WorkerBean getWorkerBean() {
        WorkerBean target = new WorkerBean();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(new ProfilingInterceptor());

        return (WorkerBean) pf.getProxy();
    }
}
