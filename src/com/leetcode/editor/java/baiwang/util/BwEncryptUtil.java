package com.leetcode.editor.java.baiwang.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.util.Map;

public class BwEncryptUtil {

    /**
     * sm4对称加密秘钥
     */
    public static String generateKey() throws Exception {
        //SM4获取秘钥
        byte[] sm4Keybytes = null;
        try {
            sm4Keybytes = Sm4Util.generateKey();
        } catch (Exception e) {
            System.out.println("获取秘钥失败");
            e.printStackTrace();
            throw e;
        }
        String sm4Key = ByteUtils.toHexString(sm4Keybytes);
        return sm4Key;
    }

    /**
     * 获取公私钥
     *
     * @return map{privateKey,publicKey}
     */
    public static Map<String, String> generateKeyPair() {
        Map<String, String> map = Sm2Util.generateKeyPair();
        return map;
    }

    /**
     * 加密请求数据
     *
     * @param requestJson
     * @param ownPrivateKey     自有私钥
     * @param oppositePublicKey 对方公钥
     * @param sm4Key
     * @return
     * @throws Exception
     */
    public static JSONObject requestEncrypt(JSONObject requestJson, String ownPrivateKey, String oppositePublicKey, String sm4Key) throws Exception {
        //SM4数据加密
        JSONObject dataJson = requestJson.getJSONObject("data");
        //转换成json数据，否则可能出现 key=value的情况
        String data = dataJson.toJSONString();
        String sm4Data = null;
        try {
            sm4Data = Sm4Util.encryptEcb(sm4Key, data);
        } catch (Exception e) {
            System.out.println("sm4加密失败");
            e.printStackTrace();
            throw e;
        }
        //System.out.println("请求数据加密后  sm4Data = " + sm4Data);
        //替换明文请求数据
        requestJson.put("data", sm4Data);
        //System.out.println("秘钥加密前 sm4Key = " + sm4Key);
        String sm2Sm4Key = Sm2Util.encrypt(sm4Key, oppositePublicKey);
        //System.out.println("秘钥加密后 sm2Sm4Key = " + sm2Sm4Key);
        //替换明文sm4秘钥
        requestJson.put("key", sm2Sm4Key);
        //生成摘要
        String sm3Abstract = Sm3Util.sm3Digest(requestJson);
        //System.out.println("生成摘要 sm3Abstract = " + sm3Abstract);
        // 生成签名
        String sign = Sm2Util.sign(sm3Abstract, ownPrivateKey);
        requestJson.put("sign", sign);
        //System.out.println("发送请求 requestJson = " + requestJson.toString());
        return requestJson;
    }

    /**
     * 解密请求数据
     *
     * @param requestJson
     * @param ownPrivateKey     自有私钥
     * @param oppositePublicKey 对方公钥
     * @return
     * @throws Exception
     */
    public static JSONObject requestDecrypt(JSONObject requestJson, String ownPrivateKey, String oppositePublicKey) throws Exception {
        String requestSign = requestJson.getString("sign");
        //生成摘要
        String sm3Abstract = Sm3Util.sm3Digest(requestJson);
        //System.out.println("服务端生成摘要 sm3Abstract = " + sm3Abstract);
        //验签
        if (!Sm2Util.verify(sm3Abstract, requestSign, oppositePublicKey)) {
            System.out.println("验签失败");
        }
        //替换摘要
        requestJson.put("sign", sm3Abstract);
        //sm2解密秘钥
        String sm4Key = Sm2Util.decrypt(requestJson.getString("key"), ownPrivateKey);
        //System.out.println("解密后key sm4Key = " + sm4Key);
        //替换原sm4加密秘钥
        requestJson.put("key", sm4Key);
        //解密data数据
        String requestData = requestJson.getString("data");
        String decryptData = null;
        try {
            decryptData = Sm4Util.decryptEcb(sm4Key, requestData);
        } catch (Exception e) {
            System.out.println("sm4解密失败,秘钥解密失败");
            e.printStackTrace();
        }
        //替换原加密数据
        requestJson.put("data", decryptData);
        //System.out.println("解密后数据 decryptData = " + decryptData);
        return requestJson;
    }

