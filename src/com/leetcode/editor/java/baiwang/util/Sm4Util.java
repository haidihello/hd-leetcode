package com.leetcode.editor.java.baiwang.util;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

public class Sm4Util {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";

    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    public static final int DEFAULT_KEY_SIZE = 128;

    //生成ECB暗号
    private static Cipher generateEcbCipher(String algorithmName,int mode,byte[] key) throws Exception{
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key,ALGORITHM_NAME);
        cipher.init(mode,sm4Key);
        return cipher;
    }

    //自动生成秘钥
    public static byte[] generateKey() throws Exception{
        return generateKey(DEFAULT_KEY_SIZE);
    }


    public static byte[] generateKey(int keySize) throws Exception{
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize,new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    //加密
    public static String encryptEcb(String hexKey,String paramStr) throws Exception{
        String cipherText = "";
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        byte[] srcData = paramStr.getBytes("UTF-8");
        byte[] cipherArray = encrypt_Ecb_Padding(keyData,srcData);
        cipherText = toHexString(cipherArray);
        return cipherText;
    }

    //加密模式之Ecb
    public static byte[] encrypt_Ecb_Padding(byte[] key,byte[] data) throws Exception{
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING,Cipher.ENCRYPT_MODE,key);
        return cipher.doFinal(data);
    }


    //sm4解密
    public static String decryptEcb(String hexKey,String cipherText) throws Exception{
        String decryptStr = "";
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        byte[] srcData = decrypt_Ecb_Padding(keyData,cipherData);
        decryptStr = new String(srcData,ENCODING);
        return decryptStr;
    }

    //解密
    public static byte[] decrypt_Ecb_Padding(byte[] key,byte[] cipherText) throws Exception{
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING,Cipher.DECRYPT_MODE,key);
        return cipher.doFinal(cipherText);
    }

    //加密数据校验
    public static boolean verifyEcb(String hexKey,String cipherText,String paramStr) throws Exception{
        boolean flag = false;
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        byte[] decryptData = decrypt_Ecb_Padding(keyData,cipherData);
        byte[] srcData = paramStr.getBytes(ENCODING);
        flag = Arrays.equals(decryptData,srcData);
        return flag;
    }

    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String toHexString(byte[] input)
    {
        StringBuffer sb = new StringBuffer();
        String result = "";
        for (int i = 0; i < input.length; i++)
        {
            sb.append(HEX_CHARS[(input[i] >>> 4) & 0x0f]);
            sb.append(HEX_CHARS[(input[i]) & 0x0f]);

        }
        return sb.toString();
    }
}
