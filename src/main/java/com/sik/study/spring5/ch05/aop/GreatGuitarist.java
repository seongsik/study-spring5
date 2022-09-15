package com.sik.study.spring5.ch05.aop;

import com.sik.study.spring5.ch02.common.Singer;

public class GreatGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("I shot the sheriff");
    }
}
