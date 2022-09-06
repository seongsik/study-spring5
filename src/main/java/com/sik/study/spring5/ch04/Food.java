package com.sik.study.spring5.ch04;

import lombok.Getter;
import lombok.Setter;

public class Food {
    private String name;

    public Food() {

    }

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
