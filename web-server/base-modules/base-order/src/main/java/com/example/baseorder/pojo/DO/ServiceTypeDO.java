package com.example.baseorder.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/2 13:25
 */
@Data
@TableName("hc_service_type")
public class ServiceTypeDO {
    private Long id;
    private String description;
}
