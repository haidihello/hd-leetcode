package com.leetcode.editor.cn;

/**给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * @author HaiDi
 * @since 2024-03-26 11:38
 */
public class zhizhen {
    public static void main(String[] args) {
        int[] nums = {0, 13, 4, 5};
        int temp = 0;
        for (int i = 0; i <nums.length ; i++) {
            nums[i] = temp;
        }
    }
    
}