    /**
     * 加密响应数据
     *
     * @param responseJson
     * @param ownPrivateKey     自有私钥
     * @param oppositePublicKey 对方公钥
     * @param sm4Key
     * @return
     * @throws Exception
     */
    public static JSONObject responseEncrypt(JSONObject responseJson, String ownPrivateKey, String oppositePublicKey, String sm4Key) throws Exception {
        //深拷贝
        JSONObject deepResponseJson = JSONObject.parseObject(responseJson.toJSONString());
        //SM4数据加密
        String response = deepResponseJson.getString("response");
        //response加密
        if (StringUtils.isNotBlank(response)) {
            System.out.println("请求数据加密前  response = " + response);
            String sm4Response = null;
            try {
                sm4Response = Sm4Util.encryptEcb(sm4Key, response);
            } catch (Exception e) {
                System.out.println("sm4解密失败");
                e.printStackTrace();
                throw e;
            }
            System.out.println("请求数据加密后  sm4Response = " + sm4Response);
            //替换明文响应数据
            deepResponseJson.put("response", sm4Response);
            System.out.println("秘钥加密前 sm4Key = " + sm4Key);
            String sm2Sm4Key = Sm2Util.encrypt(sm4Key, oppositePublicKey);
            System.out.println("秘钥加密后 sm2Sm4Key = " + sm2Sm4Key);
            //替换明文秘钥
            deepResponseJson.put("key", sm2Sm4Key);
        }
        //生成签名
        String sm3Abstract = Sm3Util.sm3Digest(deepResponseJson);
        System.out.println("生成签名 sm3Abstract = " + sm3Abstract);
        String sign = Sm2Util.sign(sm3Abstract, ownPrivateKey);
        deepResponseJson.put("sign", sign);
        System.out.println("发送响应 requestJson = " + deepResponseJson.toString());
        return deepResponseJson;
    }

    /**
     * 解密响应数据
     *
     * @param responseJson
     * @param ownPrivateKey     自有私钥
     * @param oppositePublicKey 对方公钥
     * @return
     * @throws Exception
     */
    public static JSONObject responseDecrypt(JSONObject responseJson, String ownPrivateKey, String oppositePublicKey) throws Exception {
        String responseSign = responseJson.getString("sign");
        //生成摘要
        String sm3Abstract = Sm3Util.sm3Digest(responseJson);
        //System.out.println("服务端生成摘要 sm3Abstract = " + sm3Abstract);
        //验签
        if (!Sm2Util.verify(sm3Abstract, responseSign, oppositePublicKey)) {
            System.out.println("验签失败");
            throw new RuntimeException();
        }
        //替换加密摘要
        responseJson.put("sign", sm3Abstract);
        String serverResponseResponse = responseJson.getString("response");
        //response不为空需要解密，否则只验签
        if (StringUtils.isNotBlank(serverResponseResponse)) {
            //sm2解密秘钥
            String serverSm4Key = Sm2Util.decrypt(responseJson.getString("key"), ownPrivateKey);
            //System.out.println("解密后key serverSm4Key = " + serverSm4Key);
            //替换原sm4加密秘钥
            responseJson.put("key", serverSm4Key);
            //解密response数据
            String serverResponse = null;
            try {
                serverResponse = Sm4Util.decryptEcb(serverSm4Key, serverResponseResponse);
            } catch (Exception e) {
                System.out.println("sm4解密失败");
                e.printStackTrace();
                throw e;
            }
            //替换响应数据
            try {
                JSONObject responseJsonObject = JSONObject.parseObject(serverResponse);
                responseJson.put("response", responseJsonObject);
            } catch (JSONException e) {
                JSONArray jsonArray = JSONObject.parseArray(serverResponse);
                responseJson.put("response", jsonArray);
            }
        }
        return responseJson;
    }
}

