package com.example.baseorder.core.strategy;

import com.example.baseorder.mapper.GoodsMapper;
import com.example.baseorder.mapper.OrderMapper;
import com.example.baseorder.pojo.DO.GoodsDO;
import com.example.baseorder.pojo.DO.OrderDO;
import com.example.baseorder.pojo.DTO.OrderCreateReqDTO;
import com.example.baseorder.pojo.OrderStatus;
import huice.accompaniment.common.utils.snowflake.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDate;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 16:07
 */
@Component
public class VIPOneToOneStrategy extends AbstractStrategy{


    private OrderMapper orderMapper;
    private GoodsMapper goodsMapper;
    private Snowflake snowflake;
    @Autowired
    public VIPOneToOneStrategy(OrderMapper orderMapper,GoodsMapper goodsMapper,Snowflake snowflake){
        this.orderMapper = orderMapper;
        this.goodsMapper = goodsMapper;
        this.snowflake = snowflake;
    }

    @Override
    protected OrderDO doHandle(OrderCreateReqDTO reqDTO) {
        GoodsDO goodsDO = goodsMapper.selectGoodsIfRemainByOneToOne(reqDTO.getGoodsId());
        Assert.notNull(goodsDO,"goods haven't enough nums!");
        OrderDO order = OrderDO.builder()
                .id(snowflake.generate())
//                .goodsId(goodsDO.getId())
//                .userId(reqDTO.getUserId())
//                .patientId(reqDTO.getPatientId())
//                .orderStatus(OrderStatus.PAY_WAIT.getCode())
//                .resource(reqDTO.getResource())
//                .location(reqDTO.getLocation())
//                .goodsType(goodsDO.getTypeId())
//                .price(goodsDO.getPrice())
//                .createTime(LocalDate.now())
//                .startTime(reqDTO.getStartTime())
//                .finishTime(reqDTO.getEndTime())
                .hasDelay(reqDTO.getHasDelay()).build();
        orderMapper.insert(order);
        return order;
    }
}
