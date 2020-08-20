package com.huaching.xa.campus.code_generator;

public class Main {

    public static void main(String[] args) {
        GenComp genComp = new GenComp("192.168.0.200","3306");
        genComp.generatorCode();
    }
}
