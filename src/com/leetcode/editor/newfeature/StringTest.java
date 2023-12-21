package com.leetcode.editor.newfeature;

import com.leetcode.editor.java.test.Students;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StringTest {
    @Test
    public void test() {
        //1班的学生
        List<Student> class01=new ArrayList<>();
         class01.add(new Student("1","小明",12,"男",43));
         class01.add(new Student("2","小明",12,"男",44));
        // 2班的学生
         List<Student>class02=new ArrayList<>();
        class02.add(new Student("2","小明",12,"男",43));

//        // 找两个班名字相同的同学(取交集),比较用的是重写的equals()
//        List<Student> sameName =class01.stream().filter(class02::contains).collect(Collectors.toList());
//
        List<Student> resultList = class01.stream()
                .filter(item -> class02.stream().map(e -> e.getId())
                        .collect(Collectors.toList()).contains(item.getId())
                ).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        String ls = map.get("") + "";
        System.out.println(ls);
        System.out.println(map.get("1"));
    }

}
