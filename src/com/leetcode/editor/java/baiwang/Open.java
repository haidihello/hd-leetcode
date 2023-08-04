package com.leetcode.editor.java.baiwang;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 生产环境请求示例
 * @author HaiDi
 * @since 2023-07-07 14:26
 */
public class Open {
    /**
     * 接入参数  请填写实际测试参数
     */
//    private static String url = "https://open.yinshuitong.com/api";
    private static String url = "https://open.yinshuitong.com/api";
    private static String appKey="10000892";
    private static String appSecret="7d7c6e14-3fe1-42db-8d93-a6539f8f7d4b";
    private static String userName="27169189";
    private static String password="a12345";
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
    /**
     * 发票核验接口 返回response对象
     * @param
     * @return
     */
    @Test
    public void invoiceCheck(){
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

    /**
     * 统一信用报告
     */
    @Test
    public void creditReport() throws IOException {
        Map param = new HashMap();
        param.put("orgNo", "44ad8fcde176b454636e");
        param.put("taxNo", "91110113MA01DFUN91");
        param.put("creditCode", "91110113MA01DFUN91");

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.report.creditReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("统一信用报告："+url);
        System.out.println("统一信用报告："+body);
        // 创建URL对象
        URL obj = new URL(url);

        // 打开HTTP连接
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        // 设置请求方法
        conn.setRequestMethod("POST");

        // 设置请求头
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("z-real-ip", "192.168.126.13");

        // 请求体内容
        String requestBody = body;

        // 启用输出流，将请求体内容写入请求
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(requestBody.getBytes());
        os.flush();
        os.close();

        // 发起请求
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // 读取响应内容
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 打印响应内容
        System.out.println("Response: " + response.toString());
//        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
//        System.out.println("统一信用报告："+jsonObject.toJSONString());

    }
    /**
     * 发票核验接口 返回response对象
     * @param
     * @return
     */
    @Test
    public void VINmonitor(){
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91420102MA4K39R136");
        param.put("creditCode", "91420102MA4K39R136");

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.vehicle.batchImportMonitorVin";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("VIN码监控："+url);
        System.out.println("VIN码监控："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("VIN码监控返回："+jsonObject.toJSONString());
    }

    /**
     * 单张核验
     */
    @Test
    public void single(){
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91420102MA4K39R136");
        param.put("creditCode", "91420102MA4K39R136");

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.invoiceCheckv2";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("VIN码监控："+url);
        System.out.println("VIN码监控："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("VIN码监控返回："+jsonObject.toJSONString());
    }
}
