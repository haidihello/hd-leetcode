package com.leetcode.editor.util;

import org.jasypt.encryption.StringEncryptor;

/**
 * 加解密处理类
 *
 * @date 2024/11/3 13:55
 * @author Pandans
 */
public class JasyptEncryptor implements StringEncryptor {

    private final JasyptDetector propertyDetector;

    public JasyptEncryptor() {
        propertyDetector = new JasyptDetector();
    }

    /**
     * 这里是加密方法，我们不在这里加密，原参数返回
     **/
    @Override
    public String encrypt(String encryptMessage) {
        return encryptMessage;
    }

    /**
     * 凯撒解密
     **/
    @Override
    public String decrypt(String decryptMessage) {
        // unwrapEncryptedValue方法返回的数据在这里处理
        String prefix = propertyDetector.getPrefix();
        String suffix = propertyDetector.getSuffix();
        int prefixIndex = decryptMessage.indexOf(prefix);
        int suffixIndex = decryptMessage.indexOf(suffix);
        // 截取括号中间部分，例如：EZP(邻居小玲) 里面的：邻居小玲
        decryptMessage = decryptMessage.substring(prefixIndex + prefix.length(), suffixIndex);
        // 做凯撒解密：加解密公共方法，请往后看
        return KaiserUtil.decrypt(decryptMessage);
    }
}
