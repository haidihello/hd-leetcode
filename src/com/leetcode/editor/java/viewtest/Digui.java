package com.leetcode.editor.java.viewtest;

import java.lang.reflect.Array;
import java.util.Arrays;

/**递归实现冒泡排序
 * @author HaiDi
 * @since 2025-05-13 15:22
 */
public class Digui {
    private static void bubbleSort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n-1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
            bubbleSort(arr,n-1);
        }

        }
    // 测试代码
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("排序前：" + Arrays.toString(arr));

        bubbleSort(arr, arr.length);

        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
