package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// @RequiredArgsConstructor // final 필드만 생성자 생성
@Getter
@Setter
public class hellolombok {
    private String hello;
    private int lombok;

    // private final String hello2;

    private final String hello3;

    public hellolombok(String hello3) {
        this.hello3 = hello;
    }
   /* public static void main(String[] args) {
        hellolombok hl = new hellolombok("hi");
        hl.setHello("Hello, lombok!");
        System.out.println(hl.getHello());
    }*/
}
