package com.leetcode.editor.java.baiwang.openplat;

import com.alibaba.fastjson.JSONObject;
import com.leetcode.editor.java.baiwang.util.BwEncryptUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HaiDi
 * @since 2023-12-21 10:44
 */
public class Encry {
    // 公私钥keyMap
    private static final Map<String, String> keyMap = new HashMap<>();
    static {
        // 自己的私钥
        keyMap.put("ownPrivateKey", "160197e75efd97fd4d19054a9c43905aa31a6f1710ed6180019d58bb69f6a6cf");
        // 自己公钥
        keyMap.put("ownPublicKey", "0457bfb73757b6db6139b75be34f188bf869e6c664a42100ba302c3f0f32232e6e251221ab84bd4759c5f2768b67cb2bf45e8748513e5793d90e37549b6b4a53bb");
        // 对方私钥
        keyMap.put("oppositePrivateKey", "00db7114ab31f449a48231968f3b897dd599614e21574140c553135c375c09e5bb");
        // 对方公钥
        keyMap.put("oppositePublicKey", "041d6085ba8bc235132a8bbf7bd3ce1d15bca1af1a0fb48b1e777a04bbd423d56a5f403fdd33ad7f16772b90d2223dbaca81875831a0e40444eb07728216e7cb1e");
    }
    public static void main(String[] args) throws Exception {
        // 密文数据
        String result = "{\"method\":\"baiwang.oauth.token\",\"requestId\":\"57f3792b-e3e4-4432-bcef-0f6df4b7aba8\",\"response\":\"139450782a7ddc2d6ca1b6a1ca059e82bfbe7c8aeb5ce799bffadd18cdb1c06276bb3b26d6faf8b5be0c64a43ac72e0b8bf5190b0499a533f5cc16a6614801a82140332c62c8c90f561a71e50e258bf97fad316d9dbb85652eecddfd5636213b910464f0500af2d0cc3189c18beb7ece482ef8c485cc25c5a3535fdd4b4d6a07d2514d9c3b324a626dd3a0f1cebf666f354eb072e88d7eca39f21ea57ad491745ceae0f52c693c85a439d99307560a0d\",\"sign\":\"1bb954d680be92fd7c095383b868bc11e9418785d04ae7e23378a1f9c1e300e3ac8cbb13040b2944d4a38949e7b4515abf8f19e58f69100e8f4165ef90b19878\",\"key\":\"041fa652a0dc8bd25df5b304dd00d3c8d88b4fe092e1c74874412d5944a5ae8dce6af79c2f64897912092dfbbeeb052a9f0083c28d6de93e02a82f739b68583adebe318d51fa254afdca43da1e164655d214475c015fde8286ce256fbebc651323dcabb64074650764d61038a5f143985da67f8428e2691394af8b070025da46e0\"}";
        JSONObject responseJson = JSONObject.parseObject(result);
        // 解密响应密文数据，自己私钥 + 对方公钥
        JSONObject data = BwEncryptUtil.responseDecrypt(responseJson, keyMap.get("ownPrivateKey"), keyMap.get("oppositePublicKey"));
        System.out.println("解密后数据: " + data);
    }
}
