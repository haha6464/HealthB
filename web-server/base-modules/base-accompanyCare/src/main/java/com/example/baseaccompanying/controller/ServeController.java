package com.example.baseaccompanying.controller;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.dao.ServeMapper;
import com.example.baseaccompanying.quartz.scheduler.AdminOnSaleServeScheduler;
import com.example.baseaccompanying.service.ServeItemService;
import com.example.baseaccompanying.service.ServeService;
import huice.accompaniment.common.anno.apiAuth.WhiteApi;
import huice.accompaniment.common.constant.ErrorInfo;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.core.ResponseVo;
import huice.accompaniment.common.domain.Serve;
import huice.accompaniment.common.domain.ServeItem;
import huice.accompaniment.common.enums.ServeEditStatus;
import huice.accompaniment.common.exception.BadRequestException;
import huice.accompaniment.common.exception.ForbiddenOperationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.BackingStoreException;

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

    @Resource
    private ServeItemService serveItemService;

    @Resource
    private AdminOnSaleServeScheduler adminOnSaleServeScheduler;

    @WhiteApi
    @PutMapping("/adminAddServe")
    public String adminAddServe(@RequestParam("serve_item_img") String img,
                                @RequestParam("serve_item_name") String serveItemName,
                                @RequestParam("serve_type_id") Long serveTypeId,
                                @RequestParam("hospital_id") Long hospitalId,
                                @RequestParam("serve_price") BigDecimal serve_price,
                                @RequestParam(value = "reserve_sale_time", required = false) String reserveSaleTime,
                                @RequestParam("on_sale_flag") Integer onSaleFlag) {
        // 判断定时时间是否合法
        Date publishTime = null;
        if (onSaleFlag.equals(ServeEditStatus.DISABLE.getStatus())) {
            if (reserveSaleTime == null) {
                throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + " 未设置上架时间");
            }
            try {
                publishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reserveSaleTime);
                if (publishTime.before(new Date())) {
                    throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + " 日期设置时间不合法");
                }
            } catch (ParseException e) {
                throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + " 日期格式不正确");
            }
        }
        // 创建服务项目
        ServeItem serveItem = this.serveItemService.addminAddServeItem(img, serveItemName, serveTypeId, onSaleFlag);
        // 服务项目和医院关联
        Serve serve = this.serveService.adminPublishServe(serveItem.getId(), hospitalId, serve_price, onSaleFlag);
        // 定时上架
        if (onSaleFlag.equals(ServeEditStatus.DISABLE.getStatus())) {
            // 添加定时任务，定时将该服务修改为上架
            this.adminOnSaleServeScheduler.adminOnSaleServeScheduler(serve.getId(), publishTime);
        }
        return JSONArray.toJSONString(new ResponseVo<>("ok", null, "200"));
    }

    /**
     * 管理员获取服务列表
     * @param page 页数
     * @param size 大小
     * @return 服务列表信息
     */
    @WhiteApi
    @GetMapping("/adminGetServeList")
    public String adminGetServeList(@RequestParam("page")Integer page, @RequestParam("size")Integer size) {
        PageImpl<?> serves = this.serveService.adminGetServeList(page, size);
        return JSONArray.toJSONString(new ResponseVo<>("ok", serves, "200"));
    }

    /**
     * 分页查询
     *
     * @param serve
     * @param page
     * @param size
     * @return
     */
    @WhiteApi
    @GetMapping("/queryByPage")
    public String queryByPage(@ModelAttribute Serve serve,
                              @RequestParam("page") Integer page,
                              @RequestParam("size") Integer size) {
        PageImpl<?> serves = this.serveService.queryByPage(serve, page, size);
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

