package com.leetcode.editor.cn;
//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1738 👎 0

import java.util.PriorityQueue;

/**
*@author haidi
*2022-07-15 13:13:38
*/
class SlidingWindowMaximum{
	public static void main(String[] args) {
		Solution solution = new SlidingWindowMaximum().new Solution();
		int[] a = {1,2,3};
		System.out.println(solution.maxSlidingWindow(a,2));
	}
	//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
		public int[] maxSlidingWindow(int[] nums, int k) {
			int n = nums.length;
			//这里我们传入了一个比较器，当两者的值相同时，比较下标的位置，下标大的在前面。
			PriorityQueue<int[]> queue = new PriorityQueue<>((p1, p2) -> p1[0] != p2[0] ? p2[0] - p1[0] : p2[1] - p1[1]);
			//初始化前K的元素到堆中
			for (int i = 0; i < k; i++) {
				queue.offer(new int[]{nums[i], i});
			}
			//有n-k+1个
			int[] ans = new int[n - k + 1];
			//将第一次答案加入数据
			ans[0] = queue.peek()[0];
			for (int i = k; i < n; i++) {
				//将新元素加入优先队列
				queue.offer(new int[]{nums[i], i});
				//循环判断当前队首是否在窗口中，窗口的左边界为i-k
				while (queue.peek()[1] <= i - k) {
					queue.poll();
				}
				//在窗口中直接赋值即可
				ans[i - k + 1] = queue.peek()[0];
			}
			return ans;
		}

	}
//leetcode submit region end(Prohibit modification and deletion)

}
	