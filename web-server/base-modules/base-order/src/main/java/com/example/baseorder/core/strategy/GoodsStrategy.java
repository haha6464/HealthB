package com.example.baseorder.core.strategy;

import com.example.baseorder.pojo.DTO.OrderCreateReqDTO;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 16:49
 */
public interface GoodsStrategy {

    Object handle(OrderCreateReqDTO reqDTO);

    default Object choiceStrategy(OrderCreateReqDTO reqDTO) {
        return handle(reqDTO);
    }

}
