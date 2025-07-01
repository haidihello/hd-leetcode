package com.leetcode.editor.java.yisheng;

import com.leetcode.editor.util.KaiserUtil;

import java.util.function.Function;

public class DecryptUtil {

    public static String decryptValue(String encryptedValue, Function<String, String> decryptFunc) {
        if (encryptedValue == null || !encryptedValue.startsWith("EZP(")) {
            return encryptedValue; // 非加密值直接返回
        }

        // 提取括号内的 base64 字符串
        String cipherText = encryptedValue.substring(4, encryptedValue.length() - 1);

        // 使用 KaiserUtil 解密
        return decryptFunc.apply(cipherText);
    }

    public static void main(String[] args) {
        String encryptedUser = "EZP(j4FBczyTRgTZPAiGw9++TQ==)";
        String encryptedPass = "EZP(2I8xVXJ8HSnDE71iBWq44DfUFbpgkLcv)";

        // 假设你已经有 KaiserUtil.decrypt 方法
        String username = decryptValue(encryptedUser, KaiserUtil::decrypt);
        String password = decryptValue(encryptedPass, KaiserUtil::decrypt);

        System.out.println("Decrypted Username: " + username);
        System.out.println("Decrypted Password: " + password);
    }
}