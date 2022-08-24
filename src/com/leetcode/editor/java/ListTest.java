package com.leetcode.editor.java;

import com.leetcode.editor.java.test.Students;
import com.leetcode.editor.newfeature.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 两个 不同类型的list取差集
 * @Author: HaiDi
 * @Date: 2022/8/19 10:09
 */
public class ListTest {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(16);
        Student student = new Student("1", "张三", 18, "male", 88);
        list.add(student);
        list.add(new Student("1", "张四", 17, "male", 88));
        list.add(new Student("2", "李四", 17, "male", 60));
        list.add(new Student("3", "王五", 18, "male", 100));
        List<Students> list2 = new ArrayList<>(16);
        list2.add(new Students("张四", "男", 17));
        list2.add(new Students("李四", "男", 17));
        List<Student> resultList = list.stream()
                .filter(item -> !list2.stream().map(e -> e.getName())
                        .collect(Collectors.toList()).contains(item.getName())
                )
                .collect(Collectors.toList());

        for (Student st : resultList) {
            list.remove(st);
        }
//        List<Student> studentList = new ArrayList<>();
//        studentList.add(list.get(0));
//        student.setAge(99);
//
//        list.remove(student);


//        list.remove(0);
        System.out.println(list.size());
    }
}
