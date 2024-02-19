package com.leetcode.editor.java.baiwang;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HaiDi
 * @since 2023-09-04 10:01
 */
public class TreadLoopPreTest {
    /**
     * 接入参数  请填写实际测试参数
     */
    private static String url = "https://sandbox.yinshuitong.com/api";
    private static String appKey="1000023";
    private static String appSecret="12f7add0-a189-4bc5-aab2-c3bc5da276be";
    private static String userName="23646901";
    private static String password="qweQWE123";
    private static String orgNo="4b9f82fa3273f1b94a96";
    private static String version="3.0";
    /**
     * 访问客户端 注意修改缓存处理
     */
    public static FinanceHttpClient financeHttpClient = new FinanceHttpClient(url, appKey, appSecret, userName, password, version);

    public static void main(String[] args) {
        Map param = new HashMap();
        param.put("orgNo", "4612b191ca67e9ecc816");
        param.put("taxNo", "91500000MA60394H37");
        param.put("creditCode", "91500000MA60394H37");
//        param.put("orgNo", "4b9f82fa3273f1b94a96");
//        param.put("taxNo", "91420102MA4K39R136");
//        param.put("creditCode", "91420102MA4K39R136");
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.report.tradeLoopReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("关联交易求环："+url);
        System.out.println("关联交易求环："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环："+jsonObject.toJSONString());


    }
    @Test
    public void creatReport() {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91420102MA4K39R136");
        param.put("creditCode", "91420102MA4K39R136");
        param.put("reportType", "winLending.finance.report.tradeLoopReport");
        param.put("serialNo", System.currentTimeMillis());

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.notify.createReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("关联交易求环："+url);
        System.out.println("关联交易求环："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环："+jsonObject.toJSONString());


    }
    /**
     * 监控分页查询
     */
    @Test
    public void abnoramlPage() {
        Map param = new HashMap();
        param.put("orgNo", "4dda886ad15a33287752");
        param.put("pageNum", 1);
        param.put("pageSize", 20);
        param.put("abnormalDateStart", "2020-01-01");

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.getAbnormalInvoicev3";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("关联交易求环："+url);
        System.out.println("关联交易求环："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环："+jsonObject.toJSONString());


    }
    @Test
    public void abnoramlPagef() {
        String a = "1";
        if (StringUtils.isNotBlank(a)) {
            System.out.println(1);
        } else if ("1".equals(a)) {
            System.out.println(2);
        } else {
            System.out.println(3);
        }

    }


}
