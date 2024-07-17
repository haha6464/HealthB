package com.example.baseaccompanying.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.baseaccompanying.dao.ServeToOrderListMapper;
import com.example.baseaccompanying.service.ServeToOrderListService;
import huice.accompaniment.common.domain.ServeToOrderList;
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
