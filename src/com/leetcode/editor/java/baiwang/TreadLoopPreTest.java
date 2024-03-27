package com.leetcode.editor.java.baiwang;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.leetcode.editor.util.yrt.signTopRequest;

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
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91440400MA527ALC5N");
        param.put("creditCode", "91440400MA527ALC5N");
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
        param.put("taxNo", "91440400MA527ALC5N");
        param.put("creditCode", "91440400MA527ALC5N");
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
    public void abnoramlPagef() throws Exception{
        appKey="1003084";
        appSecret="17cf4015-44db-4f70-ba5e-9fb970beb5ff";
        userName="37930083";
        password="vfmpuz";
        orgNo="43ddadbc3c9d82ac68bb";

        Map param = new HashMap();
        param.put("batchNo", "468dbddba609c58d0693");
        param.put("orgNo", "43ddadbc3c9d82ac68bb");
        Long time = System.currentTimeMillis();
        StringBuilder sburl = new StringBuilder(url);
        sburl.append("?method=baiwang.oauth.token");
        sburl.append("&username="+userName);
        sburl.append("&password="+password);
        sburl.append("&client_id="+appKey);
        sburl.append("&client_secret="+appSecret);
        sburl.append("&grant_type=password");
        sburl.append("&version="+version);
        sburl.append("&timestamp="+time);
        String tokenURL = sburl.toString();
        System.out.println("token授权url："+tokenURL);

        JSONObject tokenparam = new JSONObject();
        tokenparam.put("username",userName);
        tokenparam.put("password",password);
        tokenparam.put("client_secret",appSecret);

        String result = HttpClientUtil.doPostJson(tokenURL,tokenparam.toJSONString());
        JSONObject resultObj = JSON.parseObject(result);
        System.err.println("开放平台返回结果 \n" +resultObj);

        //接口报错处理
        if(resultObj.containsKey("errorResponse")){
            System.err.println("交易异常：请求url \n" +url);
            System.err.println("交易异常：请求json \n" +tokenparam);
            System.err.println("交易异常：返回result \n" +result);
            String requestId = resultObj.getString("requestId");
            JSONObject errorResponse = resultObj.getJSONObject("errorResponse");
            String code = StringUtils.isEmpty(errorResponse.getString("subCode"))?
                    errorResponse.getString("code"):errorResponse.getString("subCode");
            String message = errorResponse.getString("message") + " " + errorResponse.getString("subMessage");
            throw new FinanceHttpClientException(requestId, code, message);
        }
        String token = String.valueOf(resultObj.getJSONObject("response"));
        System.out.println("token 授权返回："+resultObj.getJSONObject("response"));


        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.queryBatchCheckv4";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        // 添加协议级请求参数
        Long requtime = System.currentTimeMillis();
        StringBuilder resburl = new StringBuilder(url);
        sburl.append("?method="+apiName);
        sburl.append("&version="+version);
        sburl.append("&appKey="+appKey);
        sburl.append("&format=json");
        sburl.append("&timestamp="+requtime);
        sburl.append("&token="+token);
        sburl.append("&type=sync");
        // 添加签名参数
            Map textParams = new HashMap();
            // 添加协议级请求参数
            textParams.put("method", apiName);
            textParams.put("version", version);
            textParams.put("appKey", appKey);
            textParams.put("format", "json");
            textParams.put("timestamp", requtime+"");
            textParams.put("token", token);
            textParams.put("type", "sync");
            sburl.append("&sign="+signTopRequest(textParams, appSecret, body));
        System.out.println("关联交易求环："+resburl);
        System.out.println("关联交易求环："+body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环："+jsonObject.toJSONString());

    }


}
