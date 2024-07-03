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
 * @Date 2024/7/1 15:55
 */
@Component
public class JoinGroupStrategy extends AbstractStrategy {

    private GoodsMapper goodsMapper;
    private OrderMapper orderMapper;
    private Snowflake snowflake;
    @Autowired
    public JoinGroupStrategy(GoodsMapper goodsMapper,OrderMapper orderMapper,Snowflake snowflake){
        this.goodsMapper=goodsMapper;
        this.orderMapper=orderMapper;
        this.snowflake=snowflake;
    }

    @Override
    public OrderDO doHandle(OrderCreateReqDTO reqDTO) {
        Long goodsId = reqDTO.getGoodsId();
        //TODO:不走缓存 后续加上
        //TODO:这里一共要查hospital、department、doctor 等待对应服务暴露接口可以改为接口的异步查询。提高接口响应速度
        //TODO:目前无法做参数校验正确性
        GoodsDO goodsDO = goodsMapper.selectGoodsIfRemainByJoinGroup(goodsId);
        Assert.notNull(goodsDO,"goods haven't enough nums!");
        //TODO:这一步可以抽象到父类中统一处理
        OrderDO order = OrderDO.builder()
                .id(snowflake.generate())
                .goodsId(goodsId)
                .userId(reqDTO.getUserId())
                .patientId(reqDTO.getPatientId())
                .price(goodsDO.getPrice())
                .goodsType(goodsDO.getTypeId())
                .resource(reqDTO.getResource())
                .orderStatus(OrderStatus.PAY_WAIT.getCode())
                .location(reqDTO.getLocation())
                .createTime(LocalDate.now())
                .startTime(LocalDate.now())
                //这个结束时间需要后续实际更改目前只是做一个状态
                .finishTime(LocalDate.now())
                .hasDelay(false)
                .deleteFlag(false).build();
        orderMapper.insert(order);
        return order;
    }
}
