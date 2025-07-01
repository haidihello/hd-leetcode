package com.leetcode.editor.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.jasypt.util.text.BasicTextEncryptor;

public class KaiserUtil {
    // 用来加密的密码e1a2s3y4p5a6y7
    public static final char[] ENCODE_PASSWORD = {101, 49, 97, 50, 115, 51, 121, 52, 112, 53, 97, 54, 121, 55};
    public static void main(String[] args) {
        //e12ed5eeeb94a5340af60691f7b34660

//        userName: tianlubill
//        password: 123456
        print("B2B_READ");
        print("s*QE6p$vOgFHOtqEjH");

        print("M5N.ib3LW_3TTyru");
        System.out.println("//----------------------------旧用户密码信息--------------------------//");
        print("xxl_job");
        print("TK6dv^j@^~BQ");
        print("PHOENIX_B2B");
        print("mggz+ZV%9sr@+$3aW+(z%2#xqAPq");
        print("B2BC");
        print("Qt0AL5_65c");
        print("b2bread");
        print("B2b^reaD!01");
        print("paycode");
        print("L>,zgFO01isg");
        print("b2brisk");
        print("Risk_kGRjVh18w");
        print("B2BO");
        print("cB7_1sL55.q");
        print("PHOENIX_POSP");
        print("HeKOyn@\\azNF5Yx&");
        print("READONLY_TIANLU_DP");
        print("A(8J%XJgDq(#b+*xM5x#Y@Yb-");
        print("mapai");
        print("lCZEEAJHF7%h");
        System.out.println("//----------------------------新用户密码信息--------------------------//");
        // mysql
        print("xxl_job_b2b");
        print("^Wa35=R!kLo1#*Hk");
        // paycode
        print("paycode");
        print("Ee2*!>gM,l03aA");
        //b2brisk
        print("b2brisk");
        print("bWy1K.6q_4QGf");
        System.out.println("//----------------------------结束--------------------------//");
    }
    public static void print(String source) {
        String target = encrypt(source);
        String decrypt = decrypt(target);
        if(StrUtil.equals(target,decrypt)) {
            System.out.println("!~~~~~!!!加密密码可能有问题");
        }
        System.out.println(StrUtil.padAfter(source, 50, ' ') + "->" + JasyptDetector.DEF_PREFIX +  target + JasyptDetector.DEF_SUFFIX);
    }

    /**
     * 使用默认密码加密
     * @param data 需要加密的数据
     * @return 返回值
     */
    public static String encrypt(String data) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(genPassword());
        return textEncryptor.encrypt(data);
    }

    /**
     * 使用默认密码解密
     * @param data 需要解密的数据
     * @return 返回值
     */
    public static String decrypt(String data) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(genPassword());
        return textEncryptor.decrypt(data);
    }

    /**
     * 生成密码
     * @return /
     */
    public static String genPassword(){
        return DigestUtil.md5Hex(StrUtil.reverse(new String(ENCODE_PASSWORD)).toUpperCase());
    }
}
