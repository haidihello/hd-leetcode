package com.leetcode.editor.effectivejava.sort;

/**
 * @Author: HaiDi
 * @Date: 2022/10/14 13:44
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 2, 5, 48,4, 42, 7, 8, 4};
        int target = 48;
        int idx = binarySearch(array, target);
        System.out.println(idx);
    }

    public static int binarySearch(int[] a, int t) {
        int l = 0, r = a.length - 1, m;
        while (l <= r) {
            m = (l + r) / 2;
            if (a[m] == t) {
                return m;
            } else if (a[m] > t) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;

    }
}
