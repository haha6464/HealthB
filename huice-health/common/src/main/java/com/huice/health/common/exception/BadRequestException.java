package com.huice.health.common.exception;

import static com.huice.health.common.constant.ErrorInfo.Msg.REQUEST_FAILD;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

/**
 * 请求异常
 * 适用场景：请求参数不合法，频繁请求登
 *
 * @author Doge2077 2024/6/18
 */
public class BadRequestException extends CommonException {

    public BadRequestException() {
        this(REQUEST_FAILD);
    }

    public BadRequestException(String message) {
        super(HTTP_BAD_REQUEST, message);
    }

    public BadRequestException(Throwable throwable, String message) {
        super(throwable, HTTP_BAD_REQUEST, message);
    }

    public BadRequestException(Throwable throwable) {
        super(throwable, HTTP_BAD_REQUEST, REQUEST_FAILD);
    }
}
