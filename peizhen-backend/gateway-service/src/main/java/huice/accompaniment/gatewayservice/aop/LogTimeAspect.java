package huice.accompaniment.gatewayservice.aop;

import huice.accompaniment.gatewayservice.anno.LogTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/29 22:54
 */
@Component
@Aspect
@Slf4j
public class LogTimeAspect {


    @Pointcut("@annotation(huice.accompaniment.gatewayservice.anno.LogTime)")
    public void LogTime() {

    }

    @Around("LogTime()")
    private Mono<Void> logGatewayHandleTime(final ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Mono<Void> result = (Mono<Void>) point.proceed();
            return result;
        } finally {
            long endTime = System.currentTimeMillis();
            Method targetMethod = ((MethodSignature) point.getSignature()).getMethod();
            Class<?>[] paramTypes = targetMethod.getParameterTypes();
            LogTime anno = point.getTarget().getClass().getDeclaredMethod(point.getSignature().getName(), paramTypes)
                    .getAnnotation(LogTime.class);

            ServerHttpRequest request = ((ServerWebExchange) (point.getArgs()[0])).getRequest();
            InetSocketAddress remoteAddress = request.getRemoteAddress();
            String url = request.getURI().getPath();
            String funcName = anno.funcName();
            log.info("[Filter Time] request url: %s, remote address: %s,funcName: %s,time: %sms", url, remoteAddress.getHostName(), funcName, endTime - startTime);
        }
    }
}
