package huice.accompaniment.common.anno.apiAuth;

import huice.accompaniment.common.domain.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/13 11:09
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleApi {

    Role role() default Role.USER;
}
