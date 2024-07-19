package com.huice.health.common.utils.snowflake;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/17 15:11
 */
public class CustomSnowflakeCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Environment env = context.getEnvironment();

        boolean enabled = Boolean.parseBoolean(env.getProperty("snowflake.enabled", "false"));

        boolean beanMissing = !beanFactory.containsBean("redisSnowflakeRegister");
        boolean beanExistsAndEnabled = beanFactory.containsBean("redisSnowflakeRegister") && enabled;

        return beanMissing || beanExistsAndEnabled;
    }
}
