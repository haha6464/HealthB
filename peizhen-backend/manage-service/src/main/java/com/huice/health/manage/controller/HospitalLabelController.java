package com.huice.health.manage.controller;

import com.alibaba.fastjson2.JSONArray;
import com.huice.health.common.anno.apiAuth.WhiteApi;
import com.huice.health.common.core.PageImpl;
import com.huice.health.common.core.ResponseVo;
import com.huice.health.common.domain.HospitalLabel;
import com.huice.health.manage.service.HospitalLabelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 医院标签控制器
 *
 * @author Yida Yang
 * @since 2024-06-03
 */
@RestController
@RequestMapping("/hospitalLabel")
public class HospitalLabelController {
    /**
     * 服务对象
     */
    @Resource
    private HospitalLabelService hospitalLabelService;

    /**
     * 分页查询
     *
     * @param hospitalLabel 筛选条件
     * @param page          页数
     * @param size          页大小
     * @return 查询结果
     */
    @WhiteApi
    @GetMapping("/queryByPage")
    public String queryByPage(@ModelAttribute HospitalLabel hospitalLabel, @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size) {
        PageImpl page1 = this.hospitalLabelService.queryByPage(hospitalLabel, page, size);
        return JSONArray.toJSONString(new ResponseVo("200", page1, "ok"));
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
        HospitalLabel hospitalLabel = this.hospitalLabelService.queryById(id);

        return JSONArray.toJSONString(new ResponseVo("200", hospitalLabel, "ok"));
    }

    /**
     * 新增数据
     *
     * @param hospitalLabel 实体
     * @return 新增结果
     */
    @WhiteApi
    @PostMapping("/add")
    public String add(HospitalLabel hospitalLabel) {
        HospitalLabel insert = this.hospitalLabelService.insert(hospitalLabel);

        return JSONArray.toJSONString(new ResponseVo("200", insert, "ok"));
    }

    /**
     * 编辑数据
     *
     * @param hospitalLabel 实体
     * @return 编辑结果
     */
    @WhiteApi
    @PostMapping("/edit")
    public String edit(HospitalLabel hospitalLabel) {
        HospitalLabel update = this.hospitalLabelService.update(hospitalLabel);

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
        boolean b = this.hospitalLabelService.deleteById(id);
        return JSONArray.toJSONString(new ResponseVo("200", b, "ok"));
    }

}

