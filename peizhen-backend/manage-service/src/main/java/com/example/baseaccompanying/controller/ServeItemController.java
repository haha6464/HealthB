package com.example.baseaccompanying.controller;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.service.ServeItemService;
import com.huice.health.common.anno.apiAuth.WhiteApi;
import com.huice.health.common.core.ResponseVo;
import com.huice.health.common.domain.ServeItem;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 服务项表(ServeItem)表控制层
 *
 * @author Doge2077
 * @since 2024-06-04 17:40:10
 */
@RestController
@RequestMapping("/serveItem")
public class ServeItemController {
    /**
     * 服务对象
     */
    @Resource
    private ServeItemService serveItemService;

    /**
     * 分页查询
     *
     * @param serveItem
     * @param page
     * @param size
     * @return
     */
    @WhiteApi
    @GetMapping
    public String queryByPage(@ModelAttribute ServeItem serveItem,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size) {
        Page<ServeItem> serveItems = this.serveItemService.queryByPage(serveItem, page, size);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serveItems, "200"));
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
        ServeItem serveItem = this.serveItemService.queryById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serveItem, "200"));
    }

    /**
     * 新增数据
     *
     * @param serveItem 实体
     * @return 新增结果
     */
    @WhiteApi
    @PostMapping
    public String add(ServeItem serveItem) {
        ServeItem serveItem1 = this.serveItemService.insert(serveItem);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serveItem1, "200"));
    }

    /**
     * 编辑数据
     *
     * @param serveItem 实体
     * @return 编辑结果
     */
    @WhiteApi
    @PutMapping
    public String edit(ServeItem serveItem) {
        ServeItem serveItem1 = this.serveItemService.update(serveItem);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serveItem1, "200"));
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
        boolean deleted = this.serveItemService.deleteById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", deleted, "200"));
    }

    /**
     * 启用服务项
     *
     * @param id 服务项 id
     * @return 启用是否成功
     */
    @WhiteApi
    @PutMapping("/active")
    public String activeById(@RequestParam("id") Long id) {
        boolean active = this.serveItemService.activeById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", active, "200"));
    }

    /**
     * 禁用服务项
     *
     * @param id 服务项 id
     * @return 禁用是否成功
     */
    @WhiteApi
    @PutMapping("/deactivate")
    public String deactivateById(@RequestParam("id") Long id) {
        boolean deactivate = this.serveItemService.deactivateById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", deactivate, "200"));
    }

}

