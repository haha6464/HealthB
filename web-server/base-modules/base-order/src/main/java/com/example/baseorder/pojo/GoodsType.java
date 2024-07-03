package com.example.baseorder.pojo;

import com.example.baseorder.core.strategy.GoodsStrategy;
import com.example.baseorder.core.strategy.JoinGroupStrategy;
import com.example.baseorder.core.strategy.VIPOneToOneStrategy;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 14:45
 */
public enum GoodsType {

    VIP_SERVICE("一对一_vip",0, VIPOneToOneStrategy.class),
    JOIN_GROUP("拼团",1, JoinGroupStrategy.class);

    private Integer code;
    private String des;

    private Class<? extends GoodsStrategy> strategy;

    GoodsType(String name, Integer code,Class<? extends GoodsStrategy> clazz){
        this.code = code;
        this.des = name;
        this.strategy = clazz;
    }

    public Integer getCode() {
        return code;
    }

    public Class<? extends GoodsStrategy> getStrategy() {
        return strategy;
    }

    public void setStrategy(Class<? extends GoodsStrategy> strategy) {
        this.strategy = strategy;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public static GoodsType fromCode(int code) {
        for (GoodsType status : GoodsType.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }

    public static String fromName(int code){
        for (GoodsType status : GoodsType.values()) {
            if (status.getCode() == code) {
                return status.name();
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }
}
