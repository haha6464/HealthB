package com.huice.health.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huice.health.common.domain.ServeToOrderList;
import com.huice.health.manage.mapper.ServeToOrderListMapper;
import com.huice.health.manage.service.ServeToOrderListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Doge2077 2024/7/10
 */
@Service
public class ServeToOrderListServiceImpl implements ServeToOrderListService {

    @Resource
    ServeToOrderListMapper serveToOrderListMapper;

    @Override
    public Long getSoldByServeId(Long serveId) {
        LambdaQueryWrapper<ServeToOrderList> queryWrapper = Wrappers.<ServeToOrderList>lambdaQuery()
                .eq(ServeToOrderList::getServeId, serveId);
        return this.serveToOrderListMapper.selectCount(queryWrapper);
    }
}
