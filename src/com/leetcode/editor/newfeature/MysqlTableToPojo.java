package com.leetcode.editor.newfeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MysqlTableToPojo {
    public static void main(String[] args) {
        String s = "CREATE TABLE `p4_merchant_temp` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `mercName` varchar(255) DEFAULT NULL COMMENT '商户名称',\n" +
                "  `mcc` varchar(255) DEFAULT NULL COMMENT 'MCC',\n" +
                "  `province` varchar(255) DEFAULT NULL COMMENT '归属省',\n" +
                "  `city` varchar(255) DEFAULT NULL COMMENT '归属市',\n" +
                "  `area` varchar(255) DEFAULT NULL COMMENT '归属区',\n" +
                "  `businessAddress` varchar(255) DEFAULT NULL COMMENT '营业地址',\n" +
                "  `mercType` varchar(255) DEFAULT NULL COMMENT '商户性质',\n" +
                "  `businessNumber` varchar(255) DEFAULT NULL COMMENT '营业执照号',\n" +
                "  `registerAddress` varchar(255) DEFAULT NULL COMMENT '注册地址',\n" +
                "  `idCardNumber` varchar(255) DEFAULT NULL COMMENT '法人证件号码',\n" +
                "  `legalName` varchar(255) DEFAULT NULL COMMENT '法人姓名',\n" +
                "  `bankName` varchar(255) DEFAULT NULL COMMENT '开户银行',\n" +
                "  `accountNumber` varchar(255) DEFAULT NULL COMMENT '结算银行账号',\n" +
                "  `accountName` varchar(255) DEFAULT NULL COMMENT '账户名称',\n" +
                "  `bankProvince` varchar(255) DEFAULT NULL COMMENT '开户省份',\n" +
                "  `bankCity` varchar(255) DEFAULT NULL COMMENT '开户城市',\n" +
                "  `bankBranchName` varchar(255) DEFAULT NULL COMMENT '开户支行',\n" +
                "  `linkNo` varchar(255) DEFAULT NULL COMMENT '网点号(联行号)',\n" +
                "  `termNo` varchar(255) DEFAULT NULL COMMENT '终端号',\n" +
                "  `termSerialNo` varchar(255) DEFAULT NULL COMMENT '终端序列号',\n" +
                "  `mercReportedState` varchar(255) DEFAULT NULL COMMENT '商户报备状态',\n" +
                "  `termReportedState` varchar(255) DEFAULT NULL COMMENT '终端报备状态',\n" +
                "  `unionMercNum` varchar(255) DEFAULT NULL COMMENT '银联商户号',\n" +
                "  `defaultReportedState` varchar(255) DEFAULT 'N' COMMENT '默认报备状态:N',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `un_index` (`businessNumber`) USING BTREE\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;\n" +
                "\n";
        MysqlTableToPojo(s, true, true);
    }

    private static final String PRE = "/**\n";
    private static final String AFT = "\n*/\n";
    private static final String ALT = ";\n";
    private static final String PRIVATE = "private ";
    private static final String STRING = "String ";
    private static final String INT = "int ";
    private static final String DATE = "Date ";
    private static final String DECIMAL = "BigDecimal ";

    /**
     * Mysql create语句转POJO类
     *
     * @param s    create语句
     * @param flag 类名是否下划线转驼峰
     * @Param isOutPut 是否输出到当前类
     */
    public static void MysqlTableToPojo(String s, boolean flag, boolean isOutPut) {
        String[] strings = s.split("\n");
        StringBuilder sb = new StringBuilder();
        String className = strings[0].split(" ")[2].replace("`", "");
        if (flag) {
            className = lineToHump(className);
        }
        sb.append("public class ").append(className).append("{").append("\n");
        // ID列特殊标注
        sb.append(PRE);
        sb.append("*ID\n*/\n");
        sb.append("private Long id");
        sb.append(ALT);
        for (int i = 2; i < strings.length; i++) {
            // 到索引行结束
            if (strings[i].contains("KEY")) {
                break;
            }
            sb.append(PRE);
            sb.append("*");
            sb.append(strings[i], strings[i].indexOf("COMMENT") + 9, strings[i].length() - 2);
            sb.append(AFT);
            sb.append(checkType(strings[i]));
            sb.append(ALT);
        }
        sb.append("}");
        System.out.println(sb.toString());
        if (isOutPut) {
            // 输出文件
            outPut(sb.toString(), className);
        }
    }

    public static void outPut(String s, String className) {
        // 比较复杂
//        String property = System.getProperty("user.dir") + "/src/com/leetcode/editor/newfeature/";
        String url = "D:\\git\\hd-leetcode\\src\\com\\leetcode\\editor\\newfeature\\";
        String path = url + className + ".java";

//        String classPath = MysqlUtils.class.getResource("").getPath();
//        classPath = classPath.substring(classPath.indexOf("com"));
//        String path = (property).replace("/", File.separator) + className + ".java";
        BufferedWriter wr = null;
        try {
            wr = new BufferedWriter(new FileWriter(new File(path)));
            wr.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String checkType(String s) {
        StringBuilder re = new StringBuilder();
        re.append(PRIVATE);
        String type = s.split(" ")[3];
        String name = s.split(" ")[2].replace("`", "");
        if (type.contains("varchar")) {
            re.append(STRING);
            re.append(name);
            return re.toString();
        }
        if (type.contains("int")) {
            re.append(INT);
            re.append(name);
            return re.toString();
        }
        if (type.contains("datetime")) {
            re.append(DATE);
            re.append(name);
            return re.toString();
        }
        if (type.contains("decimal")) {
            re.append(DECIMAL);
            re.append(name);
            return re.toString();
        }
        return null;
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
