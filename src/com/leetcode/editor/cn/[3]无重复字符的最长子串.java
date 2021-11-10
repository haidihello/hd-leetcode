package com.leetcode.editor.cn;
//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 6383 👎 0


import java.util.HashMap;

//2021-11-09 17:11:07
class LongestSubstringWithoutRepeatingCharacters{
	public static void main(String[] args) {
		Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
		System.out.println(solution.lengthOfLongestSubstring("abbdfads"));
	}
	//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
		public int lengthOfLongestSubstring(String s) {
			if (s.length() == 0) {
				return 0;
			}
			HashMap<Character, Integer> map = new HashMap<>();
			int max = 0;
			for (int i = 0, j = 0; i < s.length(); ++i) {
				if (map.containsKey(s.charAt(i))) {
					j = Math.max(j, map.get(s.charAt(i)) + 1);
				}
				map.put(s.charAt(i), i);
				max = Math.max(max, i - j + 1);
			}
			return max;
		}
}
//leetcode submit region end(Prohibit modification and deletion)

}
	