package com.leetcode.editor.util;

import java.io.*;
import java.util.*;

public class FileSearch {

    public static void main(String[] args) {
        String dirPath = "C:\\Users\\EDY\\Desktop\\12-27"; // 指定文件夹路径
        String searchString = "杨盼盼\"}"; // 指定要查找的字符串
        File[] files = new File(dirPath).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try (Scanner scanner = new Scanner(file)) {
                        int lineNo = 0;
                        while (scanner.hasNextLine()) {
                            lineNo++;
                            String line = scanner.nextLine();
                            if (line.contains(searchString)) {
                                System.out.printf("%s:%d:%s%n", file, lineNo, line);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (file.isDirectory()) {
                    searchFiles(file.getAbsolutePath(), searchString);
                }
            }
        }
    }

    private static synchronized void searchFiles(String dirPath, String searchString) {
        File[] files = new File(dirPath).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try (Scanner scanner = new Scanner(file)) {
                        int lineNo = 0;
                        while (scanner.hasNextLine()) {
                            lineNo++;
                            String line = scanner.nextLine();
                            if (line.contains(searchString)) {
                                System.out.printf("%s:%d:%s%n", file, lineNo, line);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (file.isDirectory()) {
                    searchFiles(file.getAbsolutePath(), searchString);
                }
            }
        }
    }
}