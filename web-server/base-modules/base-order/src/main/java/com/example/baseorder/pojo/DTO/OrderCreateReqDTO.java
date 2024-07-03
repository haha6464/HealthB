package com.example.baseorder.pojo.DTO;

import com.example.baseorder.pojo.GoodsType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Description 订单创建请求参数
 * @Author welsir
 * @Date 2024/7/1 14:40
 */
@Validated
@Data
public class OrderCreateReqDTO {

    private Long userId;
    @Max(value = 11,message = "PhoneNum length error! please check the phoneNumber!")
    private String phoneNum;
    private Long goodsId;
    private Long patientId;
    private Long hospitalId;
    private String location;
    private Long departmentId;
    private int typeCode;
    private int payType;
    private Long doctorId;
    /*
     * 是否预约下单
     **/
    private Boolean hasDelay;
    /*
     * 订单来源
     **/
    private Integer resource;
    private String startTime;
    private String endTime;

}
