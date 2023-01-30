package com.leetcode.editor.java.daili;

public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("------保存事务-------");
    }

    @Override
    public void delete() {
        System.out.println("----删除完成----");
    }
}
