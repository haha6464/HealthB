package com.example.baseaccompanying.controller;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.service.OrderListService;
import com.huice.health.api.order.OrderApi;
import com.huice.health.common.anno.apiAuth.WhiteApi;
import com.huice.health.common.core.PageImpl;
import com.huice.health.common.core.ResponseVo;
import com.huice.health.common.domain.OrderList;
import com.huice.health.common.domain.bo.AdminGetListDataBo;
import com.huice.health.common.utils.ThreadLocalUtils;
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

    @Resource
    private OrderApi orderApi;
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
        return JSONArray.toJSONString(new ResponseVo("200", orderApi.queryById(id), "ok"));
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
        System.err.println(res);
        return res;
    }

    /**
     * 管理员获取订单列表
     * @param page 页数
     * @param size 大小
     * @return 订单列表
     */
    @WhiteApi
    @GetMapping("/adminGetOrderList")
    public String adminGetOrderList(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        PageImpl<?> orderLists = this.orderListService.adminGetOrderList(page, size);
        return JSONArray.toJSONString(new ResponseVo<>("200", orderLists, "ok"));
    }

}

