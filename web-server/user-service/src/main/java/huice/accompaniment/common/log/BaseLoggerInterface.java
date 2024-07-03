package huice.accompaniment.common.log;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/23 18:20
 */
public interface BaseLoggerInterface {

    void info(String msg,Object ...arg);

    void warn(String msg,Object ...arg);

    void trace(String msg,Object ...arg);

    void error(String msg,Object ...arg);


}
