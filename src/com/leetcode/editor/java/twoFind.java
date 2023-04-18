package com.leetcode.editor.java;

/**
 * 二分查找
 * @Author: HaiDi
 * @Date: 2023/4/17 13:27
 */
public class twoFind {
    public static void main(String[] args) {
        int[] nums = {1,3,5,8,9};
        System.out.println(binarySerach(nums, 8));
    }

    static int binarySerach(int[]arr,int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left+(right-left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
