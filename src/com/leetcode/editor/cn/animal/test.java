package com.leetcode.editor.cn.animal;

/**
 * @Author: HaiDi
 * @Date: 2021/11/23 20:40
 */
public class test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> aClass = Class.forName("com.leetcode.editor.cn.animal.Cat");
        Cat o = (Cat)aClass.newInstance();
//        System.out.println(o);
//        System.out.println(aClass);
        System.out.println(getAnimal("Cat"));
        System.out.println(getAnimal("Dog"));

    }

    static Animal getAnimal(String key) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.leetcode.editor.cn.animal."+ key);
        Animal o = (Animal) aClass.newInstance();
        return o;
    }

}
