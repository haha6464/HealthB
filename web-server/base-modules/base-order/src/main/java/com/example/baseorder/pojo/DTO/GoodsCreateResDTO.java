package com.example.baseorder.pojo.DTO;

import com.example.baseorder.pojo.DO.GoodsDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/2 12:57
 */
@Data
public class GoodsCreateResDTO {

    private Long id;
    private String name;
    private String description;
    private String picture;
    private BigDecimal price;
    private int goodsNum;
    private int serviceType;

    public static GoodsCreateResDTO convertGoodsDO(GoodsDO goodsDO){
        GoodsCreateResDTO res = new GoodsCreateResDTO();
        res.setId(goodsDO.getId());
        res.setName(goodsDO.getName());
        res.setDescription(goodsDO.getDescription());
        res.setPrice(goodsDO.getPrice());
        res.setServiceType(goodsDO.getTypeId());
        res.setPicture(goodsDO.getPicture());
        res.setGoodsNum(goodsDO.getRemainNum());
        return res;
    }
}
