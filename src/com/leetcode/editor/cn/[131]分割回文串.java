package com.leetcode.editor.cn;
/**
<p>给你一个字符串 <code>s</code>，请你将<em> </em><code>s</code><em> </em>分割成一些子串，使每个子串都是 <strong>回文串</strong> 。返回 <code>s</code> 所有可能的分割方案。</p>

<p><strong>回文串</strong> 是正着读和反着读都一样的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aab"
<strong>输出：</strong>[["a","a","b"],["aa","b"]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a"
<strong>输出：</strong>[["a"]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 16</code></li> 
 <li><code>s</code> 仅由小写英文字母组成</li> 
</ul>

<div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li><li>回溯</li></div></div><br><div><li>👍 1221</li><li>👎 0</li></div>
*/

import java.util.ArrayList;
import java.util.List;

/**
*@author haidi
*2022-08-05 10:32:16
*/
class PalindromePartitioning{
	public static void main(String[] args) {
		Solution solution = new PalindromePartitioning().new Solution();
	}
	//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> partition(String s) {
		List result = new ArrayList();
		int i = 0;
		String str = "";
		while (i < s.length()) {
			str += str;
			i++;
		}
		return result;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
	