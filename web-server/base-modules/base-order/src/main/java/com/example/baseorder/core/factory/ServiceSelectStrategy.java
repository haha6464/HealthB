package com.example.baseorder.core.factory;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.hash.Hash;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.example.baseorder.core.strategy.GoodsStrategy;
import com.example.baseorder.pojo.GoodsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 15:41
 */
@Component
public class ServiceSelectStrategy {

    private static Logger log = LoggerFactory.getLogger(ServiceSelectStrategy.class);

    @Autowired
    private ApplicationContext applicationContext;
    private static final HashMap<Integer,String> SERVICE_STRATEGY_MAP = new HashMap<>();

    private static Map<String, GoodsStrategy> strategyGoodsMap;
    @PostConstruct
    public void init(){
        strategyGoodsMap = applicationContext.getBeansOfType(GoodsStrategy.class);
        log.info(strategyGoodsMap.size()+" bean instance be detected");
        log.info(strategyGoodsMap.toString());
        for (GoodsType value : GoodsType.values()) {
            String beanName = value.getStrategy().getSimpleName();
            char[] ch = beanName.toCharArray();
            ch[0]+=32;
            SERVICE_STRATEGY_MAP.put(value.getCode(), new String(ch));
        }
        log.info(SERVICE_STRATEGY_MAP.size()+" strategy be scan...");
    }

    public GoodsStrategy selectGoodStrategy(int code){
        String beanName = SERVICE_STRATEGY_MAP.get(code);
        Assert.notNull(beanName,"No name was found to the code:"+code+", please check the code!");
        GoodsStrategy strategy = strategyGoodsMap.get(beanName);
        Assert.notNull(strategy,"No strategy bean was found, please check the beanName:"+beanName);
        return strategy;
    }
}
