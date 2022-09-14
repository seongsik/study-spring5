package com.sik.study.spring5.ch05.aop;

import com.sik.study.spring5.ch02.common.Singer;

public class Guitarist implements Singer {
    private String lyric = "Sing Sing gogo";

    @Override
    public void sing() {
        System.out.println(lyric);
    }
}
