package com.leetcode.editor.java.test;

/**
 * @Author: HaiDi
 * @Date: 2022/7/28 15:52
 */
public class Students implements Comparable{
    private String name;
    private String sex;
    private Integer age;

    public Students(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
