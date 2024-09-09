package com.leetcode.editor.java.baiwang;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * @author HaiDi
 * @since 2023-07-20 9:25
 */
public class Sign {
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
}
