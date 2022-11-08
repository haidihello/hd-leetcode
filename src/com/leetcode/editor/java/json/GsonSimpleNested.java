package com.leetcode.editor.java.json;

import lombok.Data;

/**
 * 嵌套类型的序列化
 * @author sunRainAmazing
 */
@Data
public class GsonSimpleNested {
    private String nick;
    private String mobile;
    private GsonSimpleInner inner;


}