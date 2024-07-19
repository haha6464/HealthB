package huice.accompaniment.common.utils.snowflake;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import huice.accompaniment.common.config.SnowFlakeConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/14 11:05
 */
@Component
public class Snowflake implements Serializable, IdGenerator<Long> {

    private static final long serialVersionUID = 1L;

    private static final long WORKER_ID_BITS = 5L;

    private static final long DATA_CENTER_ID_BITS = 5L;

    // 最大支持机器节点数0~31，一共32个
    @SuppressWarnings({"PointlessBitwiseExpression", "FieldCanBeLocal"})
    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);

    // 最大支持数据中心节点数0~31，一共32个
    @SuppressWarnings({"PointlessBitwiseExpression", "FieldCanBeLocal"})
    private static final long MAX_DATA_CENTER_ID = -1L ^ (-1L << DATA_CENTER_ID_BITS);

    // 序列号12位（表示只允许workId的范围为：0-4095）
    private static final long SEQUENCE_BITS = 12L;

    // 机器节点左移12位
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    // 数据中心节点左移17位
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    // 时间毫秒数左移22位
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    // 序列掩码，用于限定序列最大值不能超过4095
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 允许的时钟回拨毫秒数
     */
    private long timeOffset;
    private long workerId;
    private long dataCenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    private long epoch;
    @Resource
    private ServiceIdGenerator serviceIdGenerator;
    @Resource
    SnowFlakeConfig snowFlakeConfig;

    @PostConstruct
    private void init() throws InterruptedException {
        this.timeOffset = snowFlakeConfig.getTimeOffset();
        this.workerId = Assert.checkBetween(serviceIdGenerator.workId(), 0, MAX_WORKER_ID);
        this.dataCenterId = Assert.checkBetween(serviceIdGenerator.dataCenterId(), 0, MAX_DATA_CENTER_ID);
        this.epoch = snowFlakeConfig.getEpoch();
    }

    private synchronized long generateId() {
        long curTimeStamp = System.currentTimeMillis();

        if (curTimeStamp < this.lastTimestamp) {
            if (this.lastTimestamp - curTimeStamp < timeOffset) {
                curTimeStamp = this.lastTimestamp;
            } else {
                throw new IllegalStateException(StrUtil.format("Clock moved backwards. Refusing to generate id for {}ms", lastTimestamp - curTimeStamp));
            }
        }

        if (curTimeStamp == this.lastTimestamp) {
            final long sequence = (this.sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                curTimeStamp = tilNextMillis(lastTimestamp);
            }
            this.sequence = sequence;
        } else {
            this.sequence = 0L;
        }
        this.lastTimestamp = curTimeStamp;
        return ((curTimeStamp - epoch) << TIMESTAMP_LEFT_SHIFT) | (dataCenterId << DATA_CENTER_ID_SHIFT) | (workerId << WORKER_ID_SHIFT) | sequence;
    }

    @Override
    public Long generate() {
        return this.generateId();
    }

    /**
     * 循环等待下一个时间
     *
     * @param lastTimestamp 上次记录的时间
     * @return 下一个时间
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        // 循环直到操作系统时间戳变化
        while (timestamp == lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        if (timestamp < lastTimestamp) {
            // 如果发现新的时间戳比上次记录的时间戳数值小，说明操作系统时间发生了倒退，报错
            throw new IllegalStateException(StrUtil.format("Clock moved backwards. Refusing to generate id for {}ms", lastTimestamp - timestamp));
        }
        return timestamp;
    }
}
