package huice.accompaniment.common.log;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/23 18:48
 */
@Component
public class AbstractLogger {

    @Resource(name = "${logger.handler}")
    private BaseLoggerInterface loggerHandler;

    public void info(String msg, Object... args) {
        loggerHandler.info(msg, args);
    }

    public void error(String msg, Object... args) {
        loggerHandler.error(msg, args);
    }

    public void error(Exception e) {
        loggerHandler.error("%s:%s", e.getMessage(), e.getStackTrace()[0]);
    }

    public void warn(String msg, Object... args) {
        loggerHandler.warn(msg, args);
    }

    public void trace(String msg, Object... args) {
        loggerHandler.trace(msg, args);
    }


}
