package huice.accompaniment.gatewayservice.filter;

import huice.accompaniment.gatewayservice.pojo.DO.RequestRecordDO;
import huice.accompaniment.gatewayservice.anno.LogTime;
import huice.accompaniment.gatewayservice.service.RequestRecordService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@RefreshScope
@Slf4j
public class LoggingFilter implements GlobalFilter, Ordered {
    @Resource
    RequestRecordService requestRecordService;

    @Resource(name = "taskExecutor")
    ThreadPoolTaskExecutor taskExecutor;

    @Override
    @LogTime(funcName = "LoggingFilter")
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        String url = request.getURI().getPath();
        String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
        log.info("request url: %s, remote address: %s,time: %s", url, remoteAddress.getHostName(), dateTime);
        taskExecutor.submit(()->{
            requestRecordService.insertRequestRecord(new RequestRecordDO(String.valueOf(UUID.randomUUID()),remoteAddress.getHostName(),url,dateTime));
        });
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }

}

