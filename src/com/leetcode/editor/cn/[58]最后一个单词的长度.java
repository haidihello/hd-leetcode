package com.leetcode.editor.cn;
//给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。 
//
// 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "Hello World"
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：s = "   fly me   to   the moon  "
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：s = "luffy is still joyboy"
//输出：6
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅有英文字母和空格 ' ' 组成 
// s 中至少存在一个单词 
// 
// Related Topics 字符串 
// 👍 396 👎 0

import java.lang.reflect.Array;

/**
*@author haidi
*2021-11-17 15:56:12
*/
class LengthOfLastWord{
	public static void main(String[] args) {
		Solution solution = new LengthOfLastWord().new Solution();
		System.out.println(solution.lengthOfLastWord("life is fucking movies"));
	}
	//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLastWord(String s) {

//		int end = s.length() - 1;
//		while (end >= 0 && s.charAt(end) == ' ') {
//			end--;
//		}
//		if (end < 0) {
//			return 0;
//		}
//		int start = end;
//		while (start >= 0 && s.charAt(start) != ' ') {
//			start--;
//		}
//		return end - start;
		if (s == null || s.length() == 0) {
			return 0;
		}
		int count = 0;
		for (int i = s.length()-1; i >=0 ; i--) {
			if (s.charAt(i) == ' ') {
				if (count == 0) {
					continue;

				}
				break;
			}
			count++;
		}
		return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
	