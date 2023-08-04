package com.leetcode.editor.newfeature;

import com.alibaba.fastjson2.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.leetcode.editor.java.test.Personal;
import org.junit.Test;

import java.util.*;

/**
 * @Author: HaiDi
 * @Date: 2022/7/29 15:17
 */
public class    OptionalTest {
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    public static void main(String[] args) {
        tt();
        List<Student> students = new ArrayList<>(16);
        students.add(new Student("1", "张三", 18, "male", 88));
        students.add(new Student("1", "张四", 17, "male", 88));
        students.add(new Student("2", "李四", 17, "male", 60));
        students.add(new Student("3", "王五", 18, "male", 100));
        students.add(new Student("4", "赵六", 20, "male", 10));
        students.add(new Student("5", "董七", 14, "female", 95));
        students.add(new Student("6", "幺八", 21, "male", 55));
        students.add(new Student("7", "老九", 20, "female", 66));
        students.add(new Student("8", "小明", 18, "male", 100));
        students.add(new Student("9", "小红", 22, "female", 95));
        students.add(new Student("10", "小张", 25, "male", 90));

        Personal personal = new Personal();
        personal = null;
//        String rspCode =  Optional.ofNullable(jsonObject)
//            .flatMap(js -> Optional.ofNullable(js.getString("datae"))).orElse(new String());

        Optional<Personal> optional = Optional.ofNullable(personal);
        String result = Optional.ofNullable(personal)
                .filter(a -> a.getAge() > 5)
                .filter(a -> a.getName().isEmpty())
                .map(c -> c.getName())
                .orElse("defelt");
        System.out.println(result);
        System.out.println("-----------------------------");
        students = null;
        Optional<List<Student>> studentsOptional = Optional.ofNullable(students);
        List<List<Student>> lists = Optional.ofNullable(students)
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());
        System.out.println(lists);

          }
          public static void tt(String... test){
              System.out.println(test);
          }

    @Test
    public void getCode() {
        String result = "{\"rspCode\":\"00\",\"rspMsg\":\"成功\",\"data\":{\"totalCount\":2,\"pageSize\":10,\"totalPage\":1,\"currPage\":1,\"list\":[{\"channelCode\":\"SXY\",\"bankCode\":\"BOC\",\"bankName\":\"中国银行 \",\"cardType\":\"DEBI\n" +
                "T_CARD\",\"cost\":1,\"singleQuota\":5000000,\"dayQuota\":10000000,\"priority\":1,\"status\":\"enable\",\"operator\":\"zhanghaidi\",\"id\":3,\"createTime\":{\"date\":{\"year\":2022,\"month\":11,\"day\":4},\"time\":{\"hour\":14,\"minute\"\n" +
                ":55,\"second\":18,\"nano\":0}},\"updateTime\":{\"date\":{\"year\":2022,\"month\":11,\"day\":4},\"time\":{\"hour\":14,\"minute\":55,\"second\":20,\"nano\":0}}},{\"channelCode\":\"SXY\",\"bankCode\":\"BOBJ\",\"bankName\":\"北京银行\",\"card\n" +
                "Type\":\"DEBIT_CARD\",\"cost\":1,\"singleQuota\":5000000,\"dayQuota\":10000000,\"priority\":1,\"status\":\"enable\",\"operator\":\"zhanghaidi\",\"id\":4,\"createTime\":{\"date\":{\"year\":2022,\"month\":11,\"day\":4},\"time\":{\"hour\":\n" +
                "14,\"minute\":55,\"second\":18,\"nano\":0}},\"updateTime\":{\"date\":{\"year\":2022,\"month\":11,\"day\":4},\"time\":{\"hour\":14,\"minute\":55,\"second\":18,\"nano\":0}}}]}}";
        JSONObject jsonObject = JSON.parseObject(result);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("list");
        List<Map> recordList = jsonArray.toList(Map.class);

        Map<String, Object> resultMap = JSON.parseObject(result, HashMap.class);
        JSONObject jsonObject1 = (JSONObject) resultMap.get("data");
        List<Map> list = gson.fromJson(gson.toJson(jsonObject1.get("list")), new TypeToken<List<Map>>() {
        }.getType());
    }
}
