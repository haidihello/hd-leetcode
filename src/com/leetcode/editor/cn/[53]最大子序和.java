package com.leetcode.editor.cn;
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 
// 👍 3962 👎 0

import java.util.Arrays;

/**
 * @author haidi
 * 2021-11-16 14:29:23
 */
class MaximumSubarray {
    //动态规划问题
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
        int[] num = {1, 2, 3, 6, -3, 3, 6, 5};
//		System.out.println(solution.maxSubArray(num));
        System.out.println(solution.maxSubArray2(num));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        public int maxSubArray(int[] nums) {
//
//            int len = nums.length;
//            if (len == 0) {
//                return 0;
//            }
//            return maxSubArraySum(nums, 0, len - 1);
//
//        }
//
//        private int maxCrossingSum(int[] nums, int left, int mid, int right) {
//    	int sum = 0;
//    	int leftSum = Integer.MIN_VALUE;
//			for (int i = mid; i >=left ; i--) {
//				sum += nums[i];
//				if (sum > leftSum) {
//					leftSum = sum;
//				}
//			}
//			sum = 0;
//			int rightSum = Integer.MIN_VALUE;
//			for (int i = mid +1; i <=right ; i++) {
//				sum += nums[i];
//				if (sum > rightSum) {
//					rightSum = sum;
//				}
//			}
//
//			return leftSum + rightSum;
//        }
//		private int maxSubArraySum(int[] nums, int left, int right) {
//			if (left == right) {
//				return nums[left];
//			}
//			int mid = left + (right - left) / 2;
//			return max3(maxSubArraySum(nums, left, mid),
//					maxSubArraySum(nums, mid + 1, right),
//					maxCrossingSum(nums, left, mid, right));
//		}
//
//		private int max3(int num1, int num2, int num3) {
//			return Math.max(num1, Math.max(num2, num3));
//		}

        /**
         * 贪心算法解法
         */
//        public int maxSubArray(int[] nums) {
//            int len = nums.length;
//            int[] dp = new int[len];
//            Arrays.fill(dp, Integer.MIN_VALUE);
//            dp[0] = nums[0];
//            int ans = dp[0];
//            for (int i = 1; i < len; i++) {
//                dp[i] = (Math.max(dp[i - 1], 0)) + nums[i];
//                ans = Math.max(ans, dp[i]);
//
//            }
//            return ans;
//        }

        public int maxSubArray2(int[] nums) {
            int result = Integer.MIN_VALUE;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                count += nums[i];
                if (count > result) {
                    result = count;
                }
                if (count <= 0) {
                    count = 0;
                }
            }
            return result;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
	