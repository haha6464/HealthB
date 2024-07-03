package com.example.baseorder.pojo.DTO;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/2 12:21
 */
@Data
public class GoodsCreateReqDTO {
    private Long userId;
    @NotBlank(message = "goods name not be null!")
    private String name;
    @NotBlank(message = "goods desc not be null!")
    private String description;
    @Max(value = 999L,message = "price is up to max value! please reset the price!")
    private BigDecimal price;
    private int serviceId;
    @NotBlank(message = "picture url not be null!")
    private String picture;
    private Long hospitalId;
    private Long doctorId;
    private int goodsNum;
}
