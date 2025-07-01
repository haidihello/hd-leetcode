package com.leetcode.editor.util;

import cn.hutool.core.util.StrUtil;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

/**
 * 易生通用属性加密配置类
 *
 * @date 2024/11/3 13:38
 * @author Pandans
 */
@Getter
@Setter
public class JasyptDetector implements EncryptablePropertyDetector {
    public static final String DEF_PREFIX = "EZP(";
    public static final String DEF_SUFFIX = ")";
    private String prefix;
    private String suffix;
    public JasyptDetector() {
        this(DEF_PREFIX, DEF_SUFFIX);
    }
    public JasyptDetector(String prefix, String suffix) {
        Assert.notNull(prefix, "Prefix can't be Null");
        Assert.notNull(suffix, "Suffix can't be Null");
        this.prefix = DEF_PREFIX;
        this.suffix = DEF_SUFFIX;
    }
    /**
    *判断配置文件中的数据是否是按这里指定前后缀组装的
    **/
    @Override
    public boolean isEncrypted(String message) {
        if (StrUtil.isBlank(message)) {
            return false;
        } else {
            String trimmedValue = message.trim();
            return trimmedValue.startsWith(this.prefix) && trimmedValue.endsWith(this.suffix);
        }
    }
    @Override
    public String unwrapEncryptedValue(String message) {
        // 获取到 上面方法返回true的数据， 此处原数据返回，不作处理，统一在DefaultEncryptor处理
        return message;
    }
}
