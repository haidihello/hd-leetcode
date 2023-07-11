package com.leetcode.editor.java.test;

import com.alibaba.fastjson2.JSONObject;
import com.google.gson.JsonObject;

import java.util.Optional;

public class JsonObjectTest  {
    public static void main(String[] args) {
        String json = "{\"foo\": 1, \"bar\": [10, \"apples\"], \"baz\": \"or not\"}";
        JSONObject jsonObject =JSONObject.parseObject(json);
//        System.out.println(jsonObject.get("foo"));
//        System.out.println(jsonObject.get("bar"));
//        System.out.println(jsonObject.get("baz"));
//        String foo = jsonObject.getString("foo1");
//        System.out.println(foo);
//        Students student = new Students();
//        student = null;
//       String sult =  JSONObject.toJSONString(student);
//        System.out.println(sult);
        String dataFlag = Optional.ofNullable(jsonObject)
                .map(obj -> obj.getString("bar"))
                .orElse("");
        System.out.println(dataFlag);

    }
}
