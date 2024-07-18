package com.health.api.client.orderclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Doge2077 2024/7/18
 */
@FeignClient("order-service")
public interface testorder {
    @GetMapping("/order/test")
    public String test();
}
