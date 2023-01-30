package com.leetcode.editor.java.test;

import com.alibaba.fastjson2.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


public class StringTest {
//    public static void main(String[] args) {
//        try {
//            InetAddress inetAddress = InetAddress.getLocalHost();
//            System.out.println(inetAddress.getHostAddress());
//            return;
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }finally {
//            System.out.println("finally");
//        }
//    }
//public static void main(String[] args) {
//    String str = " {\"rspCode\":\"00\",\"rspMsg\":\"成" +
//            "功\",\"data\":[{\"dictKey\":\"enable\",\"dictValue\":\"0\",\"dictName\":\"正常\",\"sort\":1},{\"dictKey\":\"disable\",\"dictValue\":\"1\",\"dictName\":\"禁用\",\"sort\":2},{\"dictKey\":\"sleep\",\"dictValue\":\"2\",\"dictName\":\"睡眠\",\"sort\":3},{\"dictKey\":\"canceled\",\"dictValue\":\"3\",\"dictName\":\"注销\",\"sort\":4}]}";
//    JSONObject jsonObject = JSONObject.parseObject(str);
//    String rspCode =  Optional.ofNullable(jsonObject)
//            .flatMap(js -> Optional.ofNullable(js.getString("datae"))).orElse(new String());
//
//    Optional optional = Optional.ofNullable(jsonObject);
////    List data =  Optional.ofNullable(jsonObject)
////            .flatMap(js -> Optional.ofNullable(js.getString("data")));
////     Object rspMsg =  Optional.empty().orElseGet((Supplier<?>) jsonObject.get("rspMsg"));
//    System.out.println(rspCode);
//    System.out.println();
//}
public static void main(String[] args) {
    System.out.println(3 ^ 4);
    System.out.println(2 ^ 2);
    System.out.println(3*0.1 == 0.3);
}
}
