package com.huice.health.common.exception;

import com.huice.health.common.constant.ErrorInfo;

/**
 * 禁止操作异常
 * 适用场景：权限不足或非法操作
 *
 * @author Doge2077 2024/6/18
 */
public class ForbiddenOperationException extends CommonException {

    public ForbiddenOperationException() {
        this(ErrorInfo.Msg.FORBIDDEN_OPERATION);
    }

    public ForbiddenOperationException(String message) {
        super(ErrorInfo.Code.FORBIDDEN_OPERATION, message);
    }

    public ForbiddenOperationException(Throwable throwable, String message) {
        super(throwable, ErrorInfo.Code.FORBIDDEN_OPERATION, message);
    }

    public ForbiddenOperationException(Throwable throwable) {
        super(throwable, ErrorInfo.Code.FORBIDDEN_OPERATION, ErrorInfo.Msg.FORBIDDEN_OPERATION);
    }
}
