package com.example.baseorder.pojo;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 16:12
 */
public enum PayType {

    WECHAT_PAY("微信支付",101);

    private Integer code;
    private String pay;

    PayType(String name, Integer code){
        this.code = code;
        this.pay = name;
    }

}
