package com.example.baseaccompanying.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.baseaccompanying.mapper.OrderListMapper;
import com.example.baseaccompanying.mapper.ToHospitalMapper;
import com.example.baseaccompanying.service.OrderListService;
import com.huice.health.common.core.PageImpl;
import com.huice.health.common.domain.OrderList;
import com.huice.health.common.domain.bo.AdminGetListDataBo;
import com.huice.health.common.domain.vo.AdminGetListDataVo;
import com.huice.health.common.enums.DelFlagEnum;
import com.huice.health.common.utils.ThreadLocalUtils;
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
public class OrderListServiceImpl extends ServiceImpl<OrderListMapper, OrderList> implements OrderListService{
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
    public org.springframework.data.domain.Page<OrderList> queryByPage(OrderList orderList, PageRequest pageRequest) {
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

    @Override
    public OrderList update(OrderList orderList) {
        return null;
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
    public org.springframework.data.domain.Page<OrderList> queryByPageForUser(OrderList orderList, PageRequest pageRequest) {
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
        return null;
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

    @Override
    public PageImpl<?> adminGetOrderList(Integer offset, Integer limit) {
        Long uid = ThreadLocalUtils.getUid();
        Page<OrderList> page = Page.of(offset, limit);
        LambdaQueryWrapper<OrderList> wrapper = Wrappers.<OrderList>lambdaQuery()
                .eq(OrderList::getDelFlag, DelFlagEnum.NOT_DELETE)
                .eq(OrderList::getAdminId, uid);
        Page<OrderList> result = baseMapper.selectPage(page, wrapper);
        return new PageImpl<>(result.getRecords(), result.getTotal());
    }


}
