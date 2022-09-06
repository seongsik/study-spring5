package com.sik.study.spring5.ch04;

import com.sik.study.spring5.ch04.config.HighshcoolConfig;
import com.sik.study.spring5.ch04.config.KindergartenConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class ProfileJavaConfigDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(KindergartenConfig.class, HighshcoolConfig.class);

        FoodProviderService foodProviderService = context.getBean("foodProviderService", FoodProviderService.class);
        List<Food> lunchSet = foodProviderService.provideLunchSet();

        for(Food food : lunchSet) {
            System.out.println("Food : " + food.getName());
        }

        context.close();
    }
}
