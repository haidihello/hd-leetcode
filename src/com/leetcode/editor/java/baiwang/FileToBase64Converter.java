package com.leetcode.editor.java.baiwang;

import cn.hutool.core.lang.UUID;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public  class FileToBase64Converter {
    public  static List<String> convertFilesToBase64() {
        String directoryPath = "D:\\测试发票";
        File dir = new File(directoryPath);

        List<String> base64List = new ArrayList<>();

        if (dir.exists() && dir.isDirectory()) {
            processDirectory(dir, base64List);
        } else {
            System.out.println("The specified path is not a valid directory.");
        }
        return base64List;
//        // 输出每个文件的 Base64 编码
//        for (String base64 : base64List) {
//            System.out.println(base64);
//        }
//        System.out.println("Total files converted: " + base64List.size());
    }

    private static void processDirectory(File dir, List<String> base64List) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归处理子目录
                    processDirectory(file, base64List);
                } else {
                    // 处理文件并转换为 Base64
                    if (isSupportedFileType(file)) {
                        try {
                            String base64 = encodeFileToBase64(file);
                            base64List.add(base64);
                            System.out.println("Converted: " + file.getAbsolutePath());
                        } catch (IOException e) {
                            System.err.println("Error converting file: " + file.getAbsolutePath());
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static boolean isSupportedFileType(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".png") ||
                name.endsWith(".jpeg") || name.endsWith(".pdf");
    }

    private static String encodeFileToBase64(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public void prdyingshuitong() {
        String url = "http://open.prd.yinshuitong.com/checkApi/api/serialNet/assets/ocrInvoiceCheck/v4" ;
        Map map = new HashMap();
        //        map.put("data", "");
        String result = HttpClientUtil.doPostJson(url, map.toString());
    }

}
