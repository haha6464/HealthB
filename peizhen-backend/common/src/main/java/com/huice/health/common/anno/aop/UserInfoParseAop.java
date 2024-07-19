package com.huice.health.common.anno.aop;

import com.huice.health.common.utils.ThreadLocalUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/5 13:36
 */
@Component
@Aspect
public class UserInfoParseAop {

    @Around("@annotation(com.huice.health.common.anno.apiAuth.WhiteApi)")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uid = request.getHeader("uid");
        String username = request.getHeader("username");
        if (uid == null || username == null) {
            return null;
        }
        ThreadLocalUtils.add(uid, username);
        Object res = pjp.proceed();
        ThreadLocalUtils.remove();
        return res;
    }
}
