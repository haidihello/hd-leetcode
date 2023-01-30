package com.leetcode.editor.java.daili;

public class Test {
    /*
    静态代理方法
     */
    public static void main(String[] args) {
        IUserDao userDao = new IUserDaoImpl();
        UserDaoProxy proxy = new UserDaoProxy(userDao);
        proxy.save();
    }

    /**
     * 动态代理
     */
    @org.junit.Test
    public void dongtaidaili() {
        //目标对象
        UserDao target = new UserDao();
        System.out.println(target.getClass());
        //给目标对象创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println("-----proxy---"+proxy.getClass());
        proxy.save();
        proxy.delete();
    }
}
