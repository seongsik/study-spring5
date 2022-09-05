package com.sik.study.spring5.ch02.decoupled;

import java.lang.reflect.Constructor;
import java.util.Properties;

public class MessageSupportFactory {
    private static MessageSupportFactory instance;

    private Properties props;
    private MessageRenderer renderer;
    private MessageProvider provider;

    private MessageSupportFactory() {
        props = new Properties();

        try {
            props.load(this.getClass().getResourceAsStream("/ch02.msf.properties"));
            String rendererClassName = props.getProperty("renderer.class");
            String providerClassName = props.getProperty("provider.class");

            // newInstance Deprecated
            Class<?> rendererClass = Class.forName(rendererClassName);
            Constructor<?> rendererConstructor = rendererClass.getConstructor();
            renderer = (MessageRenderer) rendererConstructor.newInstance();

            Class<?> providerClass = Class.forName(providerClassName);
            Constructor<?> providerConstructor = providerClass.getConstructor();
            provider = (MessageProvider) providerConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageRenderer getMessageRenderer() {
        return renderer;
    }

    public MessageProvider getMessageProvider() {
        return provider;
    }
}
