package com.huice.health.user.common.exception;


import com.huice.health.user.pojo.ResultCodeEnum;

/**
 * @Description
 * @Author welsir
 * @Date 2023/12/4 13:38
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 5225171867523879342L;

    protected int code;

    protected String msg;

    protected Object[] params;

    protected ResultCodeEnum resultCode;

    public BaseException(String str, ResultCodeEnum message) {
        this.msg = str;
        this.resultCode = message;
    }

    public BaseException(Object object, ResultCodeEnum message) {
        params = new Object[]{object};
        this.resultCode = message;
    }

    public ResultCodeEnum getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }

    public BaseException(String message, Object[] params, Throwable cause) {
        super(message, cause);
        this.params = params;
    }

    public BaseException() {
        super();
    }

    public BaseException(ResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, int code) {
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String msg, Object[] params) {
        super(msg);
        this.params = params;
    }

    public BaseException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public BaseException(String msg, int code, Object[] params) {
        this(msg, code);
        this.params = params;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}
