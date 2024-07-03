package com.example.baseorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.baseorder.pojo.DTO.GoodsCreateReqDTO;
import com.example.baseorder.pojo.DTO.GoodsUpdateReqDTO;
import com.example.baseorder.service.GoodsService;
import huice.accompaniment.common.core.ResponseVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/2 12:14
 */
@RequestMapping("/goods")
@Validated
@RestController
public class GoodsController {

    @Resource
    GoodsService goodsService;

    @PostMapping("/api/create")
    public Object creatGoods(@Validated GoodsCreateReqDTO goodsCreateReqDTO){
        return JSONArray.toJSON(new ResponseVo<>("200",goodsService.createGoods(goodsCreateReqDTO),"ok"));
    }

    @GetMapping("/api/queryAll")
    public Object queryGoodsAll(){
        return JSONArray.toJSON(new ResponseVo<>("200",goodsService.queryGoodsAll(),"ok"));
    }

    @GetMapping("/api/query")
    public Object queryGoodsPage(@RequestParam("limit") Long limit,@RequestParam("page") Long page){
        return JSONArray.toJSON(new ResponseVo<>("200",goodsService.queryGoodsByPage(limit,page),"ok"));
    }

    @DeleteMapping("/api/delete")
    public Object deleteGoods(Long goodsId){
        return JSONArray.toJSON(new ResponseVo<>("200",goodsService.deleteGoodsById(goodsId),"ok"));
    }

    @PostMapping("/api/edit")
    public Object updateGoods( GoodsUpdateReqDTO goodsUpdateReqDTO){
        return JSONArray.toJSON(new ResponseVo<>("200",goodsService.updateGoodsById(goodsUpdateReqDTO),"ok"));
    }
}
