package com.example.baseaccompanying.controller;

import com.alibaba.fastjson2.JSONArray;
import huice.accompaniment.common.anno.apiAuth.WhiteApi;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.core.ResponseVo;
import huice.accompaniment.common.domain.Hospital;
import com.example.baseaccompanying.service.HospitalService;
import huice.accompaniment.common.domain.bo.HospitalBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Hospital) 医院控制层
 *
 * @author Yida Yang
 * @since 2024-06-05 15:42:30
 */
@RestController
@RequestMapping("hospital")
public class HospitalController {
    /**
     * 服务对象
     */
    @Resource
    private HospitalService hospitalService;

    @GetMapping("/adminGetHospitalListOne")
    public String adminGetHospitalListOne(@RequestParam("id") Long id){
        return hospitalService.adminGetHospitalListOne(id);
    }
    /**
     * 管理员获取集合数据
     * @return
     */
    @WhiteApi
    @GetMapping("/adminGetHospitalList")
    public String adminGetHospitalList(@RequestParam("page")Integer page, @RequestParam("size")Integer size
    ,@RequestParam(value = "status",required = false) Integer status ,@RequestParam(value = "name",required = false)String name){
        //状态为全部的情况
        if(status != null && status == -1){
            status = null;
        }
        return  hospitalService.adminGetHospitalList(page, size,status,name);
    }

    /**
     * 分页查询
     *
     * @param hospital 筛选条件
     * @param page     页
     * @return 查询结果
     */
    @WhiteApi
    @GetMapping("/queryByPage")
    public String queryByPage(@ModelAttribute Hospital hospital,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size) {
        PageImpl<?> hospitalPage = this.hospitalService.queryByPage(hospital, page, size);

        return JSONArray.toJSONString(new ResponseVo<>("200", hospitalPage, "ok"));
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
        Hospital hospital = this.hospitalService.queryById(id);
        return JSONArray.toJSONString(new ResponseVo<>("200", hospital, "ok"));
    }

    /**
     * 新增数据
     *
     * @param hospital 实体
     * @return 新增结果
     */
    @WhiteApi
    @PostMapping("/add")
    public String add(Hospital hospital) {

        Hospital insert = this.hospitalService.insert(hospital);
        return JSONArray.toJSONString(new ResponseVo<>("200", insert, "ok"));
    }

    /**
     * 编辑数据
     *
     * @param hospital 实体
     * @return 编辑结果
     */

    @WhiteApi
    @PostMapping("/edit")
    public String edit(Hospital hospital) {
        Hospital update = this.hospitalService.update(hospital);
        return JSONArray.toJSONString(new ResponseVo<>("200", update, "ok"));
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
        boolean b = this.hospitalService.deleteById(id);
        return JSONArray.toJSONString(new ResponseVo<>("200", b, "ok"));
    }
}

