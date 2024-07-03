package com.example.baseorder.pojo.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description 这里需要对接中台支付接口 合并订单id等信息，拼凑返回dto实体类
 * @Author welsir
 * @Date 2024/7/1 14:57
 */
@Data
@Builder
public class OrderCreateResDTO {

    private Long orderId;
    private int payType;
    private LocalDate createTime;
    private BigDecimal price;
    private Long goodsId;
    private String goodsName;
    private Integer resource;

}
