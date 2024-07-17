package com.example.config.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 14:17
 */
@Configuration
public class OSSConfig {

    @Value("${oss.handler}")
    private String oss;

    public String getOss() {
        return oss;
    }

    public void setOss(String oss) {
        this.oss = oss;
    }
}
