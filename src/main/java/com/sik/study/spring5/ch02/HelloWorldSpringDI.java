package com.sik.study.spring5.ch02;

import com.sik.study.spring5.ch02.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSpringDI {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/ch02.app-context.xml");

        MessageRenderer mr = context.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}
