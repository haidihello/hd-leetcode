package com.leetcode.editor.java.baiwang;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.leetcode.editor.java.baiwang.openplat.FinanceHttpClient;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 21年之后注册的appkey需要在body中也传入参数
 * @author HaiDi
 * @since 2023-07-07 14:26
 */
public class tempOpenPlat {
    /**
     * 接入参数  请填写实际测试参数
     */
    private static String url = "https://sandbox.yinshuitong.com/api";
    private static String appKey="1002467";
    private static String appSecret="43d4959b-ffd8-47a1-b13e-59b6a441f475";
    private static String userName="35533570";
    private static String password="ff3ff6767ANF4545";
    private static String orgNo="4361b70db41191b49dc9";
    private static String version="3.0";
    /**
     * 访问客户端 注意修改缓存处理
     */
    public static FinanceHttpClient financeHttpClient = new FinanceHttpClient(url, appKey, appSecret, userName, password, version);

    @Test
    public  void test() {
        Map newMap = new HashMap();
        newMap.put("billingDate","2017-08-01");
        newMap.put("totalAmount","856.45");
        newMap.put("invoiceNumber","74853600");
        newMap.put("invoiceCode","3200160210");
        newMap.put("checkCode","699786");
        Map param = new HashMap();
        param.put("orgNo", "4361b70db41191b49dc9");
        param.put("invoie",newMap);


        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.invoiceCheckv2";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("资产核验："+url);
        System.out.println("资产核验："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("资产核验："+jsonObject.toJSONString());
    }
}
