package com.example.baseaccompanying.service;

import org.springframework.stereotype.Service;

/**
 * @author Doge2077 2024/7/10
 */
@Service
public interface ServeToOrderListService {
    /**
     * 根据 serve_item_id 获取该服务的销量
     *
     * @param serveId 服务id
     * @return 销量
     */
    Long getSoldByServeId(Long serveId);
}
