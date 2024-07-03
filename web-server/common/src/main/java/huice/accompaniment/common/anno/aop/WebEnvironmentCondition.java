package huice.accompaniment.common.anno.aop;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
public class WebEnvironmentCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String webApplicationType = environment.getProperty("spring.main.web-application-type");
        if(webApplicationType!= null && webApplicationType.equals("reactive")){
            return false;
        }
        return true; // 判断为 Web 环境
    }
}
