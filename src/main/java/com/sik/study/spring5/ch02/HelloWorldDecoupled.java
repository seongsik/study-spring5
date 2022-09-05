package com.sik.study.spring5.ch02;

import com.sik.study.spring5.ch02.decoupled.HelloWorldMessageProvider;
import com.sik.study.spring5.ch02.decoupled.MessageProvider;
import com.sik.study.spring5.ch02.decoupled.MessageRenderer;
import com.sik.study.spring5.ch02.decoupled.StandardOutMessageRenderer;

/**
 * 잔여 문제점
 * : MessageRenderer 나 MessageProvider 인터페이스 구현체를 변경하려면 코드를 변경해야 한다.
 * 이를 피하기 위해 애플리케이션 실행 도중 속성 파일에서 구현 클래스 이름을 읽어 인스턴스로 만드는 팩터리 클래스를 생성한다.
 */
public class HelloWorldDecoupled {
    public static void main(String[] args) {
        // 다형성.
        // Application Logic 에서는 interface 가 제공하는 메서드만 사용해 상호작용해야 한다.
        MessageRenderer mr = new StandardOutMessageRenderer();
        MessageProvider mp = new HelloWorldMessageProvider();

        mr.setMessageProvider(mp);
        mr.render();
    }
}
