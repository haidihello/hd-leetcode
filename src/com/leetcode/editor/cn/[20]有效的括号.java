package com.leetcode.editor.cn;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2751 👎 0

import java.util.Deque;
import java.util.LinkedList;

/**
*@author haidi
*2021-11-10 16:59:09
*/
class ValidParentheses{
	public static void main(String[] args) {
		Solution solution = new ValidParentheses().new Solution();
		System.out.println(solution.isValid("()"));
	}
	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {
		public boolean isValid(String s) {
			if (s.isEmpty() || s.length() % 2 == 1) {
				return false;
			}
			char[] c = s.toCharArray();
			//存放括号
			Deque<Character> deque = new LinkedList<>();
			for (int i = 0; i <c.length ; i++) {
				if (c[i] == ')') {
					if (deque.isEmpty() || deque.pollLast() != '(') {
						return false;
					}
				}else if (c[i] ==']') {
					if (deque.isEmpty() || deque.pollLast() != '[') {
						return false;
					}
				} else if (c[i] == '}') {
					if (deque.isEmpty() || deque.pollLast() != '{') {
						return false;
					}
				} else {
					deque.add(c[i]);
				}
			}
			return deque.isEmpty();
		}
	}
//leetcode submit region end(Prohibit modification and deletion)

}
	