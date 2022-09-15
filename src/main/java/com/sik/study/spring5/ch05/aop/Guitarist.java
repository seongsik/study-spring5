package com.sik.study.spring5.ch05.aop;

import com.sik.study.spring5.ch02.common.Guitar;
import com.sik.study.spring5.ch02.common.Singer;
import com.sik.study.spring5.ch05.aop.pointcut.AdviceRequired;

public class Guitarist implements Singer {
    private String lyric = "Sing Sing gogo";

    @Override
    public void sing() {
        System.out.println(lyric);
    }

    public void rest() {
        System.out.println("zzz");
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("play : " + guitar.play());
    }
}
