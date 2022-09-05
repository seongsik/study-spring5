package com.sik.study.spring5.ch02.decoupled;

/**
 * 메시지를 가져오는 로직과 분리,
 * MessageProvider 에게 메시지를 가져오는 책임을 위임한다.
 * MessageProvider는 MessageRenderer 에게 의존성이 있다.
 */
public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}
