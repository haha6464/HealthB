package com.example.baseaccompanying.controller;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.service.OrderListService;
import huice.accompaniment.common.anno.apiAuth.WhiteApi;
import huice.accompaniment.common.core.ResponseVo;
import huice.accompaniment.common.domain.OrderList;
import huice.accompaniment.common.domain.bo.AdminGetListDataBo;
import huice.accompaniment.common.domain.vo.AdminGetListDataVo;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单(OrderList)表控制层
 *
 * @author Yida Yang
 * @since 2024-06-14 14:51:34
 */
@RestController
@RequestMapping("orderList")
public class OrderListController {
    /**
     * 服务对象
     */
    @Resource
    private OrderListService orderListService;

    /**
     * 分页查询
     *
     * @param orderList   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @WhiteApi
    @GetMapping("/queryByPage")
    public String queryByPage(OrderList orderList, PageRequest pageRequest) {
        Page<OrderList> orderLists = this.orderListService.queryByPage(orderList, pageRequest);
        return JSONArray.toJSONString(new ResponseVo("200", orderLists, "ok"));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @WhiteApi
    @GetMapping("/queryById")
    public String queryById(@RequestParam("id") Long id) {
        OrderList orderList = this.orderListService.queryById(id);
        return JSONArray.toJSONString(new ResponseVo("200", orderList, "ok"));
    }

    /**
     * 新增数据
     *
     * @param orderList 实体
     * @return 新增结果
     */
    @WhiteApi
    @PostMapping("/add")
    public String add(OrderList orderList) {
        Long uid = ThreadLocalUtils.getUid();
        orderList.setCreateBy(uid);
        OrderList insert = this.orderListService.insert(orderList);
        return JSONArray.toJSONString(new ResponseVo("200", insert, "ok"));
    }

    /**
     * 编辑数据
     *
     * @param orderList 实体
     * @return 编辑结果
     */
    @WhiteApi
    @PostMapping("/edit")
    public String edit(OrderList orderList) {
        Long uid = ThreadLocalUtils.getUid();
        orderList.setUpdateBy(uid);
        OrderList update = this.orderListService.update(orderList);
        return JSONArray.toJSONString(new ResponseVo("200", update, "ok"));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @WhiteApi
    @PostMapping("/deleteById")
    public String deleteById(Long id) {
        boolean b = this.orderListService.deleteById(id);
        return JSONArray.toJSONString(new ResponseVo("200", b, "ok"));
    }


    /**
     * 分页查询Fouser
     *
     * @param orderList   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @WhiteApi
    @GetMapping("/queryByPageForUser")
    public String queryByPageForUser(OrderList orderList, PageRequest pageRequest) {
        Page<OrderList> orderLists = this.orderListService.queryByPage(orderList, pageRequest);
        return JSONArray.toJSONString(new ResponseVo("200", orderLists, "ok"));
    }

    /**
     * 通过主键查询单条数据ForUser
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryByIdForUser")
    public String queryByIdForUser(@RequestParam("id") Long id) {
        OrderList orderList = this.orderListService.queryById(id);
        return JSONArray.toJSONString(new ResponseVo("200", orderList, "ok"));
    }

    /**
     * 编辑数据ForUser
     *
     * @param orderList 实体
     * @return 编辑结果
     */
    @PostMapping("/editForUser")
    public String editForUser(OrderList orderList) {
        OrderList update = this.orderListService.update(orderList);
        return JSONArray.toJSONString(new ResponseVo("200", update, "ok"));
    }

    /**
     * 删除数据ForUser
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("/deleteByIdForUser")
    public String deleteByIdForUser(Long id) {
        boolean b = this.orderListService.deleteById(id);
        return JSONArray.toJSONString(new ResponseVo("200", b, "ok"));
    }


    /**
     * 管理员获取1
     *
     * @param adminGetListDataBo
     * @return
     */
    @WhiteApi
    @PostMapping("/adminGetListData")
    public String adminGetListData(AdminGetListDataBo adminGetListDataBo) {
        System.out.println(ThreadLocalUtils.getUid());
        System.out.println(adminGetListDataBo);
        String res = orderListService.adminGetListData(adminGetListDataBo);

        return res;
    }
}

