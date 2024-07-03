package com.example.baseorder.pojo;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 16:30
 */
public enum OrderStatus {

    PAY_WAIT("待支付",0),
    PAY_READY("已支付",1),
    PAY_REFUND_PART("部分退款",2),
    PAY_REFUND_ALL("全额退款",3),
    PAY_ORDER_COMMIT("处理中",4),
    PAY_ORDER_FINISH("已完成",5);

    private String status;
    private Integer code;

    OrderStatus(String status,Integer code){
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
