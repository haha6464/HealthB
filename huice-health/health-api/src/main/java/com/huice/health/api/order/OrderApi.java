package com.huice.health.api.order;

import com.huice.health.api.order.dto.response.OrderListResDTO;
import com.huice.health.api.utils.MyQueryMapEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Doge2077 2024/7/18
 */
@FeignClient(value = "order-service", path = "/inner/order",configuration = MyQueryMapEncoder.class)
public interface OrderApi {
    /**
     * 根据订单id查询订单
     * @param id
     * @return
     */
    @GetMapping("/queryById")
    public OrderListResDTO queryById(@RequestParam("id") Long id);
}
