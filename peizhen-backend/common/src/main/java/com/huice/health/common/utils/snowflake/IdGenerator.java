package com.huice.health.common.utils.snowflake;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/14 11:05
 */
public interface IdGenerator<T> {

    T generate();

    default T safeGenerate() {
        try {
            return this.generate();
        } catch (RuntimeException var2) {
            return null;
        }
    }
}
