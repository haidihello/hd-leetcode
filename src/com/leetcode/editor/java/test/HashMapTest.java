package com.leetcode.editor.java.test;

import com.alibaba.fastjson2.JSON;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: HaiDi
 * @Date: 2022/7/7 9:46
 */
public class HashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("", "");
    }
    @Test
    public void mapTest() {
        Map map = new HashMap();
        map.put("1", map);
        System.out.println(String.valueOf(map));
        System.out.println(map.toString());
        System.out.println(map.get("1"));
        System.out.println(map);
//        System.out.println(JSON.toJSON(map));

    }
    @Test
    public void listTest() {
        List list = new ArrayList();
        list.add(list);
        System.out.println(list.get(0));
        System.out.println(JSON.toJSON(list));

    }
    @Test
    public void setTest() {
        Set set = new HashSet();
        set.add(set);
        System.out.println(set);
        System.out.println(JSON.toJSON(set));

    }
}
