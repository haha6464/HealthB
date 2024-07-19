package com.huice.health.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author welsir
 * @Date 2023/12/4 14:43
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(500, "系统内部异常"),
    /**
     * 业务异常
     */
    USER_INFO_ERROR(510, "账号密码不正确"),
    GET_USER_INFO_ERROR(511, "获取用户信息失败"),
    MORE_ONE_DEFAULT_PATIENT(512, "默认就诊人只能为一个");
    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String msg;
}
