package com.sik.study.spring5.ch05.aop.keygen;

import java.util.Random;

public class KeyGenerator {
    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_KEY = 0xACDF03F590AE56L;

    private Random random = new Random();

    public long getKey() {
        int x = random.nextInt();
        if(x < 1) return WEAK_KEY;

        return STRONG_KEY;
    }
}
