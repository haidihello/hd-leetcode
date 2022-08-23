package com.leetcode.editor.cn;
//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 位运算 数学 字符串 模拟 
// 👍 704 👎 0

/**
*@author haidi
*2021-11-22 10:35:45
*/
class AddBinary{
	public static void main(String[] args) {
		Solution solution = new AddBinary().new Solution();
		System.out.println(solution.addBinary("10001","100"));
	}
	//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
		StringBuffer ans = new StringBuffer();

		int n = Math.max(a.length(), b.length()), carry = 0;
		for (int i = 0; i < n; ++i) {
			carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
			carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
			ans.append((char) (carry % 2 + '0'));
			carry /= 2;
		}

		if (carry > 0) {
			ans.append('1');
		}
		ans.reverse();

		return ans.toString();
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
	