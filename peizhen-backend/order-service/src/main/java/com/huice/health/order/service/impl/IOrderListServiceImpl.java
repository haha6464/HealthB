package com.huice.health.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huice.health.common.domain.OrderList;
import com.huice.health.order.mapper.OrderListMapper;
import com.huice.health.order.service.IOrderListService;
import org.springframework.stereotype.Service;

/**
 * @author Doge2077 2024/7/18
 */
@Service
public class IOrderListServiceImpl extends ServiceImpl<OrderListMapper, OrderList> implements IOrderListService {
}
