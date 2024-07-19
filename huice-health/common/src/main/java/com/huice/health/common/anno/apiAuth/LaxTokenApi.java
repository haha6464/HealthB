package com.huice.health.common.anno.apiAuth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不严格要求用户登录的接口
 * 用户登录：返回uid与username
 * 用户未登录，放行
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LaxTokenApi {
}
