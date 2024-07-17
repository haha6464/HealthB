package com.example.baseaccompanying.service;

import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.Serve;
import huice.accompaniment.common.domain.vo.ServePageVo;

import java.math.BigDecimal;

/**
 * 服务表(Serve)表服务接口
 *
 * @author Doge2077
 * @since 2024-06-06 14:43:00
 */
public interface ServeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Serve queryById(Long id);

    /**
     * 分页查询
     *
     * @param serve
     * @param page
     * @param size
     * @return
     */
    PageImpl queryByPage(Serve serve, Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param serve 实例对象
     * @return 实例对象
     */
    Serve insert(Serve serve);

    /**
     * 修改数据
     *
     * @param serve 实例对象
     * @return 实例对象
     */
    Serve update(Serve serve);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过主键上架服务
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean onSaleById(Long id);

    /**
     * 获取管理员服务列表
     *
     * @param page 页数
     * @param size 大小
     * @return 服务列表
     */
    PageImpl<?> adminGetServeList(Integer page, Integer size);

    /**
     * 管理员发布服务
     *
     * @param serveItemId 服务项id
     * @param hospitalId  医院id
     * @param servePrice  服务价格
     * @param onSaleFlag  是否上架
     * @return 服务
     */
    Serve adminPublishServe(Long serveItemId, Long hospitalId, BigDecimal servePrice, Integer onSaleFlag);

    /**
     * 管理员上架服务
     *
     * @param id 服务id
     * @return 服务
     */
    Serve adminOnSaleServeById(Long id);

    /**
     * 下架服务
     *
     * @param id 服务id
     * @return 是否成功
     */
    boolean offSaleById(Long id);

    /**
     * 管理员通过服务名称和服务状态搜索
     *
     * @param serveName   服务名
     * @param serveStatus 服务状态
     * @param page        页数
     * @param size        大小
     * @return 分页服务列表和分页大小
     */
    PageImpl<ServePageVo> adminFindServeByNameAndStatus(String serveName, Integer serveStatus, Integer page, Integer size);
}
