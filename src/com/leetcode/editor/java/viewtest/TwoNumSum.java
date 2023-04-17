package com.leetcode.editor.java.viewtest;

import java.util.HashMap;
import java.util.Map;

/**
 * 两树之和返回数组下标 要求时间复杂度nlogn
 */
public class TwoNumSum {
     static int[] twoSum(int[] arr ,int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == target - arr[j]) {
                    return new int[]{j, i};
                }

            }
        }
        return new int[]{};
    }

    /**
     * 使用map 时间复杂度为O(N)
     * @return
     */
    static int[] twoSumMap(int[] arr,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target-map.get(arr[i]))) {
                return new int[]{map.get(arr[i]),i};
            }else {
                map.put(arr[i], i);
            }


        }

        return new int[]{};
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 5, 6, 9};
        int[] result= twoSum(a, 13);
        for (int i = 0; i <result.length ; i++) {
            System.out.println(result[i]);

        }
    }
}

