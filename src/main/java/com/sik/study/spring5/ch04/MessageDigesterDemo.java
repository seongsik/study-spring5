package com.sik.study.spring5.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigesterDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:spring/ch04.app-context-xml.xml");
        context.refresh();

        MessageDigester digester = context.getBean("digester", MessageDigester.class);
        digester.digest("Hello World");
        context.close();
    }
}
