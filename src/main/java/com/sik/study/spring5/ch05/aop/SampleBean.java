package com.sik.study.spring5.ch05.aop;

public class SampleBean {
    public void foo(int x) {
        System.out.println("Invoked foo() with " + x);
    }

    public void bar() {
        System.out.println("Invoked bar()");
    }
}
