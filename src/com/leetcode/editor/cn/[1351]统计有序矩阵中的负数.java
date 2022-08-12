package com.leetcode.editor.cn;
/**
 * <p>给你一个&nbsp;<code>m&nbsp;* n</code>&nbsp;的矩阵&nbsp;<code>grid</code>，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。&nbsp;请你统计并返回&nbsp;<code>grid</code>&nbsp;中 <strong>负数</strong> 的数目。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * <strong>输出：</strong>8
 * <strong>解释：</strong>矩阵中共有 8 个负数。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [[3,2],[1,0]]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == grid.length</code></li>
 * <li><code>n == grid[i].length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 100</code></li>
 * <li><code>-100 &lt;= grid[i][j] &lt;= 100</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(n + m)</code> 的解决方案吗？</p>
 *
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>矩阵</li></div></div><br><div><li>👍 111</li><li>👎 0</li></div>
 */

/**
 *@author haidi
 *2022-08-11 19:53:22
 */
class CountNegativeNumbersInASortedMatrix {
    public static void main(String[] args) {
        Solution solution = new CountNegativeNumbersInASortedMatrix().new Solution();
        int[][] array = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
        System.out.println(solution.countNegatives(array));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countNegatives(int[][] grid) {
//            int m = grid.length;
//            int n = grid[0].length;
//            int i = m - 1;
//            int j = 0;
//            int res = 0;
//            while (i >= 0 && j < n) {
//                if (grid[i][j] < 0) {
//                    res += n - j;
//                    i--;
//                } else {
//                    j++;
//                }
//            }
//            return res;
			//暴力循环解法
			int row = grid.length;
			int col = grid[0].length;
			int res = 0;
			for (int i = 0; i <row ; i++) {
				for (int j = 0; j <col ; j++) {
					if (grid[i][j] < 0) {
						res++;
					}

				}
			}
			return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
	