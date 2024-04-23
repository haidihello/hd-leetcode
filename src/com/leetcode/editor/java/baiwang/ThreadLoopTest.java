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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HaiDi
 * @since 2023-07-07 14:26
 */
public class ThreadLoopTest {
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
        param.put("orgNo", "493b8ba68cda90020a57");
        param.put("taxNo", "914404007079266759");
        param.put("creditCode", "914404007079266759");

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.report.tradeLoopReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("关联交易求环："+url);
        System.out.println("关联交易求环："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环："+jsonObject.toJSONString());


    }
    /**
     * 直连isp
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
     * 直连 finance-service 链路
     * 统一信用报告
     */
    @Test
    public void creditReport() throws IOException {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91420102MA4K39R136");
        param.put("creditCode", "91420102MA4K39R136");

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
//        conn.setRequestProperty("k-real-ip", "192.168.126.13");
//        conn.setRequestProperty("z-real-ip", "127.0.0.1");

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
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("统一信用报告："+jsonObject.toJSONString());

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

    @Test
    public void test() {
        Map newMap = new HashMap();
        newMap.put("billingDate","2017-08-01");
        newMap.put("totalAmount","856.45");
        newMap.put("invoiceNumber","74853600");
        newMap.put("invoiceCode","3200160210");
        newMap.put("checkCode","699786");
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("invoie",newMap);


        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.invoiceCheckv2";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("资产核验："+url);
        System.out.println("资产核验："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("资产核验："+jsonObject.toJSONString());
    }
    /**
     *查询税局状态
     */
    @Test
    public void getStatus() {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.pub.queryOrgNoAreaStatus";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("查询税局状态url："+url);
        System.out.println("查询税局状态body："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("查询税局状态："+jsonObject.toJSONString());
    }

    /**
     * 价税指数 分页查询
     */
    @Test
    public void priceIndex() {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("invoiceMonth", "202310");
        param.put("userSign", "123445");
        param.put("pageNum", 1);
        param.put("pageSize", 2);
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.vehicle.carAssoQueryPriceIndex";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("查询税局状态url："+url);
        System.out.println("查询税局状态body："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("查询税局状态："+jsonObject.toJSONString());
    }
    /**
     * vin结果
     */
    @Test
    public void Vinresult() {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        List list = new ArrayList<>();
        list.add("lvwe1223334");
        param.put("vinCodeList", list);
        param.put("userSign", "1234");
        param.put("brandName", "ad123");
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.vehicle.carAssoQueryVinResult";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("查询税局状态url："+url);
        System.out.println("查询税局状态body："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("查询税局状态："+jsonObject.toJSONString());
    }
    @Test
    public void xwCreditresult() {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91450900697633085P");
        param.put("creditCode", "91450900697633085P");
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.report.xwCreditReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("查询税局状态url："+url);
        System.out.println("查询税局状态body："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("查询税局状态："+jsonObject.toJSONString());
    }

    @Test
    public void checkV4() {
        Map newMap = new HashMap();
//        newMap.put("billingDate","2020-05-22");
//        newMap.put("totalAmount","14002.22");
//        newMap.put("invoiceNumber","49419834");
//        newMap.put("invoiceCode","032001900105");
//        newMap.put("checkCode","411854");
        newMap.put("billingDate","2023-05-30");
        newMap.put("totalAmount","2040269.83");
        newMap.put("invoiceNumber","23502000000008188696");
        newMap.put("invoiceCode","");
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("invoice",newMap);


        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.invoiceCheckv4";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("资产核验："+url);
        System.out.println("资产核验："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("资产核验："+jsonObject.toJSONString());
    }
    @Test
    public void checkV4temp() {
        Map newMap = new HashMap();
        newMap.put("billingDate","2023-02-13");
        newMap.put("totalAmount","57.00");
        newMap.put("invoiceNumber","20163899");
        newMap.put("invoiceCode","037002200311");
        newMap.put("checkCode","535092");
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("invoice",newMap);


        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.invoiceCheckv4";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("资产核验："+url);
        System.out.println("资产核验："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("资产核验："+jsonObject.toJSONString());
    }

    @Test
    public void querycheckV4temp() {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("batchNo", "457f818b9e7b3a9a27e0");


        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.queryBatchCheckv4";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("资产核验："+url);
        System.out.println("资产核验："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("资产核验："+jsonObject.toJSONString());
    }
}
