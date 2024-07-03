package huice.accompaniment.common.anno.apiAuth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要进行验证Token的接口（必须）
 * 包含正确形式的token返回uid,username
 * 不包含不通过则直接返回401
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WhiteApi {
}
