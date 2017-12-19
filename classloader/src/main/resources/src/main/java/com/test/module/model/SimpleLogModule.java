package com.test.module.model;

public class SimpleLogModule implements Module {
    @Override
    public void run() {
        System.out.println("Hello, it's SimpleLogModule");
    }
}
