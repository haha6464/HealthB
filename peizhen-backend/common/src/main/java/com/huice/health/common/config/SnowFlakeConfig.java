package com.huice.health.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/17 11:06
 */
@Configuration
public class SnowFlakeConfig {

    @Value("${snowflake.workId:2000}")
    private Long timeOffset;
    @Value("${snowflake.epoch:1288834974657}")
    private Long epoch;

    public Long getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(Long timeOffset) {
        this.timeOffset = timeOffset;
    }

    public Long getEpoch() {
        return epoch;
    }

    public void setEpoch(Long epoch) {
        this.epoch = epoch;
    }
}
