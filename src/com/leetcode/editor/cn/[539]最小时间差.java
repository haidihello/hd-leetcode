package com.leetcode.editor.cn;
//给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：timePoints = ["23:59","00:00"]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：timePoints = ["00:00","23:59","00:00"]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 2 <= timePoints.length <= 2 * 104 
// timePoints[i] 格式为 "HH:MM" 
// 
// Related Topics 数组 数学 字符串 排序 
// 👍 182 👎 0

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author haidi
 * 2022-01-19 15:04:19
 */
class MinimumTimeDifference {
    public static void main(String[] args) {

        Solution solution = new MinimumTimeDifference().new Solution();
        List<String> list = new ArrayList<>();
        list.add("00:00");

        list.add("23:59");
        list.add("22:55");

        System.out.println(solution.findMinDifference(list));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMinDifference(List<String> timePoints) {
            Integer chazhi;
            int[] shuzu;

            for (String st : timePoints) {
                st = st.replace(":", "");
                Integer stInt = Integer.parseInt(st);
                Collections.sort(timePoints);
            }

            return 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
	