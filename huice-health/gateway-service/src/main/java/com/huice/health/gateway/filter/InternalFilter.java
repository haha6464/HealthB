package com.huice.health.gateway.filter;

import com.huice.health.gateway.anno.LogTime;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/29 23:04
 */
@Component
@Data
@RefreshScope
@Slf4j
@ConfigurationProperties("api")
public class InternalFilter implements GlobalFilter, Ordered, InitializingBean {

    private static HashSet<String> internalApi = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("internalApi=%s", internalApi);
    }

    @SneakyThrows
    @LogTime(funcName = "InternalFilter")
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getURI().getPath();
        if (internalApi.contains(path)) {
            //7. 响应中放入返回的状态吗, 没有权限访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //8. 返回
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
