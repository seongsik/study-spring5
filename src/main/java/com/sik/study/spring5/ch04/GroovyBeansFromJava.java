package com.sik.study.spring5.ch04;

import com.sik.study.spring5.ch03.xml.Singer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class GroovyBeansFromJava {
    public static void main(String[] args) {
        ApplicationContext context = new GenericGroovyApplicationContext("classpath:ch04.beans.groovy");
        Singer singer = context.getBean("singer", Singer.class);
        System.out.println(singer);
    }
}
