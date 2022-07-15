package com.leetcode.editor.newfeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MysqlTableToPojo {
    public static void main(String[] args) {
        String s = "create table p4_settle_audit\n" +
                "(\n" +
                "   id bigint auto_increment comment '主键id'\n" +
                "      primary key,\n" +
                "   globalId varchar(255) not null comment '全局id-->对应出款系统的打款请求号',\n" +
                "   orderId varchar(255) null comment '订单号',\n" +
                "   mercNum varchar(255) not null comment '商户号',\n" +
                "   mercName varchar(255) not null comment '商户号',\n" +
                "   netDotNum varchar(255) null comment '网点编号',\n" +
                "   mercSettleType varchar(255) null comment '按网点结算（BYNETDOT） / 按商户结算（BYMERC）',\n" +
                "   source varchar(255) not null comment '结算来源  POS  QPOS  QPAY  MPOS',\n" +
                "   settleWay varchar(255) not null comment '结算方式 TS、T0、D0、T1',\n" +
                "   accountType varchar(255) null comment '账户类型  对公：TO_PUBLIC  对私：TO_PRIVATE',\n" +
                "    accountName varchar(255) null comment '商户在银行的开户名',\n" +
                "    debitCard varchar(255) not null comment '结算卡号',\n" +
                "    bankAbbr varchar(255) null comment '开户行缩写 例如：ICBC-->对应出款系统的开户银行编号',\n" +
                "   province varchar(255) null comment '省',\n" +
                "   lineNum varchar(255) null comment '联行号',\n" +
                "    settlementAmount decimal(18) not null comment '结算金额',\n" +
                "    applyUser varchar(255) null comment '申请人',\n" +
                "    auditUser varchar(255) null comment '审核人',\n" +
                "    createTime datetime null comment '申请时间',\n" +
                "   auditTime datetime null comment '审核时间'\n" +
                ")\n" +
                "comment '商户T1结算审核表'";
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
