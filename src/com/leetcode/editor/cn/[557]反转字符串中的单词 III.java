package com.leetcode.editor.cn;
/**
<p>给定一个字符串
 <meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "Let's take LeetCode contest"
<strong>输出：</strong>"s'teL ekat edoCteeL tsetnoc"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong> s = "God Ding"
<strong>输出：</strong>"doG gniD"
</pre>

<p>&nbsp;</p>

<p><strong><strong><strong><strong>提示：</strong></strong></strong></strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li> 
 <li>
  <meta charset="UTF-8" /><code>s</code>&nbsp;包含可打印的 <strong>ASCII</strong> 字符。</li> 
 <li>
  <meta charset="UTF-8" /><code>s</code>&nbsp;不包含任何开头或结尾空格。</li> 
 <li>
  <meta charset="UTF-8" /><code>s</code>&nbsp;里 <strong>至少</strong> 有一个词。</li> 
 <li>
  <meta charset="UTF-8" /><code>s</code>&nbsp;中的所有单词都用一个空格隔开。</li> 
</ul>

<div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 468</li><li>👎 0</li></div>
*/


/**
*@author haidi
*2022-08-11 09:56:01
*/
class ReverseWordsInAStringIii{
	public static void main(String[] args) {
		Solution solution = new ReverseWordsInAStringIii().new Solution();
		System.out.println(solution.reverseWords("Let's take LeetCode contest"));
	}
	//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {

		String[] str = s.split(" ");
		StringBuffer word = new StringBuffer();
		for (int i = 0; i <str.length ; i++) {
			word.append(new StringBuffer(str[i]).reverse()).append(" ");
		}
		return word.toString().trim();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
	