package com.leetcode.editor.cn;
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 45 
// 
// Related Topics 记忆化搜索 数学 动态规划 
// 👍 2126 👎 0

/**
 * @author haidi
 * 2022-01-19 15:03:27
 */
class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
		System.out.println(solution.climbStairs(10));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        /**
//         * 动态规划思想
//         * @param n
//         * @return
//         */
//        public int climbStairs(int n) {
//            if (n == 0) return 0;
//            if (n == 1) return 1;
//            int[] dp = new int[n + 1];
//            dp[1] = 1;
//            dp[2] = 2;
//            for (int i = 3; i <= n; i++) {
//                dp[i] = dp[i - 1] + dp[i - 2];
//            }
//            return dp[n];
//        }

        public int climbStairs(int n) {
            if (n == 0){
                return 0;
            }
            int pre = 0, cur = 1;
            for (int i = 1; i <= n; i++) {
                cur = cur + pre;
                pre = cur - pre;
            }
            return cur;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
