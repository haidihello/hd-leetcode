package com.leetcode.editor.java.daili.jdk;

public class RealObject implements TestServiceA, TestServiceB {

    @Override
    public void executeTestA() {
        System.out.println("Test A execute.");
    }

    @Override
    public void submitTestA() {
        System.out.println("Test A submit.");
    }

    @Override
    public void executeTestB() {
        System.out.println("Test B execute.");
    }

    @Override
    public void submitTestB() {
        System.out.println("Test B submit.");
    }

}