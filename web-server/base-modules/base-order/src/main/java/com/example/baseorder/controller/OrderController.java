package com.example.baseorder.controller;

import cn.hutool.json.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.baseorder.pojo.DTO.OrderCreateReqDTO;
import com.example.baseorder.service.OrderService;
import huice.accompaniment.common.core.ResponseVo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/1 14:09
 */
@RequestMapping("/order")
@Validated
@RestController
public class OrderController {

    @Resource
    OrderService orderService;
    @PostMapping("/api/create")
    public Object createOrder( OrderCreateReqDTO createReqDTO){
        return JSONArray.toJSON(new ResponseVo<>("200",orderService.createOrder(createReqDTO),"ok"));
    }

    @DeleteMapping("/api/delete")
    public Object deleteOrder(Long orderId){
        return JSONArray.toJSON(new ResponseVo<>("200",orderService.deleteOrderById(orderId),"ok"));
    }
}
