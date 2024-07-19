package com.huice.health.common.domain;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/17 13:40
 */
public class RedisKey {
    private String key;
    private long time;
    private TimeUnit timeUnit;

    public RedisKey(String key, long time, TimeUnit timeUnit) {
        this.key = key;
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public RedisKey(String key) {
        this.key = key;
        this.time = -1L;
        this.timeUnit = TimeUnit.SECONDS;
    }

    public String getKey(Object value) {
        return String.format(this.key, value);
    }

    public String getKey(Object... values) {
        return String.format(this.key, values);
    }

    public long getTime() {
        return this.time;
    }

    public TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public void setTime(final long time) {
        this.time = time;
    }

    public void setTimeUnit(final TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
