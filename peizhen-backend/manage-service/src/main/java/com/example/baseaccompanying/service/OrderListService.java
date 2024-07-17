package com.example.baseaccompanying.service;

import huice.accompaniment.common.domain.OrderList;
import huice.accompaniment.common.domain.bo.AdminGetListDataBo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 订单(OrderList)表服务接口
 *
 * @author Yida Yang
 * @since 2024-06-14 14:51:34
 */
public interface OrderListService {
    public String adminGetListData(AdminGetListDataBo adminGetListDataBo);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderList queryById(Long id);

    /**
     * 分页查询
     *
     * @param orderList   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<OrderList> queryByPage(OrderList orderList, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orderList 实例对象
     * @return 实例对象
     */
    OrderList insert(OrderList orderList);

    /**
     * 修改数据
     *
     * @param orderList 实例对象
     * @return 实例对象
     */
    OrderList update(OrderList orderList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderList queryByIdForUser(Long id);

    /**
     * 分页查询
     *
     * @param orderList   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<OrderList> queryByPageForUser(OrderList orderList, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orderList 实例对象
     * @return 实例对象
     */
    OrderList insertForUser(OrderList orderList);

    /**
     * 修改数据
     *
     * @param orderList 实例对象
     * @return 实例对象
     */
    OrderList updateForUser(OrderList orderList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteByIdForUser(Long id);
}
