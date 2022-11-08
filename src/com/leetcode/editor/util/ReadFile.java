package com.leetcode.editor.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

    /**
     * 读文件生成单条sql文件
     * @throws IOException
     */
    @Test
    public void readFileToSQL() throws IOException {

        String path = "C:\\Users\\admin\\Desktop\\需求文档\\10.26协议\\银行编码.txt";
        LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("C:\\Users\\admin\\Desktop\\需求文档\\10.26协议\\银行编码base.txt")));
        List list = new ArrayList();
        try {
            int i = 0;
            while (it.hasNext()) {
                String line = it.nextLine();
                i++;
                if (i == 1) {
                    continue;
                }
                String[] strArr = line.split(",");
                String str = "insert into protocol_bank_base set "
                        + " bankCode = " + "'" + strArr[0] + "'"
                        + ",bankName=" + "'" + strArr[1] + "'";
                if (strArr.length>2) {
                   str = str + ",channelBankCode=" + "'" + strArr[2]+ "'";
                }
                str = str + ";";
                writer.write(str + System.lineSeparator());
                list.add(line);
            }
        } finally {
            writer.close();
            LineIterator.closeQuietly(it);
        }
    }

}
