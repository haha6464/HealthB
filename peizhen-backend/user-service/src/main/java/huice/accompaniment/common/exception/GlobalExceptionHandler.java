package huice.accompaniment.common.exception;

import huice.accompaniment.common.log.AbstractLogger;
import huice.accompaniment.pojo.ResultCodeEnum;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author welsir
 * @Date 2023/12/4 13:38
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    AbstractLogger logger;

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleException(HttpServletRequest request,
                                          Exception ex) {
        ResponseEntity result;
        //系统异常
        if (ex instanceof BaseException) {
            BaseException se = (BaseException) ex;
            ResultCodeEnum resultCode = se.getResultCode();
            if (resultCode == null) {
                result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(se.getMessage());
            } else {
                result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultCode);
            }
        }
        //参数错误
        else {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
        logger.error("基本异常,异常类:BaseException, url:%s,异常信息:%s", request.getServletPath(), result);
        return result;
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(HttpServletRequest request,
                                      BindException ex){
        String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        logger.error("参数绑定异常,异常类:BindException, url:%s, 异常信息:%s", request.getServletPath(),message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
