package huice.accompaniment.common.utils.snowflake;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/14 11:06
 */
public interface ServiceIdGenerator{

    long workId() throws InterruptedException;

    long dataCenterId();

}
