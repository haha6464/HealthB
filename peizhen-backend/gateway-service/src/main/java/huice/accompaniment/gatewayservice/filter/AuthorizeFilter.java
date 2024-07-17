package huice.accompaniment.gatewayservice.filter;

import huice.accompaniment.common.utils.JwtUtil;
import huice.accompaniment.gatewayservice.anno.LogTime;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Map;

import static huice.accompaniment.common.constant.GatewayConstantPool.AUTHORIZE_TOKEN;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/29 23:00
 */
@Component
@Data
@RefreshScope
@Slf4j
@ConfigurationProperties("api")
public class AuthorizeFilter implements GlobalFilter, Ordered, InitializingBean {

    private HashSet<String> whiteApi = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("whiteApi=" + whiteApi);
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @SneakyThrows
    @Override
    @LogTime(funcName = "AuthorizeFilter")
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getURI().getPath();
        if (whiteApi.contains(path)) {
            // 获取请求头
            HttpHeaders headers = request.getHeaders();
            // 请求头中获取令牌
            String token = headers.getFirst(AUTHORIZE_TOKEN);
            System.out.println(token);

            try {
                ServerHttpRequest newRequest = null;
                Map<String, Object> analysis = JwtUtil.analysis(token);

                String loginID = (String) analysis.get("id");

                newRequest = request.mutate().header("uid", loginID).header("username", "qwe").build();
                return chain.filter(exchange.mutate().request(newRequest).build());
            } catch (Exception e) {
                e.printStackTrace();
                //10. 解析jwt令牌出错, 说明令牌过期或者伪造等不合法情况出现
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //11. 返回
                return response.setComplete();
            }
        }
        // 放行
        return chain.filter(exchange);
    }
}
