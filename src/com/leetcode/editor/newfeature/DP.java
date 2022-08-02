package com.leetcode.editor.newfeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DP {
    public static void main(String[] args) {
        // 1.DP打印三角形最短路径
//        minimumTotalInit();
        // 2.DP求斐波那契数
//        fibonacciInit();
        // 3.DP跳台阶
//        climbStairsInit();
        // 4.使用最小花费爬楼梯
//        minCostClimbingStairsInit();
        // 5.打家劫舍
//        robInit();
        // 6.删除并获得点数
//        deleteAndEarnInit();
        // 7.跳跃游戏
//        canJumpInit();
        // 8.编辑距离
//        minDistanceInit();
        // 9.跳跃游戏Ⅱ
//        jumpInit();
        // 10.最大子数组和
//        maxSubArrayInit();
        // 11.乘积最大子数组
        maxProductInit();
    }

    /**
     * 乘积最大子数组
     * https://leetcode-cn.com/problems/maximum-product-subarray/
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }

    public static void maxProductInit() {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct(new int[]{-2, 0, -1}));
    }


    /**
     * 最大子数组和
     * https://leetcode-cn.com/problems/maximum-subarray/
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            // 可以用滚动数组
            res = Math.max(res, dp[i]);
        }
        return res;
//        int pre = 0, maxAns = nums[0];
//        for (int x : nums) {
//            pre = Math.max(pre + x, x);
//            maxAns = Math.max(maxAns, pre);
//        }
//        return maxAns;
    }

    public static void maxSubArrayInit() {
        System.out.println(maxSubArray(new int[]{-2, -3, -1, -5}));
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }

    /**
     * 跳跃游戏Ⅱ
     * https://leetcode-cn.com/problems/jump-game-ii/
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int pos = 0, end = 0, step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            pos = Math.max(pos, i + nums[i]);
            if (i == end) {
                end = pos;
                step++;
            }
        }
        return step;
    }

    public static void jumpInit() {
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jump(new int[]{2, 3, 0, 1, 4}));
    }

    /**
     * 字符串题：两个字符串变成一样的需要多少步
     * https://leetcode-cn.com/problems/edit-distance/
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }
        // DP 数组
        int[][] D = new int[n + 1][m + 1];
        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }
        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    public static void minDistanceInit() {
        System.out.println(minDistance("horse", "ros"));
    }

    /**
     * 跳跃游戏
     * https://leetcode-cn.com/problems/jump-game/
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > temp) {
                return false;
            }
            int x = i + nums[i];
            temp = Math.max(temp, x);
            if (temp >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    public static void canJumpInit() {
//        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
    }

    /**
     * 删除并获得点数
     * https://leetcode-cn.com/problems/delete-and-earn/
     *
     * @param nums
     * @return
     */
    public static int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int max = Arrays.stream(nums).max().getAsInt();
        int[] temp = new int[max + 1];
        for (int i = 0; i < nums.length; i++) {
            temp[nums[i]]++;
        }
        int[] dp = new int[max + 1];
        dp[1] = temp[1];
        dp[2] = Math.max(dp[1], temp[2] * 2);
        for (int i = 2; i <= max; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * temp[i]);
        }
        return dp[max];
    }

    public static void deleteAndEarnInit() {
        System.out.println(deleteAndEarn(new int[]{3, 4, 2}));
        System.out.println(deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
    }

    /**
     * 打家劫舍
     * https://leetcode-cn.com/problems/house-robber/
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int x = dp[i - 1];
            int y = nums[i] + dp[i - 2];
            dp[i] = Math.max(x, y);
        }
        return dp[dp.length - 1];
    }

    public static int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        // 偷第一家，不能偷最后一家
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < length - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        int temp = dp[length - 2];
        // 不偷第一家，能偷最后一家
        dp[0] = 0;
        dp[1] = nums[1];
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return Math.max(temp, dp[length - 1]);
    }

    public static void robInit() {
        System.out.println(rob(new int[]{1, 2}));
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(rob2(new int[]{2, 3, 2}));
        System.out.println(rob2(new int[]{1, 2, 3, 1}));
    }

    /**
     * 使用最小花费爬楼梯
     * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {
        // 滚动数组
        int pre = 0, curr = 0;
        for (int i = 2; i <= cost.length; i++) {
            int temp = Math.min(curr + cost[i - 1], pre + cost[i - 2]);
            pre = curr;
            curr = temp;
        }
        return curr;
//        int n = cost.length;
//        int[] dp = new int[n + 1];
//        dp[0] = dp[1] = 0;
//        for (int i = 2; i <= n; i++) {
//            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
//        }
//        return dp[n];
        //
//        int[] dp = new int[cost.length];
//        dp[0] = cost[0];
//        dp[1] = cost[1];
//        for (int i = 2; i < cost.length; i++) {
//            int x = dp[i - 1] + cost[i];
//            int y = dp[i - 2] + cost[i];
//            dp[i] = Math.min(x, y);
//        }
//        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }

    public static void minCostClimbingStairsInit() {
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    /**
     * 跳台阶 每次有1步或2步
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        int x = 0;
        int y = 0;
        int z = 1;
        for (int i = 1; i <= n; i++) {
            x = y;
            y = z;
            z = x + y;
        }
        return z;
//        if (n < 2) {
//            return n;
//        }
//        int[] dp = new int[n + 1];
//        dp[0] = 0;
//        dp[1] = 1;
//        dp[2] = 2;
//        for (int i = 3; i <= n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[n];
    }

    public static void climbStairsInit() {
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));
    }

    /**
     * 求斐波那契数
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 求泰波那契数
     *
     * @param n
     * @return
     */
    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int p = 0, q = 0, r = 1, s = 1;
        for (int i = 3; i <= n; i++) {
            p = q;
            q = r;
            r = s;
            s = p + q + r;
        }
        return s;
    }

    public static void fibonacciInit() {
        System.out.println(fibonacci(7));
//        System.out.println(fibonacci(25));
        System.out.println(tribonacci(6));
//        System.out.println(tribonacci(25));
    }

    /**
     * 打印三角形最短路径
     * https://leetcode-cn.com/problems/triangle/
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
//        int[][] dp = new int[triangle.size()][triangle.size()];
//        dp[0][0] = triangle.get(0).get(0);
//        for (int i = 1; i < triangle.size(); i++) {
//            for (int j = 0; j < triangle.get(i).size(); j++) {
//                if (j == 0) {
//                    // 第一个直接加上面的
//                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
//                } else if (j == i) {
//                    // 最后一个直接加上面的
//                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
//                } else {
//                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
//                }
//            }
//        }
//        int size = triangle.size() - 1;
//        int temp = dp[size][0];
//        for (int i = 0; i < dp[size].length; i++) {
//            temp = Math.min(dp[size][i], temp);
//        }
//        return temp;
    }

    public static void minimumTotalInit() {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        list.add(l1);

        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);
        list.add(l2);

        List<Integer> l3 = new ArrayList<>();
        l3.add(6);
        l3.add(5);
        l3.add(7);
        list.add(l3);

        List<Integer> l4 = new ArrayList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);
        list.add(l4);
        System.out.println(minimumTotal(list));
//                {1},            //      1
//                {2, 3},         //     3  4
//                {6, 5, 1},      //    9  9  5
//                {5, 7, 8, 1},   //   14 16 17 6
//                {1, 4, 6, 7, 9} // 15 20 23 24 15
    }
}
