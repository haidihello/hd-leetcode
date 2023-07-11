package com.leetcode.editor.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectTableNotice {
    public static void main(String[] args) throws IOException {
        Long start = System.currentTimeMillis();
        String path = "C:\\Users\\EDY\\Desktop\\文档\\数据研发部项目表\\数据研发部项目表.csv";
        LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");
        String[] name = {"刘方明", "胡付豪", "罗智擎", "张笑天", "杨晓峰", "魏冬冬", "宋吕军", "康嘉鑫", "杨海波", "李强", "周宇新", "李心甜", "张海迪"};
        List list = new ArrayList<>();
        Map<String, String> resultMap = new HashMap<>();
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                String[] split = line.split(",");
                for (int i = 0; i < name.length; i++) {
                    if (split.length < 6) {
                        continue;
                    }
                    if (!"进行中、开发中、技术设计中".contains(split[6])) {
                        continue;
                    }
                    String nametemp;
                    if (name[i].length() > 2) {
                        //三字的取后俩字
                        nametemp = name[i].substring(1, 3);
                    } else {
                        nametemp = name[i];
                    }
                    if (line.contains(nametemp)) {
                        String project = resultMap.get(name[i]);
                        if (StringUtils.isBlank(project)) {
                            resultMap.put(name[i], split[1]);
                        } else {
                            resultMap.put(name[i], project + "," + split[1]);
                        }
                    }

                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }

        System.out.println("Map" + JSON.toJSON(resultMap));

    }

    @Test
    public void readExcel() throws FileNotFoundException, InterruptedException {
        // 创建一个输入流，将 Excel 文件读取出来
//        InputStream inputStream = new FileInputStream("C:\\Users\\EDY\\Desktop\\文档\\数据研发部项目表\\数据研发部项目表.xlsx");
        InputStream inputStream = new FileInputStream("C:\\Users\\EDY\\Desktop\\文档\\数据研发部项目表\\7-7-1.xlsx");


        List<Map> tmpList = EasyExcel.read(inputStream)
                // 设置与Excel表映射的类
                // 设置sheet,默认读取第一个
                .sheet()
                // 设置标题所在行数
                .headRowNumber(1)
                // 异步读取
                .doReadSync();

        Thread.sleep(2000);
        System.out.println(tmpList.size());
        String[] name = {"刘方明", "胡付豪", "罗智擎", "张笑天", "杨骁锋", "魏冬冬", "宋吕军", "康嘉鑫", "杨海波", "李强", "周宇新", "李心田", "张海迪","郭智慧","牛春雨","常军霞","娄晓磊"};
        Map<String, String> resultMap = new HashMap<>();

        for (int j = 0; j < tmpList.size(); j++) {
           Map map = tmpList.get(j);
            String line = map.values().toString();
            for (int i = 0; i < name.length; i++) {
                if (!(map.containsValue("进行中")||map.containsValue("开发中")||map.containsValue("技术设计中"))) {
                    continue;
                }
                String nametemp;
                if (name[i].length() > 2) {
                    //三字的取后俩字
                    nametemp = name[i].substring(1, 3);
                } else {
                    nametemp = name[i];
                }

                if (line.contains(nametemp)) {
                    String project = resultMap.get(name[i]);
                    if (StringUtils.isBlank(project)) {
                        resultMap.put(name[i], map.get(2).toString());
                    } else {
                        resultMap.put(name[i], project + "," + map.get(2).toString());
                    }
                }else {
                    if (StringUtils.isBlank(resultMap.get(name[i]))) {
                        resultMap.put(name[i], "");
                    }
                }

            }

        }
        System.out.println(resultMap.size());
        StringBuilder sb = new StringBuilder();
        for (String key : resultMap.keySet()) {
            sb.append(key).append(" => ").append(resultMap.get(key)).append("\n");
        }
//        DingDingSendMsgUtils.sendMessageAtAll("开发中、进行中、技术设计中状态的任务[时间]：\n"+sb.toString());

    }

}
