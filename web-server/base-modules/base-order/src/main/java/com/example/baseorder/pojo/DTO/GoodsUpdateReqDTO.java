package com.example.baseorder.pojo.DTO;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/2 14:07
 */
@Data
public class GoodsUpdateReqDTO {

    private Long goodsId;
    private Long userId;
    private String name;
    private String description;
    private BigDecimal price;
    private String picture;
    private int goodsNum;

}
