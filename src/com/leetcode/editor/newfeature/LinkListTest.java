package com.leetcode.editor.newfeature;

class Node {
    public int val;
   public Node next;

    public Node(int val) {
        this.next = next;
    }
public  class LinkListTest {

    public Node head;

    //反转单链表
    public  Node reverser() {
        //没有节点
        if (this.head == null) {
            return null;
        }
        //一个元素
        if (this.head.next == null) {
            return this.head;
        }
        //
        Node cru = this.head.next;
        this.head.next = null;
        //反转
        while (cru != null) {
            Node cruNext =cru.next;
            cru.next = head;
            this.head = cru;
            //交换位置
            cru = cruNext;
        }
        return head;
    }
}

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);

    }


}
