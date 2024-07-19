package com.huice.health.user.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/3 11:12
 */
@TableName("hc_user")
@Data
public class UserDO {

    private String uid;
    private String username;
    private String password;
    private String role;
}
