package com.huice.health.common.utils.snowflake;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/14 11:09
 */
@Component("snowflake-register-default")
@ConditionalOnProperty(name = "snowflake.enable", havingValue = "true")
@ConditionalOnMissingBean(RedisSnowflakeRegister.class)
public class DefaultSnowflakeRegister implements ServiceIdGenerator {

    @Value("${snowflake.workerId:0}")
    private long workerId;
    @Value("${snowflake.dataCenterId:0}")
    private long dataCenterId;

    @Override
    public long workId() {
        return workerId;
    }

    @Override
    public long dataCenterId() {
        return dataCenterId;
    }
}
