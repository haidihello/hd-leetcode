package com.leetcode.editor.cn;
/**
<p>给你一个数组 <code>nums</code> 。数组「动态和」的计算公式为：<code>runningSum[i] = sum(nums[0]…nums[i])</code> 。</p>

<p>请返回 <code>nums</code> 的动态和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>[1,3,6,10]
<strong>解释：</strong>动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,1,1,1]
<strong>输出：</strong>[1,2,3,4,5]
<strong>解释：</strong>动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [3,1,2,10,1]
<strong>输出：</strong>[3,4,6,16,17]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
 <li><code>-10^6&nbsp;&lt;= nums[i] &lt;=&nbsp;10^6</code></li> 
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>前缀和</li></div></div><br><div><li>👍 190</li><li>👎 0</li></div>
*/

import java.util.Arrays;

/**
*@author haidi
*2022-07-21 11:08:08
*/
class RunningSumOf1dArray{
	public static void main(String[] args) {
		Solution solution = new RunningSumOf1dArray().new Solution();
		int[] n = {1, 4, 5, 6};
		System.out.println(Arrays.toString(solution.runningSum(n)));
	}
	//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] runningSum(int[] nums) {
		int n = nums.length;
		if (nums.length == 1){
			return nums;
		}
		for (int i = 1; i <n ; i++) {
			nums[i] += nums[i - 1];
		}
		return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
	