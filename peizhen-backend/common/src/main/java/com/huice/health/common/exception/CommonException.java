package com.huice.health.common.exception;

import cn.hutool.http.HttpStatus;
import com.huice.health.common.constant.ErrorInfo;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

/**
 * 通用异常
 *
 * @author Doge2077 2024/6/18
 */
public class CommonException extends RuntimeException {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public CommonException() {
        this.code = HTTP_BAD_REQUEST;
        this.message = ErrorInfo.Msg.PROCESS_FAILD;
    }

    public CommonException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonException(Throwable throwable, int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonException(String message) {
        this(HttpStatus.HTTP_INTERNAL_ERROR, message);
    }

}
