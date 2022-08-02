package com.leetcode.editor.util;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

/**
 * 3des加密解密
 */
public class ThreeDesUtil {
    private static String KEY="abcdefghijklmnopqrstuvwx";//24位
    private static String DES_ALGORITHM="DESede/ECB/PKCS5Padding";

    /**
     * 加密
     * @param message 要加密的字符串
     * @param key 加密密钥
     * @return
     */
    public static String desEncrypt(String message) {

        String result = ""; // DES加密字符串
        String newResult = "";// 去掉换行符后的加密字符串
        if(message!=null && !"".equals(message)){
            try {
                Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, generateKey(KEY)); // 设置工作模式为加密模式，给出密钥
                byte[] resultBytes = cipher.doFinal(message.getBytes("UTF-8")); // 正式执行加密操作
                BASE64Encoder enc = new BASE64Encoder();
                result = enc.encode(resultBytes);// 进行BASE64编码
                newResult = filter(result); // 去掉加密串中的换行符
            } catch (Exception e) {
                return result;
            }
//            log.debug("加密后的数据:" + newResult);
        }

        return newResult;
    }
    /**
     * 加密
     * @param message 要加密的字符串
     * @param key 加密密钥
     * @return
     */
    public static String desEncrypt(String message, String key) {
        //如果KEY为空，走默认KEY
        if(key==null || "".equals(key)){
            key=KEY;
        }
        String result = ""; // DES加密字符串
        String newResult = "";// 去掉换行符后的加密字符串
        if(message!=null && !"".equals(message)){
            try {
                Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, generateKey(key)); // 设置工作模式为加密模式，给出密钥
                byte[] resultBytes = cipher.doFinal(message.getBytes("UTF-8")); // 正式执行加密操作
                BASE64Encoder enc = new BASE64Encoder();
                result = enc.encode(resultBytes);// 进行BASE64编码
                newResult = filter(result); // 去掉加密串中的换行符
            } catch (Exception e) {
                return result;
            }
//            log.debug("加密后的数据:" + newResult);
        }

        return newResult;
    }

    /**
     * 解密
     * @param message 要解密的字符串
     * @param key 解密密钥
     * @return
     */
    public static String desDecrypt(String message, String key) {
        //如果KEY为空，走默认KEY
        if(key==null || "".equals(key)){
            key=KEY;
        }
        String result = "";
        if(message!=null && !"".equals(message)){
            try {
                BASE64Decoder dec = new BASE64Decoder();
                byte[] messageBytes = dec.decodeBuffer(message); // 进行BASE64编码
                Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, generateKey(key)); // 设置工作模式为解密模式，给出密钥
                byte[] resultBytes = cipher.doFinal(messageBytes);// 正式执行解密操作
                result = new String(resultBytes, "UTF-8");
            } catch (Exception e) {
                return result;
            }
//            log.debug("解密后的数据:" + result);
        }
        return result;
    }
    /**
     * 解密
     * @param message 要解密的字符串
     * @param key 解密密钥
     * @return
     */
    public static String desDecrypt(String message) {
        String result = "";
        if(message!=null && !"".equals(message)){
            try {
                BASE64Decoder dec = new BASE64Decoder();
                byte[] messageBytes = dec.decodeBuffer(message); // 进行BASE64编码
                Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, generateKey(KEY)); // 设置工作模式为解密模式，给出密钥
                byte[] resultBytes = cipher.doFinal(messageBytes);// 正式执行解密操作
                result = new String(resultBytes, "UTF-8");
            } catch (Exception e) {
                return result;
            }
//            log.debug("解密后的数据:" + result);
        }
        return result;
    }

    /**
     * 去掉加密字符串换行符
     *
     * @param str
     * @return
     */
    public static String filter(String str) {
        String output = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if (asc != 10 && asc != 13) {
                sb.append(str.subSequence(i, i + 1));
            }
        }
        output = new String(sb);
        return output;
    }
    private static SecretKey generateKey(String secretKey) throws NoSuchAlgorithmException,InvalidKeyException,InvalidKeySpecException {

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        DESedeKeySpec keySpec = new DESedeKeySpec(secretKey.getBytes());
        keyFactory.generateSecret(keySpec);
        return keyFactory.generateSecret(keySpec);
    }
    /*
     * public static void main(String[] args) {
     * String message ="01&130000000000000000&6220000000000000&张三&18888888888";
     * String key = "JAOEOIREUYHDNSMLQIEJALWQ";
     * String result =desEncrypt(message, key);
     * desDecrypt(result, key);
     * String aString = "";
     * System.out.println("".equals(aString)); }
     */
 /*   public static void main(String[] args) {
    	String key="ybcdefghijklmnopqrstuvwx";
    	String s1=desEncrypt("",key);
    	String s2=desDecrypt(s1, null);
    	System.out.println("s1="+s1);
    	System.out.println("s2"+s2);

//    	System.out.println(MyMD5Utils.encode("15120038557"));
//    	System.out.println(MyMD5Utils.decode(MyMD5Utils.encode("15120038557")));
	}*/

    /**
     * 获取加密字符串，判断是否加密:加密：直接返回，非加密：加密后返回
     * @param args
     */
    public static String getDesEncryptMsg(String message, String key){
        try {
            String result = desDecrypt(message, key);
            if(StringUtils.isBlank(result)){
                return desEncrypt(message, key);
            }
        } catch (Exception e) {
            return desEncrypt(message, key);
        }
        return message;
    }

    /**
     * 获取解密字符串，判断是否加密:加密：解密返回，非加密：直接返回返回
     * @param args
     */
    public static String getDesDecryptMsg(String message, String key){
        try {
            String result = desDecrypt(message, key);
            if(StringUtils.isBlank(result)){
                return message;
            } else {
                return result;
            }
        } catch (Exception e) {
            return message;
        }
    }

}

