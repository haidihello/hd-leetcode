package com.leetcode.editor.java.test;

import cn.hutool.core.date.DateTime;

import java.util.Date;

/**
 * @author HaiDi
 * @since 2025-04-07 16:43
 */
public class TimeTest {
    public static void main(String[] args) {

        Date date = DateTime.of("2023-04-07","yyyy-MM-dd").toJdkDate();
        if (date.getTime() < System.currentTimeMillis()
        ) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
