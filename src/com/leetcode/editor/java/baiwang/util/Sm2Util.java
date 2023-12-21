package com.leetcode.editor.java.baiwang.util;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.ECKeyUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Sm2Util {

    /**
     * 生成公私钥
     */
    public static Map<String, String> generateKeyPair() {
        Map<String, String> map = new HashMap<>();
        SM2 sm2 = SmUtil.sm2();
        byte[] privateKey = BCUtil.encodeECPrivateKey(sm2.getPrivateKey());
        byte[] publicKey = ((BCECPublicKey) sm2.getPublicKey()).getQ().getEncoded(false);
        map.put("privateKey", HexUtil.encodeHexStr(privateKey));
        map.put("publicKey", HexUtil.encodeHexStr(publicKey));
        return map;
    }

    /**
     * 公钥加密
     */
    public static String encrypt(String content, String publicKey) {
        SM2 sm2 = new SM2(null, ECKeyUtil.toSm2PublicParams(publicKey));
        return sm2.encryptHex(content, StandardCharsets.UTF_8, KeyType.PublicKey);
    }

    /**
     * 私钥解密
     */
    public static String decrypt(String encryptData, String privateKey) {
        SM2 sm2 = new SM2(ECKeyUtil.toSm2PrivateParams(privateKey), null);
        return sm2.decryptStr(encryptData, KeyType.PrivateKey, StandardCharsets.UTF_8);
    }

    /**
     * 私钥加签
     */
    public static String sign(String content, String privateKeyHex) {
        byte[] contentBytes = content.getBytes();
        SM2 sm2 = new SM2(privateKeyHex, null, null);
        sm2.usePlainEncoding();
        byte[] signBytes = sm2.sign(contentBytes, null);
        String sign = HexUtil.encodeHexStr(signBytes);
        return sign;
    }

    /**
     * 公钥验签
     */
    public static boolean verify(String content, String signHex, String publicKeyHex) {
        SM2 sm2 = new SM2(null, ECKeyUtil.toSm2PublicParams(publicKeyHex));
        sm2.usePlainEncoding();
        byte[] dataBytes = content.getBytes();
        boolean verify = sm2.verify(dataBytes, HexUtil.decodeHex(signHex));
        System.out.println("验签结果 verify=" + verify);
        return verify;
    }
}

