package com.leetcode.editor.java.base;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: HaiDi
 * @Date: 2023/4/27 17:40
 */
public class Objectquote {
    public static void main(String[] args) {
        String[] arr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        List list = Arrays.asList(arr);
        list.add("0");
        System.out.println(Arrays.toString(arr));

    }
}
