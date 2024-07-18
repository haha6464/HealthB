package com.example.baseaccompanying.service;

import com.baomidou.mybatisplus.extension.service.IService;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.OrderList;
import huice.accompaniment.common.domain.bo.AdminGetListDataBo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 订单(OrderList)表服务接口
 *
 * @author Yida Yang
 * @since 2024-06-14 14:51:34
 */
public interface OrderListService extends IService<OrderList> {
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

    /**
     * 管理员获取订单列表
     * @param offset 页数
     * @param limit 大小
     * @return 订单列表
     */
    PageImpl<?> adminGetOrderList(Integer offset, Integer limit);
}
