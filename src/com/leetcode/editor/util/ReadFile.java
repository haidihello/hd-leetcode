package com.leetcode.editor.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 处理11月分润数据
     * @throws IOException
     */
    @Test
    public void readFileToMongo() throws IOException {
        String haveAgentNum = "A151201808021549146250\n" ;
        String path = "C:\\Users\\admin\\Desktop\\online\\2023-0302非直营分润处理\\调整金额.csv";
        LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("C:\\Users\\admin\\Desktop\\online\\2023-0302非直营分润处理\\03月非直营分润数据处理408sql.txt")));
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
                if (StringUtils.isBlank(strArr[0])) {
                    break;
                }
                if (!haveAgentNum.contains(strArr[0])) {
                    continue;
                }
                String str = " db.getCollection(\"month_new_profit_bd\").update({\"agentNum\":"
                        + "\"" + strArr[0] + "\","
                        + "\"monthProfitDate\":\"2023-02\"},"
                        + "{ $inc: { totalMonthAccountDecreaseAmt: NumberLong(" + strArr[1] + ") } });";
                writer.write(str + System.lineSeparator());
                list.add(line);
            }
        } finally {
            writer.close();
            it.close();
            LineIterator.closeQuietly(it);
        }
    }

    @Test
    public void stingTest() {
        String[] strArr = {"A171201809250612351520","济南风雨人商贸有限公司","301"};

        String str = " db.getCollection(\"month_new_profit_bd\").update({\"agentNum\":"
                + "\"" + strArr[0] + "\","
                + "\"monthProfitDate\":\"2022-11\"},"
                + "{ $inc: { totalDayAccountDecreaseAmt: NumberLong(" + strArr[2] + ") } })";
        System.out.println(str);

    }
    /*
    *处理表格数据
     */
    @Test
    public void readFileToCSV() throws IOException {

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
    //读EXCEL文件
    @Test
    public void readExecl() throws IOException {
        String path = "C:\\Users\\admin\\Desktop\\online\\test.xlsx";
        HashMap<String, ArrayList<ArrayList<String>>> map =  ReadExcelUtil.readExcel(new File(path), 0);
        List list = map.get("sheet0");

    }

    @Test
    public void insert() throws IOException {
        String path = "C:\\Users\\admin\\Desktop\\online\\2023-0302非直营分润处理\\调整金额.csv";
        LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("C:\\Users\\admin\\Desktop\\online\\2023-0302非直营分润处理\\03月非直营分润数据处理408sql.txt")));
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
                if (StringUtils.isBlank(strArr[0])) {
                    break;
                }
                String str = " db.getCollection(\"month_profit_deduct\").insert({\"agentNum\":"
                        + "\"" + strArr[0] + "\","
                        + "\"agentNum\": NumberLong(" + strArr[1] + ")  });";
                writer.write(str + System.lineSeparator());
                list.add(line);
            }
        } finally {
            writer.close();
            it.close();
            LineIterator.closeQuietly(it);
        }
    }
    /*
     *处理表格数据
     */
    @Test
    public void deduplicationSql() throws IOException, CsvException {

        String path = "C:\\Users\\EDY\\Desktop\\文档\\慢sql\\10111.csv";
        String newPath = "C:\\Users\\EDY\\Desktop\\文档\\慢sql\\slowsqllast.csv";
        // 创建CSVReader对象
        CSVReader reader = new CSVReader(new FileReader(path));

        CSVWriter writer = new CSVWriter(new FileWriter(new File(newPath)));
        // 使用readAll()方法读取所有行
        List<String[]> rows = reader.readAll();

        List<String[]> newList = new ArrayList<>();
        Map<String,String> map = new HashMap();
        // 遍历每一行并按表格输出
        for (String[] row : rows) {
            String str = row[7];
            String keyword = "where";
            int index = str.indexOf(keyword);
            if (index != -1) {
                // 如果找到关键字，使用 substring() 方法提取从开始到该位置的字符串
                String result = str.substring(0, index);
                if (map.containsKey(result)) {
                    continue;
                }
                map.put(result, result);
                newList.add(row);
            }
            String keyword2 = "WHERE";
            int index2 = str.indexOf(keyword2);
            if (index2 != -1) {
                // 如果找到关键字，使用 substring() 方法提取从开始到该位置的字符串
                String result = str.substring(0, index2);
                if (map.containsKey(result)) {
                    continue;
                }
                map.put(result, result);
                newList.add(row);
            }

            newList.add(row);
        }
        writer.writeAll(newList);
        // 关闭reader
        reader.close();
        writer.close();
    }
//    @Test
//    public void readFile() throws IOException {
//
//        String path = "C:\\Users\\admin\\Desktop\\需求文档\\10.26协议\\银行编码.txt";
//        LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");
////        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("C:\\Users\\admin\\Desktop\\需求文档\\10.26协议\\银行编码base.txt")));
//        List list = new ArrayList();
//        try {
//            int i = 0;
//            while (it.hasNext()) {
//                String line = it.nextLine();
//                i++;
//                if (i == 1) {
//                    continue;
//                }
//                String[] strArr = line.split(",");
//                String str = "insert into protocol_bank_base set "
//                        + " bankCode = " + "'" + strArr[0] + "'"
//                        + ",bankName=" + "'" + strArr[1] + "'";
//                if (strArr.length>2) {
//                    str = str + ",channelBankCode=" + "'" + strArr[2]+ "'";
//                }
//                str = str + ";";
//                writer.write(str + System.lineSeparator());
//                list.add(line);
//            }
//        } finally {
//            writer.close();
//            LineIterator.closeQuietly(it);
//        }
    }
