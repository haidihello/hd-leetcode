package com.leetcode.editor.juc;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HaiDi
 * @Date: 2022/9/19 20:29
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {
        Long start = System.currentTimeMillis();
        String path = "D:\\文档\\书籍\\银联微信\\银联微信_20220811.csv";
        LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");
        List list = new ArrayList<>();
        try {
            while (it.hasNext()) {
                String line = it.nextLine();

                list.add(line);
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
        System.out.println("list大小" + list.size());
        System.out.println("读取耗时：" + (System.currentTimeMillis() - start));
    }

}
