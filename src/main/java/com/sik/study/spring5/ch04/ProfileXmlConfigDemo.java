package com.sik.study.spring5.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class ProfileXmlConfigDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:spring/ch04.*-config.xml");
        context.refresh();

        FoodProviderService foodProviderService = context.getBean("foodProviderService", FoodProviderService.class);
        List<Food> lunchSet = foodProviderService.provideLunchSet();

        for(Food food : lunchSet) {
            System.out.println("Food : " + food.getName());
        }

        context.close();
    }
}
