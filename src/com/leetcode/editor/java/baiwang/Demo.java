//package com.leetcode.editor.java.baiwang;
//
//
//import com.alibaba.fastjson2.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Demo {
//
//    // 开发者key，各个测试和生产环境需要单独申请。clientId 与 appKey 具体参数值是一样的
//    private static final String clientId = "开发者申请的appKey";
//    private static final String clientSecret = "dcc3121e-9c1c-4dc7-881d-xxxxxx";
//
//    // 公私钥keyMap
//    private static final Map<String, String> keyMap = new HashMap<>();
//
//    static {
//        // 自己的私钥
//        keyMap.put("ownPrivateKey", "160197e75efd97fd4d19054a9c43905aa31a6f1710ed6180019d58bb69f6a6cf");
//        // 自己公钥
//        keyMap.put("ownPublicKey", "0457bfb73757b6db6139b75be34f188bf869e6c664a42100ba302c3f0f32232e6e251221ab84bd4759c5f2768b67cb2bf45e8748513e5793d90e37549b6b4a53bb");
//        // 对方私钥
//        keyMap.put("oppositePrivateKey", "00db7114ab31f449a48231968f3b897dd599614e21574140c553135c375c09e5bb");
//        // 对方公钥
//        keyMap.put("oppositePublicKey", "041d6085ba8bc235132a8bbf7bd3ce1d15bca1af1a0fb48b1e777a04bbd423d56a5f403fdd33ad7f16772b90d2223dbaca81875831a0e40444eb07728216e7cb1e");
//    }
//
//    public static void main(String[] args) {
//        // 生成公私钥
//        //genderKey();
//
//        JSONObject requestJson = null;
//
//        // 1. 获取token接口, 请求示例
//        // requestJson = buildTokenParams();
//
//        /**
//         * 2. 接口文档上，每一个业务接口method名称
//         *    2.1 示例: 机构授权: winLending.finance.auth.corp
//         */
//        String reqMethod = "winLending.finance.auth.corp";
//        // 调用1. 获取token接口，解密后 response 节点下的access_token字段
//        String token = "7ed4cac9-bbeb-434f-990e-621f3d9a55fd";
//        requestJson = buildBusinessParams(reqMethod, clientId, clientSecret, token);
//        try {
//            // 请求加密，自己私钥 + 对方公钥
//            JSONObject encryptRequest = BwEncryptUtil.requestEncrypt(requestJson, keyMap.get("ownPrivateKey"), keyMap.get("oppositePublicKey"), BwEncryptUtil.generateKey());
//            System.out.println("加密后的密文: " + encryptRequest.toJSONString());
//
//            // ========================   调用接口，获取密文响应结果 result  ==================================================
//            // 密文数据
//            String result = "{\"method\":\"baiwang.oauth.token\",\"requestId\":\"57f3792b-e3e4-4432-bcef-0f6df4b7aba8\",\"response\":\"139450782a7ddc2d6ca1b6a1ca059e82bfbe7c8aeb5ce799bffadd18cdb1c06276bb3b26d6faf8b5be0c64a43ac72e0b8bf5190b0499a533f5cc16a6614801a82140332c62c8c90f561a71e50e258bf97fad316d9dbb85652eecddfd5636213b910464f0500af2d0cc3189c18beb7ece482ef8c485cc25c5a3535fdd4b4d6a07d2514d9c3b324a626dd3a0f1cebf666f354eb072e88d7eca39f21ea57ad491745ceae0f52c693c85a439d99307560a0d\",\"sign\":\"1bb954d680be92fd7c095383b868bc11e9418785d04ae7e23378a1f9c1e300e3ac8cbb13040b2944d4a38949e7b4515abf8f19e58f69100e8f4165ef90b19878\",\"key\":\"041fa652a0dc8bd25df5b304dd00d3c8d88b4fe092e1c74874412d5944a5ae8dce6af79c2f64897912092dfbbeeb052a9f0083c28d6de93e02a82f739b68583adebe318d51fa254afdca43da1e164655d214475c015fde8286ce256fbebc651323dcabb64074650764d61038a5f143985da67f8428e2691394af8b070025da46e0\"}";
//            JSONObject responseJson = JSONObject.parseObject(result);
//            // 解密响应密文数据，自己私钥 + 对方公钥
//            JSONObject data = BwEncryptUtil.responseDecrypt(responseJson, keyMap.get("ownPrivateKey"), keyMap.get("oppositePublicKey"));
//            System.out.println("解密后数据: " + data);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 生成公私钥
//     */
//    private static void genderKey() {
//        Map<String, String> keyMap = Sm2Util.generateKeyPair();
//        System.out.println("公钥: " + keyMap.get("publicKey"));
//        System.out.println("私钥: " + keyMap.get("privateKey"));
//    }
//
//    /**
//     * 构建获取token业务参数
//     */
//    private static JSONObject buildTokenParams() {
//        // 拼接业务请求参数
//        JSONObject requestJson = new JSONObject(true);
//        requestJson.put("method", "baiwang.oauth.token");
//        requestJson.put("client_id", clientId);
//        requestJson.put("grant_type", "password");
//        requestJson.put("version", "3.0");
//        requestJson.put("timestamp", System.currentTimeMillis());
//        JSONObject dataJson = new JSONObject();
//        dataJson.put("username", "xxxx");
//        dataJson.put("password", "xxxx");
//        dataJson.put("client_secret", clientSecret);
//        requestJson.put("data", dataJson);
//        return requestJson;
//    }
//
//    /**
//     * 构建非token其他的业务接口参数
//     */
//    private static JSONObject buildBusinessParams(String method, String appKey, String clientSecret, String token) {
//        JSONObject dataJson = new JSONObject(true);
//        dataJson.put("method", method);
//        dataJson.put("appKey", appKey);
//        dataJson.put("client_secret", clientSecret);
//        dataJson.put("version", "3.0");
//        dataJson.put("timestamp", System.currentTimeMillis());
//        dataJson.put("token", token);
//        // 1. 机构授权
//        dataJson.put("data", buildCorpAuthParams());
//        return dataJson;
//    }
//
//    /**
//     * {
//     *     "orgNo": "机构编号",
//     *     "corpName": "企业名称",
//     *     "creditCode": "企业统一信用代码",
//     *     "authFiles": [
//     *         [
//     *             "collectFile",
//     *             "BWS001 (销项数据授权合同编号)",
//     *             "2022-10-01 (授权有效期yyyy-MM-dd)"
//     *         ]
//     *     ]
//     * }
//     */
//    private static JSONObject buildCorpAuthParams() {
//        JSONObject json = new JSONObject(true);
//        // 机构编号
//        json.put("orgNo", "机构编号");
//        // 企业名称
//        json.put("corpName", "企业名称");
//        // 企业统一信用代码
//        json.put("creditCode", "企业统一信用代码");
//        // 构建授权文件参数
//        JSONArray array = new JSONArray();
//        JSONArray authData = new JSONArray();
//        // 授权类型: 固定值
//        authData.add("collectFile");
//        // 授权合同编号, 以BWS开头
//        authData.add("BWS001");
//        // 可以传空字符，默认一年有效期 authData.add("");
//        authData.add("2022-10-01"); // 也可以传指定到某个日期
//        array.add(authData);
//        json.put("authFiles", array);
//        System.out.println("授权请求参数: " + json.toJSONString());
//        return json;
//    }
//}