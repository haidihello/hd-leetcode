package com.leetcode.editor.newfeature;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class NewFeature {
    public static void showList(String value) {
        System.out.println(value);
    }
    @Test
    public void listTest(){
        //1 遍历
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("abc");
        list.add("abc");
        //使用lambda表达式输出list中的每个值
        System.out.println("--------1.遍历---------");
        list.forEach(c->{
            System.out.println(c);
        });
        //调用方法
        list.forEach(NewFeature::showList);
        System.out.println("------------");
        //2.筛选
        System.out.println("-------2.筛选--------");
        List<String> selectResult = list.stream().filter(value->{
            return value.startsWith("a");
        }).collect(Collectors.toList());
        System.out.println(selectResult);
        //3.筛选并去重
        System.out.println("---3.筛选并去重---");
        List<String> selectResult3 = list.stream().filter(value->{
            return value.startsWith("a");
        }).distinct().collect(Collectors.toList());
        System.out.println(selectResult3);
        //4.截断流取出前n个元素
        System.out.println("----------4.截断流取出前n个元素-------------");
        List<String> selectResult4 = list.stream().filter(value->{
            return value.startsWith("a");
        }).limit(3).collect(Collectors.toList());
        System.out.println(selectResult4);

        //5.跳过元素，它和limit是互补的
        List<String> list5 = new ArrayList<>();
        list.add("aaa");
        list.add("aaa");
        list.add("absc");
        list.add("bbb");
        System.out.println("------------5.跳过元素，它和limit是互补的---------------");
        List<String> selectResult5 = list5.stream().filter(value->{
            return value.startsWith("a");
        }).skip(3).collect(Collectors.toList());
        System.out.println(selectResult5);
    }

}
