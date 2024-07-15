package com.example.baseaccompanying.controller;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.service.PatientescortlistService;
import huice.accompaniment.common.anno.apiAuth.WhiteApi;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.core.ResponseVo;
import huice.accompaniment.common.domain.Patientescortlist;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 陪诊师控制类
 *
 * @author Yida Yang
 * @since 2024-06-11 09:50:27
 */
@RestController
@RequestMapping("/patientescortlist")
public class PatientescortlistController {
    /**
     * 服务对象
     */
    @Resource
    private PatientescortlistService patientescortlistService;

    /**
     * 管理员获取陪诊师列表
     * @param offset 页数
     * @param limit 大小
     * @return 陪诊师分页列表和总数
     */
    @WhiteApi
    @GetMapping("/adminGetPatientEscortList")
    public String getPatientEscortList(@Param("offset") Integer offset, @Param("limit") Integer limit) {
        PageImpl<?> adminGetPatientEscortList = this.patientescortlistService.adminGetPatientEscortList(offset * limit, limit);
        return JSONArray.toJSONString(new ResponseVo<>("ok", adminGetPatientEscortList, "200"));
    }

    /**
     * 分页查询
     *
     * @param patientescortlist 筛选条件
     * @param page              第几页
     * @param size              页大小
     * @return 查询结果
     */
    @GetMapping("/queryByPage")
    public String queryByPage(@ModelAttribute Patientescortlist patientescortlist, @RequestParam("page") Integer page, @Param("size") Integer size) {
        PageImpl<?> page1 = this.patientescortlistService.queryByPage(patientescortlist, page, size);
        return JSONArray.toJSONString(new ResponseVo("200", page1, "ok"));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public String queryById(@RequestParam("id") Long id) {
        Patientescortlist patientescortlist = this.patientescortlistService.queryById(id);
        return JSONArray.toJSONString(new ResponseVo("200", patientescortlist, "ok"));
    }

    /**
     * 新增数据
     *
     * @param patientescortlist 实体
     * @return 新增结果
     */
    @WhiteApi
    @PostMapping("/add")
    public String add(Patientescortlist patientescortlist) {
        Long uid = ThreadLocalUtils.getUid();
        patientescortlist.setCreateBy(uid);
        Patientescortlist insert = this.patientescortlistService.insert(patientescortlist);
        return JSONArray.toJSONString(new ResponseVo("200", insert, "ok"));
    }

    /**
     * 编辑数据
     *
     * @param patientescortlist 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public String edit(Patientescortlist patientescortlist) {
        Long uid = ThreadLocalUtils.getUid();
        patientescortlist.setUpdateBy(uid);
        Patientescortlist update = this.patientescortlistService.update(patientescortlist);
        return JSONArray.toJSONString(new ResponseVo("200", update, "ok"));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("/deleteById")
    public String deleteById(Long id) {
        boolean b = this.patientescortlistService.deleteById(id);
        return JSONArray.toJSONString(new ResponseVo("200", b, "ok"));
    }
}

