package com.sik.study.spring5.ch02;

import com.sik.study.spring5.ch02.decoupled.MessageProvider;
import com.sik.study.spring5.ch02.decoupled.MessageRenderer;
import com.sik.study.spring5.ch02.decoupled.MessageSupportFactory;

public class HelloWorldDecoupledWithFactory {
    public static void main(String[] args) {
        MessageRenderer mr = MessageSupportFactory.getInstance().getMessageRenderer();
        MessageProvider mp = MessageSupportFactory.getInstance().getMessageProvider();

        mr.setMessageProvider(mp);
        mr.render();
    }
}
