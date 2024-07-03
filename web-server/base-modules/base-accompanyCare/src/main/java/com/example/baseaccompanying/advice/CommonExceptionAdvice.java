package com.example.baseaccompanying.advice;

import com.alibaba.fastjson2.JSONArray;
import huice.accompaniment.common.core.ResponseVo;
import huice.accompaniment.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Doge2077 2024/6/18
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionAdvice {

    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = CommonException.class)
    public String customException(CommonException e) {
        log.error("请求异常，message:{}", e.getMessage());
        return JSONArray.toJSONString(new ResponseVo<>(e.getMessage(), e.getCode(), "200"));
    }

    /**
     * 未定义的系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String Exception(Exception e) {
        log.error("请求异常，message:{}, cause:{}", e.getMessage(), e.getCause());
        return JSONArray.toJSONString(new ResponseVo<>(e.getMessage(), "系统异常", "200"));
    }

}
