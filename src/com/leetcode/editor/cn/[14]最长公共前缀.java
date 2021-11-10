package com.leetcode.editor.cn;
//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1852 👎 0


//2021-11-10 10:17:48
class LongestCommonPrefix{
	public static void main(String[] args) {
		Solution solution = new LongestCommonPrefix().new Solution();
		String[] strs = {"abcd","abab","anbacdb"};
		System.out.println(solution.longestCommonPrefix(strs));
	}
	//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {

		if (strs.length<=0 || strs ==null) {
			return "";
		}
		String res = strs[0];
		int i = 0;
		while (i < strs.length) {
			while (strs[i].indexOf(res) != 0) {
				res = res.substring(0, res.length() - 1);
			}
			i++;
		}
		return res;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
	