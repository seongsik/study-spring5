package com.sik.study.spring5.ch04.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        assert(context != null);
        logger.info("Search Beans...");

        Arrays.stream(context.getBeanDefinitionNames()).forEach(logger::info);

        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        helloWorld.sayHi();

        System.in.read();
        context.close();
    }
}
