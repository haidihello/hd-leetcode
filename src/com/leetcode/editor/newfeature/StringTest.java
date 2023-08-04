package com.leetcode.editor.newfeature;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

public class StringTest {
    public static void main(String[] args) {
        String ip = "";
        String creditRealIp = "1";
        String userRealIp = "2";
        if (StringUtils.isNotBlank(creditRealIp)) {
            ip = creditRealIp;
        } else if (StringUtils.isNotBlank(userRealIp)){
            ip = userRealIp;
        }
        System.out.println(ip);
    }

}
