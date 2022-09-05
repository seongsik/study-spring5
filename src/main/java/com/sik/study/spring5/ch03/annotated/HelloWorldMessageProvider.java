package com.sik.study.spring5.ch03.annotated;

import com.sik.study.spring5.ch02.decoupled.MessageProvider;
import org.springframework.stereotype.Component;

@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider {

    @Override
    public String getMessage() {
        return "Hello World Annotated";
    }
}
