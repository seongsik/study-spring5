package com.sik.study.spring5.ch02.decoupled;

public class HelloWorldMessageProvider implements MessageProvider {

    @Override
    public String getMessage() {
        return "Hello World";
    }
}
