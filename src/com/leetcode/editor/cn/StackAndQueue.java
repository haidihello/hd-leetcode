package com.leetcode.editor.cn;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 两个栈实现队列
 *
 * @Author: HaiDi
 * @Date: 2023/3/17 17:02
 */
public class StackAndQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();



    public void push() {

    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayBlockingQueue<Integer>(5);
        queue.add(1);
        queue.peek();
        queue.poll();
    }


    }

