package com.sik.study.spring5.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

public class MessageSourceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:spring/ch04.app-context-xml.xml");
        context.refresh();

        Locale eng = Locale.ENGLISH;
        Locale kor = new Locale("ko", "KR");

        System.out.println(context.getMessage("msg", null, eng));
        System.out.println(context.getMessage("msg", null, kor));

        context.close();
    }
}
