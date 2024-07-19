package com.example.baseaccompanying.controller;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.service.ServeTypeService;
import com.huice.health.common.anno.apiAuth.WhiteApi;
import com.huice.health.common.core.ResponseVo;
import com.huice.health.common.domain.ServeType;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务类型表(ServeType)表控制层
 *
 * @author Doge2077
 * @since 2024-06-04 15:30:25
 */
@RestController
@RequestMapping("/serveType")
public class ServeTypeController {
    /**
     * 服务对象
     */
    @Resource
    private ServeTypeService serveTypeService;

    /**
     * 管理员获取全部已启用的服务类型
     *
     * @return 已启用的服务类型列表
     */
    @WhiteApi
    @GetMapping("/adminGetAllActiveServeType")
    public String adminGetAllActiveServeType() {
        List<ServeType> serveTypeList = this.serveTypeService.adminGetAllActiveServeType();
        return JSONArray.toJSONString(new ResponseVo<>("ok", serveTypeList, "200"));
    }

    /**
     * 分页查询
     *
     * @param serveType
     * @param page
     * @param size
     * @return
     */
    @WhiteApi
    @GetMapping
    public String queryByPage(@ModelAttribute ServeType serveType,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size) {
        Page<ServeType> serveTypes = this.serveTypeService.queryByPage(serveType, page, size);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serveTypes, "200"));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @WhiteApi
    @GetMapping("{id}")
    public String queryById(@PathVariable("id") Long id) {
        ServeType serveType = this.serveTypeService.queryById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serveType, "200"));
    }

    /**
     * 新增数据
     *
     * @param serveType 实体
     * @return 新增结果
     */
    @WhiteApi
    @PostMapping
    public String add(ServeType serveType) {
        ServeType serveType1 = this.serveTypeService.insert(serveType);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serveType1, "200"));
    }

    /**
     * 编辑数据
     *
     * @param serveType 实体
     * @return 编辑结果
     */
    @WhiteApi
    @PutMapping
    public String edit(ServeType serveType) {
        ServeType serveType1 = this.serveTypeService.update(serveType);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serveType1, "200"));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @WhiteApi
    @DeleteMapping
    public String deleteById(Long id) {
        boolean deleted = this.serveTypeService.deleteById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", deleted, "200"));
    }

    /**
     * 启用服务类型
     *
     * @param id 主键
     * @return 启用是否成功
     */
    @WhiteApi
    @PutMapping("/active")
    public String activateById(Long id) {
        boolean active = this.serveTypeService.activeById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", active, "200"));
    }

    /**
     * 禁用服务类型
     *
     * @param id 主键
     * @return 禁用是否成功
     */
    @WhiteApi
    @PutMapping("/deactivate")
    public String deactivateById(Long id) {
        boolean deactive = this.serveTypeService.deactivateById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", deactive, "200"));
    }

}

