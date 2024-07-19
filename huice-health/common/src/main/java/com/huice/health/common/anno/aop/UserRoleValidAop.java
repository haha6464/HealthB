package com.huice.health.common.anno.aop;

import com.huice.health.common.anno.apiAuth.RoleApi;
import com.huice.health.common.domain.Role;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/13 11:10
 */
@Component
@Aspect
public class UserRoleValidAop {

    @Around("@annotation(com.huice.health.common.anno.apiAuth.RoleApi)")
    public Object checkRoleValid(ProceedingJoinPoint pjp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        int role;
        role = Integer.parseInt(request.getHeader("role"));
        Assert.notNull(role, "请求role参数为空");
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        RoleApi api = method.getAnnotation(RoleApi.class);
        Role realRole = api.role();
        if (realRole.getCode() != role) {
            throw new RuntimeException("未认证");
        }
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return proceed;
    }
}
