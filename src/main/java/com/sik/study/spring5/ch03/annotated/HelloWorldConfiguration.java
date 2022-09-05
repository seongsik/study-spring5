package com.sik.study.spring5.ch03.annotated;

import com.sik.study.spring5.ch02.decoupled.HelloWorldMessageProvider;
import com.sik.study.spring5.ch02.decoupled.MessageProvider;
import com.sik.study.spring5.ch02.decoupled.MessageRenderer;
import com.sik.study.spring5.ch02.decoupled.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.sik.study.spring5.ch03.annotated"})
@Configuration
public class HelloWorldConfiguration {

}
