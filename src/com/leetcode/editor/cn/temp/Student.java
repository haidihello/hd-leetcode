package com.leetcode.editor.cn.temp;

/**
 * @Author: HaiDi
 * @Date: 2022/3/2 12:16
 */
public class Student {
    private Long num;
    private String name;
    private Integer age;
    private String address;

    public Student(Long num, String name, Integer age, String address) {
        this.num = num;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
