package com.example.baseorder.service;

import com.example.baseorder.pojo.DTO.OrderCreateReqDTO;
import com.example.baseorder.pojo.DTO.OrderCreateResDTO;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 14:38
 */
public interface OrderService {

    OrderCreateResDTO createOrder(OrderCreateReqDTO reqDTO);

    Boolean deleteOrderById(Long id);
}
