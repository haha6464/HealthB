package com.example.baseorder.core.strategy;

import com.example.baseorder.pojo.DO.OrderDO;
import com.example.baseorder.pojo.DTO.OrderCreateReqDTO;
import com.example.baseorder.pojo.DTO.OrderCreateResDTO;
import com.example.baseorder.pojo.GoodsType;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 16:47
 */
public abstract class AbstractStrategy implements GoodsStrategy{

    @Override
    @Transactional
    public Object handle(OrderCreateReqDTO reqDTO) {
        //解耦 可以额外加处理逻辑
        //子类实现只需要考虑自己服务对应的业务逻辑即可，有抽象类来负责统一的中台调用逻辑并组装参数返回
        OrderDO orderDO = doHandle(reqDTO);
        //todo: 中台接口调用

        //TODO:订单写入mq做延迟订单

        OrderCreateResDTO response = OrderCreateResDTO.builder()
                .orderId(orderDO.getId())
                .price(orderDO.getPrice())
                .goodsId(orderDO.getGoodsId())
                .goodsName(GoodsType.fromName(orderDO.getGoodsType()))
                .payType(orderDO.getPayType())
                .createTime(orderDO.getCreateTime())
                .resource(orderDO.getResource())
                .build();
        return response;
    }

    protected abstract OrderDO doHandle(OrderCreateReqDTO reqDTO);
}
