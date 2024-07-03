package huice.accompaniment.common.anno.aop;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import huice.accompaniment.common.anno.apiAuth.InternalApi;
import huice.accompaniment.common.anno.apiAuth.LaxTokenApi;
import huice.accompaniment.common.anno.apiAuth.WhiteApi;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.yaml.snakeyaml.Yaml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 获取注解将api过滤规则名单设置到Nacos服务上面
 * Lax-Token接口: 基于LaxTokenApi.class注解实现
 * White-Api接口: 基于WhiteApi.class注解实现
 * Internal-Api接口: 基于InternalApi.class注解实现
 */
@Component
@Conditional(WebEnvironmentCondition.class)
public class ApiAuthRegister implements InitializingBean {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    private final Map<String,Class<? extends Annotation>> anoMap = new HashMap(){{
        put("whiteApi", WhiteApi.class);
        put("laxTokenApi", LaxTokenApi.class);
        put("internalApi", InternalApi.class);
    }};

    @Value("${spring.cloud.nacos.server-addr}")
    private String NACOS_SERVER_ADDRESS;

    @Value("${spring.cloud.nacos.username}")
    private String NACOS_USERNAME;

    @Value("${spring.cloud.nacos.password}")
    private String NACOS_PASSWORD;

    @Value("${spring.cloud.nacos.config.namespace}")
    private String NACOS_NAMESPACE;


    private static final String NACOS_GROUP = "DEFAULT_GROUP";

    @Value("${spring.profiles.active}")
    private String SERVER_ACTIVE;
    private static String  NACOS_DATAID = "authority-api-%s.yaml";

    @Autowired(required = false)
    public ApiAuthRegister(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(this.requestMappingHandlerMapping == null){
            return;
        }
        //      根据环境构建api配置文件
        NACOS_DATAID = String.format(NACOS_DATAID, SERVER_ACTIVE);
        //      获取存在配置
        Properties properties = new Properties();
        properties.put("username",NACOS_USERNAME);
        properties.put("password",NACOS_PASSWORD);
        properties.put("serverAddr", NACOS_SERVER_ADDRESS);
        properties.put("namespace",NACOS_NAMESPACE);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig(NACOS_DATAID, NACOS_GROUP, 5000);
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap = config==null?new HashMap<>():yaml.load(config);
        Map<String, Object> apiMap = (Map<String, Object>) yamlMap.get("api");
        StringBuilder sb = new StringBuilder("api:  ");
        anoMap.forEach(
                (k,v)->{
                    List<String> apiList;
                    if(apiMap==null||!apiMap.containsKey(k)){
                        apiList = new ArrayList<>();
                    }else{
                        apiList = (List<String>) apiMap.get(k);
                    }
                    String res = buildRequestUrl(k, v, apiList);
                    if(StringUtils.hasText(res)){
                        sb.append("\n ").append(res);
                    }
                }
        );
        configService.publishConfig(NACOS_DATAID, NACOS_GROUP, sb.toString(), ConfigType.YAML.getType());
    }

    private String buildRequestUrl(String apiName, Class<? extends Annotation> anoClass, List<String> apiList) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo requestMappingInfo = entry.getKey();
            Method method = entry.getValue().getMethod();
            if (method.isAnnotationPresent(anoClass)) {
//              对于占位符替换为*
                String replacedPattern = requestMappingInfo.getActivePatternsCondition().toString().replaceAll("\\{[^}]+\\}", "*");
                String apiUrl = replacedPattern.substring(1,replacedPattern.length()-1);
//              添加不存在的接口
                if (!apiList.contains(apiUrl)) {
                    apiList.add(apiUrl);
                }
            }
        }
        if (apiList.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(apiName+": \n");
        for (String value : apiList) {
            sb.append("    - ").append(value).append("\n");
        }
        return sb.toString();
    }
}
