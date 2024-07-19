package com.huice.health.order.controller.inner;

import cn.hutool.core.bean.BeanUtil;
import com.huice.health.api.order.OrderApi;
import com.huice.health.api.order.dto.response.OrderListResDTO;
import com.huice.health.common.domain.OrderList;
import com.huice.health.order.service.IOrderListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Doge2077 2024/7/18
 */
@RestController
@RequestMapping("/inner/order")
public class InnerOrderListController implements OrderApi {

    @Resource
    private IOrderListService orderListService;

    /**
     * 根据订单id查询订单
     * @param id
     * @return 订单
     */
    @Override
    public OrderListResDTO queryById(Long id) {
        OrderList orderList = this.orderListService.getById(id);
        return BeanUtil.toBean(orderList, OrderListResDTO.class);
    }
}
