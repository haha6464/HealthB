package com.example.baseaccompanying.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.mapper.OrderListMapper;
import com.example.baseaccompanying.mapper.ToHospitalMapper;
import com.example.baseaccompanying.service.OrderListService;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.OrderList;
import huice.accompaniment.common.domain.bo.AdminGetListDataBo;
import huice.accompaniment.common.domain.vo.AdminGetListDataVo;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单(OrderList)表服务实现类
 *
 * @author Yida Yang
 * @since 2024-06-14 14:51:34
 */
@Service("orderListService")
public class OrderListServiceImpl implements OrderListService {
    @Resource
    private OrderListMapper orderListDao;
    @Resource
    private ToHospitalMapper toHospitalMapper;

    @Override
    public String adminGetListData(AdminGetListDataBo adminGetListDataBo) {
        Long uid = ThreadLocalUtils.getUid();
        //获取我管理的医院
        Long hospitalId = toHospitalMapper.getIdByUserId(uid);
        adminGetListDataBo.setHospitalId(hospitalId);
        System.err.println("===");
        System.err.println(adminGetListDataBo);
        System.err.println("===");
        adminGetListDataBo.setPage((adminGetListDataBo.getPage() - 1) * adminGetListDataBo.getSize());
        System.err.println(hospitalId);

        List<AdminGetListDataVo> adminGetListDataVos = orderListDao.adminGetListData(adminGetListDataBo);
        System.err.println(adminGetListDataVos);
        Long count = orderListDao.adminGetCount(adminGetListDataBo);

        PageImpl page = new PageImpl<>(adminGetListDataVos, count);

        return JSONArray.toJSONString(page);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderList queryById(Long id) {
        return this.orderListDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param orderList   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<OrderList> queryByPage(OrderList orderList, PageRequest pageRequest) {
//        long total = this.orderListDao.count(orderList);
//        return new PageImpl<>(this.orderListDao.queryAllByLimit(orderList, pageRequest), pageRequest, total);
        return null;
    }

    /**
     * 新增数据
     *
     * @param orderList 实例对象
     * @return 实例对象
     */
    @Override
    public OrderList insert(OrderList orderList) {
        this.orderListDao.insert(orderList);
        return orderList;
    }

    /**
     * 修改数据
     *
     * @param orderList 实例对象
     * @return 实例对象
     */
    @Override
    public OrderList update(OrderList orderList) {
        this.orderListDao.update(orderList);
        return this.queryById(orderList.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.orderListDao.deleteById(id) > 0;
    }


    /**
     * 通过ID查询单条数据 ForUser
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderList queryByIdForUser(Long id) {
        return this.orderListDao.queryById(id);
    }

    /**
     * 分页查询 ForUser
     *
     * @param orderList   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<OrderList> queryByPageForUser(OrderList orderList, PageRequest pageRequest) {
//        long total = this.orderListDao.count(orderList);
//        return new PageImpl<>(this.orderListDao.queryAllByLimit(orderList, pageRequest), pageRequest, total);
        return null;
    }

    /**
     * 新增数据 ForUser
     *
     * @param orderList 实例对象
     * @return 实例对象
     */
    @Override
    public OrderList insertForUser(OrderList orderList) {
        this.orderListDao.insert(orderList);
        return orderList;
    }

    /**
     * 修改数据 ForUser
     *
     * @param orderList 实例对象
     * @return 实例对象
     */
    @Override
    public OrderList updateForUser(OrderList orderList) {
        this.orderListDao.update(orderList);
        return this.queryById(orderList.getId());
    }

    /**
     * 通过主键删除数据 ForUser
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByIdForUser(Long id) {
        return this.orderListDao.deleteById(id) > 0;
    }


}
