package com.huice.health.common.utils.snowflake;

import com.huice.health.common.domain.RedisKey;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/17 13:37
 */
@Component("snowflake-register-redis")
@ConditionalOnProperty("spring.redis.host")
public class RedisSnowflakeRegister implements ServiceIdGenerator {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final RedisKey WORK_ID_KEY = new RedisKey("assistant:snowflake:workId");
    private static final RedisKey DATACENTER_ID_KEY = new RedisKey("assistant:snowflake:datacenterId");
    private static final RedisKey LOCK;
    private static final int MAX_WORKER_ID = 32;
    private static final int MAX_DATACENTER_ID = 32;

    static {
        LOCK = new RedisKey("assistant:snowflake:lock", 1L, TimeUnit.MINUTES);
    }

    private void tryLock() throws InterruptedException {
        if (!Boolean.TRUE.equals(this.stringRedisTemplate.opsForValue().setIfAbsent(LOCK.getKey(new Object[0]), "1", LOCK.getTime(), LOCK.getTimeUnit()))) {
            Thread.sleep(10L);
            this.tryLock();
        }
    }

    private void unlock() {
        this.stringRedisTemplate.delete(LOCK.getKey(new Object[0]));
    }

    @PostConstruct
    public void init() {
        this.stringRedisTemplate.opsForValue().setIfAbsent(WORK_ID_KEY.getKey(new Object[0]), "0");
        this.stringRedisTemplate.opsForValue().setIfAbsent(DATACENTER_ID_KEY.getKey(new Object[0]), "0");
    }

    @Override
    public long workId() throws InterruptedException {
        long workId;
        try {
            this.tryLock();
            Long increment = Optional.ofNullable(this.stringRedisTemplate.opsForValue().increment(WORK_ID_KEY.getKey(new Object[0]))).orElse(0L);
            if (increment == 0L) {
                throw new IllegalStateException("redis generate workId error,please check redis config");
            }

            workId = increment - 1L;
            if (workId == 32L) {
                this.stringRedisTemplate.opsForValue().increment(DATACENTER_ID_KEY.getKey(new Object[0]));
                this.stringRedisTemplate.opsForValue().set(WORK_ID_KEY.getKey(new Object[0]), "0");
                workId = 0L;
                long var4 = workId;
                return var4;
            }
        } finally {
            this.unlock();
        }

        return workId;
    }

    @Override
    public long dataCenterId() {
        String incrementStr = this.stringRedisTemplate.opsForValue().get(DATACENTER_ID_KEY.getKey(new Object[0]));
        if (incrementStr == null) {
            throw new IllegalStateException("redis generate dataCenterId error,please check redis config");
        } else {
            long increment = Long.parseLong(incrementStr);
            return increment >= 32L ? 0L : increment;
        }
    }
}
