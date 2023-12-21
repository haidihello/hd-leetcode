package com.leetcode.editor.java.test;

import java.util.Scanner;

public class ScoreCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入成绩个数 n: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("请输入有效的成绩个数");
            return;
        }
        double totalScore = 0;

        for (int i = 1; i <= n; i++) {
            System.out.print("请输入第 " + i + " 个成绩: ");
            double score = scanner.nextDouble();
            totalScore += score;
        }

        double averageScore = totalScore / n;

        System.out.println("总分: " + totalScore + "，平均分: " + averageScore);
    }
}
