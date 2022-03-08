package com.leetcode.editor.java.daili;

public class UserDaoProxy implements IUserDao {
    private IUserDao target;
    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }
    @Override
    public void save() {
        System.out.println("------------开始事务------------------");
        target.save();
        System.out.println("---------------结束事务-----------------");
    }

    @Override
    public void delete() {
    }
}
