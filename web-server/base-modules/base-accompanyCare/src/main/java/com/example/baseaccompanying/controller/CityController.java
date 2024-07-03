package com.example.baseaccompanying.controller;

import com.alibaba.fastjson2.JSONArray;
import huice.accompaniment.common.anno.apiAuth.WhiteApi;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.core.ResponseVo;
import huice.accompaniment.common.domain.City;
import com.example.baseaccompanying.service.CityService;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 城市(City)控制层
 *
 * @author Yida Yang
 * @since 2024-06-04 13:08:26
 */
@RestController
@RequestMapping("/city")
public class CityController {
    /**
     * 服务对象
     */
    @Resource
    private CityService cityService;

    /**
     * 获取城市集合
     * @return
     */
    @GetMapping("/getCityList")
    public String getCityList(){

        return cityService.getCityList();
    }

    /**
     * 测试登入token out
     */
    @WhiteApi
    @RequestMapping("/test")
    public String test() {
        System.out.println(ThreadLocalUtils.getUid());
        System.out.println("test");

        return "AAAA";
    }

    /**
     * 分页查询
     *
     * @param city 筛选条件
     * @param page 页数
     * @param size 页大小
     * @return 查询结果
     */
//    @WhiteApi
    @GetMapping("/queryByPage")
    public String queryByPage(@ModelAttribute City city, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        PageImpl page1 = this.cityService.queryByPage(city, page, size);
        ResponseVo<?> responseVo = new ResponseVo<>("200", page1, "ok");
        return JSONArray.toJSONString(new ResponseVo<>("200", responseVo, "ok"));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @WhiteApi
    @GetMapping("/queryById/{id}")
    public String queryById(@PathVariable("id") Long id) {
        City city = this.cityService.queryById(id);
        ResponseVo<?> responseVo = new ResponseVo<>("200", city, "ok");
        return JSONArray.toJSONString(new ResponseVo<>("200", responseVo, "ok"));
    }

    /**
     * 新增数据
     *
     * @param city 实体
     * @return 新增结果
     */
//    @WhiteApi
    @PostMapping("/add")
    public String add(City city) {
        City insert = this.cityService.insert(city);

        ResponseVo<City> ok = new ResponseVo<>("200", insert, "ok");
        return JSONArray.toJSONString(new ResponseVo<>("200", ok, "ok"));
    }

    /**
     * 编辑数据
     *
     * @param city 实体
     * @return 编辑结果
     */
//    @WhiteApi
    @PostMapping("/edit")
    public String edit(City city) {
        City update = this.cityService.update(city);
        ResponseVo<City> ok = new ResponseVo<>("200", update, "ok");
        return JSONArray.toJSONString(new ResponseVo<>("200", ok, "ok"));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("/deleteById")
    public String deleteById(Long id) {
        boolean b = this.cityService.deleteById(id);
        return JSONArray.toJSONString(new ResponseVo<>("200", new ResponseVo<>("200", b, "ok"), "ok"));
    }

}

