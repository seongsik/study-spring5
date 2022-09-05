package com.sik.study.spring5.ch03;

import com.sik.study.spring5.ch02.decoupled.MessageRenderer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        //context.load("classpath:spring/ch03.app-context-xml.xml");          // ch02 와 동일하게 동작.
        context.load("classpath:spring/ch03.app-context-annotation.xml"); // Annotation Component scan 동작
        context.refresh();

        MessageRenderer mr = context.getBean("renderer", MessageRenderer.class);
        mr.render();
        context.close();
    }
}
