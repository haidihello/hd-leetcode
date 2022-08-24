package com.leetcode.editor.newfeature;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class NewFeature {
    public static void showList(String value) {
        System.out.println(value);
    }
    //快速排序
    


    @Test
    public void listTest() {
        //1 遍历
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("abc");
        list.add("abc");
        //使用lambda表达式输出list中的每个值
        System.out.println("--------1.遍历---------");
        list.forEach(c -> {
            System.out.println(c);
        });
        //调用方法
        list.forEach(NewFeature::showList);
        System.out.println("------------");
        //2.筛选
        System.out.println("-------2.筛选--------");
        List<String> selectResult = list.stream().filter(value -> {
            return value.startsWith("a");
        }).collect(Collectors.toList());
        System.out.println(selectResult);
        //3.筛选并去重
        System.out.println("---3.筛选并去重---");
        List<String> selectResult3 = list.stream().filter(value -> {
            return value.startsWith("a");
        }).distinct().collect(Collectors.toList());
        System.out.println(selectResult3);
        //4.截断流取出前n个元素
        System.out.println("----------4.截断流取出前n个元素-------------");
        List<String> selectResult4 = list.stream().filter(value -> {
            return value.startsWith("a");
        }).limit(3).collect(Collectors.toList());
        System.out.println(selectResult4);
        //5.取最后一个元素
        List<String> selectResult5 = list.stream().filter(value -> {
                    return value.startsWith("a");
                }
        ).skip(1).collect(Collectors.toList());
        //5.跳过元素，它和limit是互补的
        List<String> list6 = new ArrayList<>();
        list6.add("aaa");
        list6.add("aaa");
        list6.add("absc");
        list6.add("bbb");
        System.out.println("------------5.跳过元素，它和limit是互补的---------------");
        List<String> selectResult6 = list6.stream().filter(value -> {
            return value.startsWith("a");
        }).skip(3).collect(Collectors.toList());
        System.out.println(selectResult5);
    }
    //list 按照某一字段去重
//    list = accountVoucherList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(AccountVoucherEntity::getAccountNo))), ArrayList::new));



    public static void main(String[] args) {
        String str = "2022年08月03日";

        System.out.println(str.replaceAll("^[0-9]",""));
    }
}


