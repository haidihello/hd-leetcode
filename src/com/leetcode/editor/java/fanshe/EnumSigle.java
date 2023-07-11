package com.leetcode.editor.java.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum  EnumSigle {
    RED;
    public EnumSigle getInstance(){
        return RED;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSigle red = EnumSigle.RED;
        Constructor<EnumSigle> declaredConstructor = EnumSigle.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        EnumSigle enumSigle = declaredConstructor.newInstance();
        System.out.println(red);
        System.out.println(enumSigle);


    }
}