package com.example.baseorder.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description 服务商品实体类
 * @Author welsir
 * @Date 2024/7/1 16:14
 */
@Data
@Builder
@TableName("hc_goods")
public class GoodsDO {

    private Long id;
    private Long createBy;
    private Long updateBy;
    private String name;
    private String description;
    private BigDecimal price;
    private String picture;
    private int typeId;
    private Long doctorId;
    private Long hospitalId;
    private int remainNum;
    private LocalDate updateTime;
    private LocalDate createTime;
    private Boolean deleteFlag;
}
