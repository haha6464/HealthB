package com.example.baseorder.service.Impl;

import com.example.baseorder.core.factory.ServiceSelectStrategy;
import com.example.baseorder.core.strategy.GoodsStrategy;
import com.example.baseorder.mapper.OrderMapper;
import com.example.baseorder.pojo.DTO.OrderCreateReqDTO;
import com.example.baseorder.pojo.DTO.OrderCreateResDTO;
import com.example.baseorder.pojo.GoodsType;
import com.example.baseorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 15:00
 */
@Service
public class OrderServiceImpl implements OrderService {

    private ServiceSelectStrategy strategy;
    private OrderMapper orderMapper;
    @Autowired
    public OrderServiceImpl(ServiceSelectStrategy strategy,OrderMapper orderMapper){
        this.orderMapper = orderMapper;
        this.strategy = strategy;
    }

    @Override
    public OrderCreateResDTO createOrder(OrderCreateReqDTO reqDTO) {
        //查看对应服务类型合法性
        GoodsType type = GoodsType.fromCode(reqDTO.getTypeCode());
        GoodsStrategy goodsStrategy = strategy.selectGoodStrategy(type.getCode());
        return (OrderCreateResDTO) goodsStrategy.choiceStrategy(reqDTO);
    }

    @Override
    public Boolean deleteOrderById(Long id) {
        int i = orderMapper.deleteById(id);
        return true;
    }
}
