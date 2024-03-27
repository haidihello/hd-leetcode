package com.leetcode.editor.java.baiwang;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoutao
 * @desc 需完善getToken对缓存的处理
 * @date 2019/2/18
 */
public class FinanceHttpClient {

    private String url;

    private String appKey;

    private String appSecret;

    private String userName;

    private String password;

    private String version;

    /**
     * 分布式缓存 建议redis，示例暂用本地map代替
     */
    private Map<String, String> map = new HashMap<>();


    public FinanceHttpClient(String url, String appKey, String appSecret, String userName, String password, String version) {
        this.url = url;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.userName = userName;
        this.password = password;
        this.version = version;
    }



    /**
     * token获取更新管理  需处理本地缓存
     * @return
     */
    private String getToken(){
        // 从缓存中获取token
        if(map.containsKey("access_token")){
            return map.get("access_token");
        }
        if(!map.containsKey("refresh_token")){
            //不存在 refresh_token，调用授权接口
            JSONObject jsonObject = authToken();
            token2Cache(jsonObject.getString("access_token"), jsonObject.getString("refresh_token"),
                    jsonObject.getInteger("expires_in"));
            return map.get("access_token");
        }
        JSONObject refreshObj = null;
        try {
            //刷新token
            refreshObj = refreshToken(map.get("refresh_token"));
        }catch (Exception e){
            //刷新token失败 重新授权
            refreshObj = authToken();
        }
        token2Cache(refreshObj.getString("access_token"), refreshObj.getString("refresh_token"),
                refreshObj.getInteger("expires_in"));
        return map.get("access_token");

    }

    /**
     * 示例使用本地map 需更新
     * @param access_token
     * @param refresh_token
     * @param expiresIn
     */
    private void token2Cache(String access_token, String refresh_token, Integer expiresIn) {
        //将 access_token refresh_token 放入缓存
        map.put("refresh_token", refresh_token);
        map.put("access_token", access_token);
        //设置 access_token 有效期  过期时间 - 提前刷新时间  expires_in 单位秒 提前刷新时间 建议5分钟
        int refreshTime = 5*60;
        expiresIn = expiresIn - refreshTime;
        //todo 设置 access_token 有效时间 本地map暂不处理
    }


    /**
     * 获取请求路径
     * @param apiName
     * @param body
     * @return
     */
    public String getAPIRequestURL(String apiName, String body)
    {
        // 添加协议级请求参数
        Long time = System.currentTimeMillis();
        StringBuilder sburl = new StringBuilder(url);
        sburl.append("?method="+apiName);
        sburl.append("&version="+version);
        sburl.append("&appKey="+appKey);
        sburl.append("&format=json");
        sburl.append("&timestamp="+time);
        sburl.append("&token="+getToken());
        sburl.append("&type=sync");
        // 添加签名参数
        try
        {
            Map textParams = new HashMap();
            // 添加协议级请求参数
            textParams.put("method", apiName);
            textParams.put("version", version);
            textParams.put("appKey", appKey);
            textParams.put("format", "json");
            textParams.put("timestamp", time+"");
            textParams.put("token", getToken());
            textParams.put("type", "sync");
            sburl.append("&sign="+signTopRequest(textParams, appSecret, body));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sburl.toString();
    }


    /**
     * 获取token请求URL
     * 注：获取后，可发送HTTP请求获取token
     * @return
     */
    public JSONObject authToken()
    {
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
        JSONObject responseObj = executePostJson(tokenURL, "");
        System.out.println("token 授权返回："+responseObj);
        return responseObj;
    }

    /**
     * 刷新token
     * @return
     */
    public JSONObject refreshToken(String refreshToken)
    {
        Long time = System.currentTimeMillis();
        StringBuilder sburl = new StringBuilder(url);
        sburl.append("?method=baiwang.oauth.token");
        sburl.append("&client_id="+appKey);
        sburl.append("&client_secret="+appSecret);
        sburl.append("&grant_type=refresh_token");
        sburl.append("&refresh_token="+refreshToken);
        sburl.append("&version="+version);
        sburl.append("&timestamp="+time);
        String tokenURL = sburl.toString();
        System.out.println("刷新token url："+url);
        JSONObject responseObj = executePostJson(tokenURL, "");
        System.out.println("刷新token返回："+responseObj );
        return responseObj;
    }

    public JSONObject executePostJson(String url, String json) {
        String result = HttpClientUtil.doPostJson(url,json);
        JSONObject resultObj = JSON.parseObject(result);
        System.err.println("开放平台返回结果 \n" +resultObj);

        //接口报错处理
        if(resultObj.containsKey("errorResponse")){
            System.err.println("交易异常：请求url \n" +url);
            System.err.println("交易异常：请求json \n" +json);
            System.err.println("交易异常：返回result \n" +result);
            String requestId = resultObj.getString("requestId");
            JSONObject errorResponse = resultObj.getJSONObject("errorResponse");
            String code = StringUtils.isEmpty(errorResponse.getString("subCode"))?
                    errorResponse.getString("code"):errorResponse.getString("subCode");
            String message = errorResponse.getString("message") + " " + errorResponse.getString("subMessage");
            throw new FinanceHttpClientException(requestId, code, message);
        }
        return resultObj.getJSONObject("response");
    }


    /**
     * 给BOP请求签名。
     *
     * @param params 所有字符型的BOP请求参数
     * @param secret 签名密钥
     * @param body 请求业务JSON数据
     * @return 签名
     */
    private String signTopRequest(Map<String, String> params, String secret, String body) throws IOException
    {

        // 第一步：检查参数是否已经排序
        ArrayList<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        query.append(secret);
        for (String key : keys)
        {
            String value = params.get(key);
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value))
            {
                query.append(key).append(value);
            }
        }
        body.replaceAll("\n","");
        body.replaceAll("\t","");
        body.replaceAll("\r","");
        query.append(body);
        query.append(secret);
        // 第三步：使用MD5加密
        byte[] bytes;
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("加签前参数："+query);

        bytes = md5.digest(query.toString().getBytes("UTF-8"));
        // 第四步：把二进制转化为大写的十六进制
        StringBuilder sign = new StringBuilder();
        for (byte b : bytes)
        {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1)
            {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * 获取token请求URL
     * 注：获取后，可发送HTTP请求获取token
     * @return
     */
    public JSONObject authTokenNew()
    {
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

        JSONObject param = new JSONObject();
        param.put("username",userName);
        JSONObject responseObj = executePostJson(tokenURL, param.toJSONString());
        System.out.println("token 授权返回："+responseObj);
        return responseObj;
    }
}
