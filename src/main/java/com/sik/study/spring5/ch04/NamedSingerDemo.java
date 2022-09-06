package com.sik.study.spring5.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

public class NamedSingerDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:spring/ch04.app-context-xml.xml");
        context.refresh();

        NamedSinger singer = (NamedSinger) context.getBean("johnMayer");
        singer.sing();

        context.close();
    }
}
