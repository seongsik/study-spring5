package com.sik.study.spring5.ch03;

import com.sik.study.spring5.ch02.annotated.HelloWorldConfiguration;
import com.sik.study.spring5.ch02.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldSpringAnnotated {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);

        MessageRenderer mr = context.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}
