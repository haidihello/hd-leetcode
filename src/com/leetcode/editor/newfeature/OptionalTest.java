package com.leetcode.editor.newfeature;

import com.alibaba.fastjson2.JSONObject;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @Author: HaiDi
 * @Date: 2022/7/29 15:17
 */
public class OptionalTest {
    public static void main(String[] args) throws Exception{
        String str = " {\"rspCode\":\"00\",\"rspMsg\":\"成" +
                "功\",\"data\":[{\"dictKey\":\"enable\",\"dictValue\":\"0\",\"dictName\":\"正常\",\"sort\":1},{\"dictKey\":\"disable\",\"dictValue\":\"1\",\"dictName\":\"禁用\",\"sort\":2},{\"dictKey\":\"sleep\",\"dictValue\":\"2\",\"dictName\":\"睡眠\",\"sort\":3},{\"dictKey\":\"canceled\",\"dictValue\":\"3\",\"dictName\":\"注销\",\"sort\":4}]}";
        str = null;
        JSONObject jsonObject = JSONObject.parseObject(str);
        String rspCode = Optional.ofNullable(jsonObject)
                .flatMap(js -> Optional.ofNullable(js.getString("rspCode"))).orElse(new String());
        String data = Optional.ofNullable(jsonObject)
                .flatMap(js -> Optional.ofNullable(js.getString("data"))).orElse(new String());

        Optional optional = Optional.ofNullable(jsonObject);
//    List data =  Optional.ofNullable(jsonObject)
//            .flatMap(js -> Optional.ofNullable(js.getString("data")));
//     Object rspMsg =  Optional.empty().orElseGet((Supplier<?>) jsonObject.get("rspMsg"));
        System.out.println(rspCode);
        System.out.println(data);
    }
}
