package com.sik.study.spring5.ch04;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class SingerWithJSR250 {

    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    @PostConstruct
    private void init() {
        System.out.println("Bean initialization");

        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(SingerWithJSR250.class + " Bean type must set the age property.");
        }
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:spring/ch04.app-context-xml.xml");
        context.refresh();

        getBean("singerSeven", context);
        getBean("singerEight", context);
        getBean("singerNine", context);

        context.close();
    }

    public static SingerWithJSR250 getBean(String beanName, ApplicationContext context) {
        SingerWithJSR250 bean = null;
        try {
            bean = (SingerWithJSR250) context.getBean(beanName);
            System.out.println(bean.toString());
        } catch (BeanCreationException e) {
            System.out.println("Occur Error in Bean Creation : " + e.getMessage());
        }
        return bean;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
