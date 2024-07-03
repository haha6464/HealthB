package com.example.baseorder.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.baseorder.pojo.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 16:29
 */
@Data
@Builder
@TableName("hc_order")
public class OrderDO {
    private Long id;
    private Long goodsId;
    private Long userId;
    private Long patientId;
    private int goodsType;
    private BigDecimal price;
    private String location;
    private Integer resource;
    private Integer orderStatus;
    private Integer payType;
    private LocalDate createTime;
    private LocalDate updateTime;
    private LocalDate startTime;
    private LocalDate finishTime;
    private Boolean hasDelay;
    private Boolean deleteFlag;
}
