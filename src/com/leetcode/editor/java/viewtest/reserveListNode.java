package com.leetcode.editor.java.viewtest;


/**
 * 反转单链表
 * @Author: HaiDi
 * @Date: 2023/4/18 15:40
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
public class reserveListNode {
    static ListNode reverseList(ListNode head){
        ListNode next =null;
        ListNode pre = null;
        while(head !=null){
            next = head.next;
            head.next =pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1,new ListNode(2,new ListNode(4,new ListNode(3))));

        reverseList(node);

    }

}
