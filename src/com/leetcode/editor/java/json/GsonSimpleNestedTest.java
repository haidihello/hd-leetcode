package com.leetcode.editor.java.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

public class GsonSimpleNestedTest {

    private Gson gson = new Gson();

    /**
     * {"nick":"A","mobile":"B","inner":{"id":101,"name":"tom"}}
     * GsonSimpleNested(nick=A, mobile=B, inner=GsonSimpleInner(id=101, name=tom))
     * {
     * "nick": "A",
     * "mobile": "B",
     * "inner": {
     * "id": 101,
     * "name": "tom"
     * }
     * }
     */
    @Test
    public void testGsonSimpleNested() {
        GsonSimpleInner in = new GsonSimpleInner();
        in.setId(199);
        in.setName("jfasda");
        GsonSimpleNested gs = new GsonSimpleNested();
        gs.setMobile("A");
        gs.setInner(in);
        gs.setMobile("Gjh");
        // json 序列化
        String json = gson.toJson(gs);
        System.out.println(json);
        // json 反序列化
        GsonSimpleNested u = gson.fromJson(json, GsonSimpleNested.class);
        System.out.println(u);

        // 采用格式 输出 json
        gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(gs));
    }


}