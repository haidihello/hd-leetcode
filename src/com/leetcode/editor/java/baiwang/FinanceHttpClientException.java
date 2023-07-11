package com.leetcode.editor.java.baiwang;

/**
 * @author zhoutao
 * @desc
 * @date 2019/2/18
 */
public class FinanceHttpClientException extends RuntimeException {

    /**
     * 请求流水号，用于错误追踪，建议出错时日志记录
     */
    private String requestId;

    private String code;

    private String message;

    public FinanceHttpClientException(String code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public FinanceHttpClientException(String requestId, String code, String message){
        super(message);
        this.requestId = requestId;
        this.code = code;
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
