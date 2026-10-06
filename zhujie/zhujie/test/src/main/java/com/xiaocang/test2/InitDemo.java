package com.xiaocang.test2;

public class InitDemo {


    @InitMethod
    public void init() {
        System.out.println("init....");
    }

    @InitMethod
    public void init2() {
        System.out.println("init2....");
    }
}
