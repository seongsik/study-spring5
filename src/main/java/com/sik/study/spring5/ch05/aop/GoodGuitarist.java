package com.sik.study.spring5.ch05.aop;

import com.sik.study.spring5.ch02.common.Singer;

public class GoodGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Who says I can't be free");
    }
}

