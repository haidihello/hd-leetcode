package com.leetcode.editor.cn;
/**
<p>你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。</p>

<p>比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 <code>ops</code>，其中 <code>ops[i]</code> 是你需要记录的第 <code>i</code> 项操作，<code>ops</code> 遵循下述规则：</p>

<ol> 
 <li>整数 <code>x</code> - 表示本回合新获得分数 <code>x</code></li> 
 <li><code>"+"</code> - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。</li> 
 <li><code>"D"</code> - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。</li> 
 <li><code>"C"</code> - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。</li> 
</ol>

<p>请你返回记录中所有得分的总和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>ops = ["5","2","C","D","+"]
<strong>输出：</strong>30
<strong>解释：</strong>
"5" - 记录加 5 ，记录现在是 [5]
"2" - 记录加 2 ，记录现在是 [5, 2]
"C" - 使前一次得分的记录无效并将其移除，记录现在是 [5].
"D" - 记录加 2 * 5 = 10 ，记录现在是 [5, 10].
"+" - 记录加 5 + 10 = 15 ，记录现在是 [5, 10, 15].
所有得分的总和 5 + 10 + 15 = 30
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>ops = ["5","-2","4","C","D","9","+","+"]
<strong>输出：</strong>27
<strong>解释：</strong>
"5" - 记录加 5 ，记录现在是 [5]
"-2" - 记录加 -2 ，记录现在是 [5, -2]
"4" - 记录加 4 ，记录现在是 [5, -2, 4]
"C" - 使前一次得分的记录无效并将其移除，记录现在是 [5, -2]
"D" - 记录加 2 * -2 = -4 ，记录现在是 [5, -2, -4]
"9" - 记录加 9 ，记录现在是 [5, -2, -4, 9]
"+" - 记录加 -4 + 9 = 5 ，记录现在是 [5, -2, -4, 9, 5]
"+" - 记录加 9 + 5 = 14 ，记录现在是 [5, -2, -4, 9, 5, 14]
所有得分的总和 5 + -2 + -4 + 9 + 5 + 14 = 27
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>ops = ["1"]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= ops.length &lt;= 1000</code></li> 
 <li><code>ops[i]</code> 为 <code>"C"</code>、<code>"D"</code>、<code>"+"</code>，或者一个表示整数的字符串。整数范围是 <code>[-3 * 10<sup>4</sup>, 3 * 10<sup>4</sup>]</code></li> 
 <li>对于 <code>"+"</code> 操作，题目数据保证记录此操作时前面总是存在两个有效的分数</li> 
 <li>对于 <code>"C"</code> 和 <code>"D"</code> 操作，题目数据保证记录此操作时前面总是存在一个有效的分数</li> 
</ul>

<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>模拟</li></div></div><br><div><li>👍 276</li><li>👎 0</li></div>
*/

import java.util.ArrayList;
import java.util.List;

/**
*@author haidi
*2023-03-13 17:33:20
*/
class BaseballGame{
	public static void main(String[] args) {
		Solution solution = new BaseballGame().new Solution();
		String[] str = {"5","-2","4","C","D","9","+","+"};
		System.out.println(solution.calPoints(str));
	}
	//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int calPoints(String[] operations) {
		int sum = 0;
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < operations.length; i++) {
			if (operations[i].equals("C")) {
				list.remove(list.size()-1);
				continue;
			} else if (operations[i].equals("D")) {
				list.add(2*list.get(list.size()-1));
				continue;
			} else if (operations[i].equals("+")) {
				list.add(list.get(list.size()-2) + list.get(list.size()-1));
				continue;
			}
			list.add(Integer.parseInt(operations[i]));
			sum = sum + Integer.parseInt(operations[i]);
		}
		System.out.println(list);
		return sum;
    }

	}
//leetcode submit region end(Prohibit modification and deletion)

}
	