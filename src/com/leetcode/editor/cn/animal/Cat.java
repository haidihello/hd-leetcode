package com.leetcode.editor.cn.animal;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HaiDi
 * @Date: 2021/11/23 20:37
 */
public class Cat extends Animal {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list = list.subList(0, 3);
        System.out.println(list.subList(0,3));
    }
}
