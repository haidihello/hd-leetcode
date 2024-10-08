package com.leetcode.editor.java.baiwang;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.leetcode.editor.java.baiwang.openplat.FinanceHttpClient;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HaiDi
 * @since 2023-07-13 11:09
 */
public class CreditReport {
    /**
     * 接入参数  请填写实际测试参数
     */
    private static String url = "https://bwpre.credit.caih.com/creditApi";
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
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91420102MA4K39R136");
        param.put("creditCode", "91420102MA4K39R136");

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.report.tradeLoopReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("关联交易求环："+url);
        System.out.println("关联交易求环："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环："+jsonObject.toJSONString());

    }

    @Test
    public void test() {
        int i = 0;
        do {
            String url = "http://fintech-open.aliyun.yinshuitong.com/encryption/openApi/v2?channel=lhzx&uuid=" + UUID.randomUUID();
            Map map = new HashMap();
//        map.put("data", "");
            String result = HttpClientUtil.doPostJson(url, map.toString());
            System.out.println(result);
            if (result.contains("time")) {
                System.out.println("-------------");
                System.out.println(result);
                i = 1;
            }
        } while (i==0);

    }
}
