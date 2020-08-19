package com.huaching.xa.campus.collect.controller;

public class Test {
    private Integer a;


    private Boolean flag;

    Test(Integer a) {
        this.a = a;
        this.flag = false;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}