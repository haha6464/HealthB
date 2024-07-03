package com.example.baseaccompanying.controller;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.service.ServeService;
import huice.accompaniment.common.anno.apiAuth.WhiteApi;
import huice.accompaniment.common.core.ResponseVo;
import huice.accompaniment.common.domain.Serve;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 服务表(Serve)表控制层
 *
 * @author Doge2077
 * @since 2024-06-06 14:43:00
 */
@RestController
@RequestMapping("/serve")
public class ServeController {
    /**
     * 服务对象
     */
    @Resource
    private ServeService serveService;

    /**
     * 分页查询
     *
     * @param serve
     * @param page
     * @param size
     * @return
     */
    @WhiteApi
    @GetMapping
    public String queryByPage(@ModelAttribute Serve serve,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size) {
        Page<Serve> serves = this.serveService.queryByPage(serve, page, size);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serves, "200"));
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
        Serve serve = this.serveService.queryById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serve, "200"));
    }

    /**
     * 新增数据
     *
     * @param serve 实体
     * @return 新增结果
     */
    @WhiteApi
    @PostMapping
    public String add(Serve serve) {
        Serve serve1 = this.serveService.insert(serve);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serve1, "200"));
    }

    /**
     * 编辑数据
     *
     * @param serve 实体
     * @return 编辑结果
     */
    @WhiteApi
    @PutMapping
    public String edit(Serve serve) {
        Serve serve1 = this.serveService.update(serve);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serve1, "200"));
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
        boolean deleted = this.serveService.deleteById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", deleted, "200"));
    }

    /**
     * 上架服务
     *
     * @param id 服务 id
     * @return 上架是否成功
     */
    @WhiteApi
    @PutMapping("/onSale")
    public String onSaleById(@RequestParam("id") Long id) {
        boolean onSale = this.serveService.onSaleById(id);
        return JSONArray.toJSONString(new ResponseVo<>("ok", onSale, "200"));
    }

}

