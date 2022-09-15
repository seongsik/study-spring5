package com.sik.study.spring5.ch05.aop;

import com.sik.study.spring5.ch02.common.Guitar;
import com.sik.study.spring5.ch02.common.Singer;

public class GrammyGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("sing : Gravity is working against me");
    }

    public void sing(Guitar guitar) {
        System.out.println("play : " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }

    public void talk() {
        System.out.println("talk");
    }
}
