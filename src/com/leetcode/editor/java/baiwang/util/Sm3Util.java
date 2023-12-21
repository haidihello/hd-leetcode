package com.leetcode.editor.java.baiwang.util;

import cn.hutool.crypto.SmUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Sm3Util {

    public static String sm3Digest(JSONObject jsonObject) {
        String signBefor = getParamStr(jsonObject);
        //System.out.println("参数拼接后字符串 signBefor = " + signBefor);
        String digestHex = SmUtil.sm3(signBefor);
        return digestHex;
    }

    private static String getParamStr(JSONObject jsonObject) {
        StringBuilder query = new StringBuilder();
        ArrayList<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            if ("sign".equals(key)) {
                continue;
            }
            String value = jsonObject.getString(key);
            if (!isNull(key) && !isNull(value)) {
                query.append(key).append(value);
            }
        }
        return query.toString();
    }

    private static boolean isNull(String str) {
        return (str == null || "".equals(str) ? true : false);
    }
}

