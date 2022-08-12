package com.leetcode.editor.cn;
/**
 * <p>给你一个整数数组 <code>nums</code> ，该数组具有以下属性：</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <ul>
 * <li><code>nums.length == 2 * n</code>.</li>
 * <li><code>nums</code> 包含 <code>n + 1</code> 个 <strong>不同的</strong> 元素</li>
 * <li><code>nums</code> 中恰有一个元素重复 <code>n</code> 次</li>
 * </ul>
 * </div>
 * </div>
 *
 * <p>找出并返回重复了 <code>n</code><em> </em>次的那个元素。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,3]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,1,2,5,3,2]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [5,1,5,2,5,3,5,4]
 * <strong>输出：</strong>5
 * </pre>
 *
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= n &lt;= 5000</code></li>
 * <li><code>nums.length == 2 * n</code></li>
 * <li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>nums</code> 由 <code>n + 1</code> 个<strong> 不同的</strong> 元素组成，且其中一个元素恰好重复 <code>n</code> 次</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 173</li><li>👎 0</li></div>
 */

import java.util.HashMap;
import java.util.Map;

/**
 *@author haidi
 *2022-08-11 17:05:22
 */
class NRepeatedElementInSize2nArray {
    public static void main(String[] args) {
        Solution solution = new NRepeatedElementInSize2nArray().new Solution();
        System.out.println(solution.repeatedNTimes(new int[]{2, 1, 2, 5, 3, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int repeatedNTimes(int[] nums) {
            Map map = new HashMap();
            for (int i = 0; i < nums.length; i++) {
                if (map.get(nums[i]) != null) {
                    return nums[i];
                }
                map.put(nums[i], i);
            }
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
	