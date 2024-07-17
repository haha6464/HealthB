package huice.accompaniment.gatewayservice.filter;

import cn.dev33.satoken.stp.StpUtil;
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
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;

import static huice.accompaniment.common.constant.GatewayConstantPool.AUTHORIZE_TOKEN;

/**
 * @author welsir
 */
@Component
@Data
@RefreshScope
@ConfigurationProperties("api")
@Slf4j
public class LaxAuthorizeFilter implements GlobalFilter, Ordered, InitializingBean {

    private HashSet<String> laxTokenApi = new HashSet<String>();

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("laxTokenApi=" + laxTokenApi);
    }

    @SneakyThrows
    @Override
    @LogTime(funcName = "LaxAuthorizeFilter")
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if (laxTokenApi.contains(path)) {
            // 获取请求头
            HttpHeaders headers = request.getHeaders();
            System.out.println(headers);
            // 请求头中获取令牌
            String token = headers.getFirst(AUTHORIZE_TOKEN);
            ServerHttpRequest newRequest;
            try {
                String loginID = (String) StpUtil.getLoginIdByToken(token);
                if (StpUtil.isLogin(loginID)) {
                    String[] vars = loginID.split("\\|");
                    if (vars.length == 2) {
                        String uid = vars[0];
                        String username = vars[1];
                        newRequest = request.mutate().header("uid", uid).header("username", username).build();
                        return chain.filter(exchange.mutate().request(newRequest).build());
                    }
                }
            } catch (Exception e) {
                log.error("token解析失败:%s", e.getMessage());
            }
            newRequest = request.mutate().header("uid", "").header("username", "").build();
            return chain.filter(exchange.mutate().request(newRequest).build());
        }

        // 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

