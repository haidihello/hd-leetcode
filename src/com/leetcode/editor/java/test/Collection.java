package com.leetcode.editor.java.test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * @Author: HaiDi
 * @Date: 2022/7/28 15:40
 */
public class Collection {
    public static void main(String[] args) {
        Vector vector = new Vector();
        vector.add("1");
        vector.add("2");
        System.out.println(vector);
        Map<Integer, Object> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        Map map = new TreeMap();
        map.put(5, new Object());
        map.put(10, new Object());
        map.put(8, new Object());
        map.put(12, new Object());
        System.out.println(map);


    }
}
