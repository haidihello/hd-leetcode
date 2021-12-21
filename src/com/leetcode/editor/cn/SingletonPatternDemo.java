package com.leetcode.editor.cn;

/**
 * @Author: HaiDi
 * @Date: 2021/11/23 20:19
 */
public class SingletonPatternDemo {


    public static void main(String[] args) {
//        SingleObject singleObject = new SingleObject();
        //获取唯一的可用对象
        SingleObject object = SingleObject.getInstance();
        object.showMessage();
    }

}
