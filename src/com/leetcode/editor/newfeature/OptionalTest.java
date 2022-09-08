package com.leetcode.editor.newfeature;

import com.leetcode.editor.java.test.Personal;
import com.leetcode.editor.java.test.Students;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: HaiDi
 * @Date: 2022/7/29 15:17
 */
public class OptionalTest {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(16);
        students.add(new Student("1", "张三", 18, "male", 88));
        students.add(new Student("1", "张四", 17, "male", 88));
        students.add(new Student("2", "李四", 17, "male", 60));
        students.add(new Student("3", "王五", 18, "male", 100));
        students.add(new Student("4", "赵六", 20, "male", 10));
        students.add(new Student("5", "董七", 14, "female", 95));
        students.add(new Student("6", "幺八", 21, "male", 55));
        students.add(new Student("7", "老九", 20, "female", 66));
        students.add(new Student("8", "小明", 18, "male", 100));
        students.add(new Student("9", "小红", 22, "female", 95));
        students.add(new Student("10", "小张", 25, "male", 90));

        Personal personal = new Personal();
        personal = null;
//        String rspCode =  Optional.ofNullable(jsonObject)
//            .flatMap(js -> Optional.ofNullable(js.getString("datae"))).orElse(new String());

        Optional<Personal> optional = Optional.ofNullable(personal);
        String result = Optional.ofNullable(personal)
                .filter(a -> a.getAge() > 5)
                .filter(a -> a.getName().isEmpty())
                .map(c -> c.getName())
                .orElse("defelt");
        System.out.println(result);

    }
}
