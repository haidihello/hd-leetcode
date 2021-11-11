package com.leetcode.editor.cn;
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 2016 👎 0

import java.util.List;

/**
*@author haidi
*2021-11-10 17:32:37
*/
class MergeTwoSortedLists{
	public static void main(String[] args) {
		Solution solution = new MergeTwoSortedLists().new Solution();
		ListNode l1 = new ListNode(0);
		ListNode l2 = new ListNode(2);
		System.out.println(solution.mergeTwoLists(l1,l2));


	}
 // Definition for singly-linked list.
  public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
	//leetcode submit region begin(Prohibit modification and deletion)
//	class Solution {
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//		if (l1 == null) {
//			return l1;
//		}
//		if (l2 == null) {
//			return l2;
//		}
//		ListNode sentinel = new ListNode(0);
//		ListNode result = sentinel;
//		merge(l1,l2,sentinel);
//		return result.next;
//    }
//
//	public void merge(ListNode l1, ListNode l2,ListNode result) {
//		if (l1 == null) {
//			result.next = l2;
//			return;
//		}
//		if (l2 == null) {
//			result.next = l1;
//			return;
//		}
//		if (l1.val < l2.val) {
//			result.next = new ListNode(l1.val);
//			l1 = l1.next;
//		} else {
//			result.next = new ListNode(l2.val);
//			l2 = l2.next;
//		}
//		result = result.next;
//		merge(l1, l2, result);
//
//	}
//}
	class Solution {
		public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
			if (l1 == null) {
				return l2;
			}
			if (l2 == null) {
				return l1;
			}
			if (l1.val < l2.val) {
				l1.next = mergeTwoLists(l1.next, l2);
				return l1;
			} else {
				l2.next = mergeTwoLists(l1, l2.next);
				return l2;
			}
		}

	}
//leetcode submit region end(Prohibit modification and deletion)

}
	