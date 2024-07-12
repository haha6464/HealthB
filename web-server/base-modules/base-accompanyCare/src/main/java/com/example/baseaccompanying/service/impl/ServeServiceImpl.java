package com.example.baseaccompanying.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.baseaccompanying.dao.HospitalMapper;
import com.example.baseaccompanying.dao.ServeMapper;
import com.example.baseaccompanying.dao.ServeTypeMapper;
import com.example.baseaccompanying.dao.ToHospitalMapper;
import com.example.baseaccompanying.service.ServeItemService;
import com.example.baseaccompanying.service.ServeService;
import com.example.baseaccompanying.service.ServeToOrderListService;
import huice.accompaniment.common.constant.ErrorInfo;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.*;
import huice.accompaniment.common.domain.vo.ServePageVo;
import huice.accompaniment.common.enums.DelFlagEnum;
import huice.accompaniment.common.enums.ServeEditStatus;
import huice.accompaniment.common.enums.ServeSaleStatus;
import huice.accompaniment.common.exception.BadRequestException;
import huice.accompaniment.common.exception.ForbiddenOperationException;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务表(Serve)表服务实现类
 *
 * @author Doge2077
 * @since 2024-06-06 14:43:00
 */
@Service("serveService")
public class ServeServiceImpl extends ServiceImpl<ServeMapper, Serve> implements ServeService {
    @Resource
    private ServeMapper serveMapper;

    @Resource
    private ServeItemService serveItemService;

    @Resource
    private HospitalMapper hospitalMapper;

    @Resource
    private ToHospitalMapper toHospitalMapper;

    @Resource
    private ServeTypeMapper serveTypeMapper;

    @Resource
    private ServeToOrderListService serveToOrderListService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Serve queryById(Long id) {
        return this.serveMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param serve 筛选条件
     * @param page 页数
     * @param size 大小
     * @return 分页信息
     */
    @Override
    public PageImpl<?> queryByPage(Serve serve, Integer page, Integer size) {
        Long uid = ThreadLocalUtils.getUid();
        long total = this.serveMapper.count(serve, uid);
        if (total == 0) return new PageImpl<>(null, total);
        return getPage(page, size, uid, serve, total);
    }

    /**
     * 新增数据
     *
     * @param serve 实例对象
     * @return 实例对象
     */
    @Override
    public Serve insert(Serve serve) {
        Long uid = ThreadLocalUtils.getUid();
        serveItemService.findServeItemById(serve.getServeItemId());
        Hospital hospital = hospitalMapper.queryById(serve.getHospitalId());
        if (hospital == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "医院不存在");
        }

        LambdaQueryWrapper<Serve> queryWrapper = Wrappers.<Serve>lambdaQuery().eq(Serve::getServeItemId, serve.getServeItemId()).eq(Serve::getHospitalId, serve.getHospitalId());

        Long count = baseMapper.selectCount(queryWrapper);
        // 不能重复添加服务项目
        if (count > 0) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "服务已存在");
        }

        // 添加信息
        serve.setCreateBy(uid);
        serve.setUpdateBy(uid);

        this.serveMapper.insert(serve);
        return serve;
    }

    /**
     * 修改数据
     *
     * @param serve 实例对象
     * @return 实例对象
     */
    @Override
    public Serve update(Serve serve) {
        // TODO 鉴权
        Long uid = ThreadLocalUtils.getUid();
        // 校验服务是否存在
        serveItemService.findServeItemById(serve.getServeItemId());
        Hospital hospital = hospitalMapper.queryById(serve.getHospitalId());
        if (hospital == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "医院不存在");
        }

        LambdaQueryWrapper<Serve> queryWrapper = Wrappers.<Serve>lambdaQuery().eq(Serve::getServeItemId, serve.getServeItemId()).eq(Serve::getHospitalId, serve.getHospitalId());

        Long count = baseMapper.selectCount(queryWrapper);
        // 服务项目不能重复
        if (count > 0) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + "服务已存在");
        }

        serve.setUpdateBy(uid);

        this.serveMapper.update(serve);
        return this.queryById(serve.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        // 查询是否存在该服务且未被删除
        LambdaQueryWrapper<Serve> queryWrapper = Wrappers.<Serve>lambdaQuery().eq(Serve::getId, id).eq(Serve::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus());
        Serve serve = baseMapper.selectOne(queryWrapper);
        if (serve == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "已删除或服务不存在");
        }
        serve.setDelFlag(DelFlagEnum.IS_DELETE.getStatus());
        return serveMapper.update(serve) > 0;
    }

    @Override
    public boolean onSaleById(Long id) {
        // 查询是否存在服务
        Serve serve = this.findServeById(id);
        // 校验是否已经上架
        if (serve.getSaleStatus().equals(ServeSaleStatus.ENABLE.getStatus())) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + "服务已上架");
        }
        // 校验服务项和对应的服务类型是否已经启用
        LambdaQueryWrapper<ServeItem> serveItemLambdaQueryWrapper = Wrappers.<ServeItem>lambdaQuery().eq(ServeItem::getId, serve.getServeItemId()).eq(ServeItem::getActiveStatus, ServeEditStatus.ENABLE.getStatus()).eq(ServeItem::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus());
        ServeItem serveItem = this.serveItemService.getOne(serveItemLambdaQueryWrapper);
        LambdaQueryWrapper<ServeType> serveTypeLambdaQueryWrapper = Wrappers.<ServeType>lambdaQuery().eq(ServeType::getId, serveItem.getServeTypeId()).eq(ServeType::getActiveStatus, ServeEditStatus.ENABLE.getStatus()).eq(ServeType::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus());
        ServeType serveType = serveTypeMapper.selectOne(serveTypeLambdaQueryWrapper);
        if (serveType == null) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "服务类型禁用");
        }
        // 上架
        serve.setSaleStatus(ServeSaleStatus.ENABLE.getStatus());
        return serveMapper.update(serve) > 0;
    }

    /**
     * 管理员获取服务列表
     * @param page 页数
     * @param size 大小
     * @return 服务列表
     */
    @Override
    public PageImpl<?> adminGetServeList(Integer page, Integer size) {
        Long uid = ThreadLocalUtils.getUid();
        Serve serve = new Serve();
        long total = this.serveMapper.count(serve, uid);
        if (total == 0) return new PageImpl<>(null, total);
        return getPage(page, size, uid, serve, total);
    }

    @Override
    public Serve adminPublishServe(Long serveItemId, Long hospitalId, BigDecimal servePrice, Integer onSaleFlag) {
        Long uid = ThreadLocalUtils.getUid();
        // TODO 鉴权

        // 将服务项和医院关联服务
        Serve serve = new Serve();
        serve.setServeItemId(serveItemId);
        serve.setHospitalId(hospitalId);
        serve.setSaleStatus(onSaleFlag);
        serve.setPrice(servePrice.doubleValue());
        serve.setCreateBy(uid);
        serve.setUpdateBy(uid);

        this.serveMapper.insert(serve);
        return serve;
    }

    @Override
    public Serve adminOnSaleServeById(Long id) {
        // TODO 鉴权
        Long uid = ThreadLocalUtils.getUid();
        // 将服务和对应服务项设置为上架
        // 校验是否存在
        Serve serve = this.findServeById(id);
        // 校验服务是否已经上架过
        if (serve.getSaleStatus().equals(ServeSaleStatus.ENABLE.getStatus())) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + "服务已上架");
        }
        // 校验是否有该医院的管理权限
        this.verifyUserHasHospital(uid, serve.getHospitalId());
        // 服务项是否存在
        ServeItem serveItem = serveItemService.findServeItemById(serve.getServeItemId());
        // 上架，强制修改服务项启用
        serveItem.setActiveStatus(ServeEditStatus.ENABLE.getStatus());
        this.serveItemService.update(serveItem);
        serve.setSaleStatus(ServeSaleStatus.ENABLE.getStatus());
        baseMapper.update(serve);
        return serve;
    }

    @Override
    public boolean offSaleById(Long id) {
        // TODO 鉴权
        Long uid = ThreadLocalUtils.getUid();
        // 校验服务是否存在
        Serve serve = this.findServeById(id);
        // 校验是否已经处于下架状态
        if (serve.getSaleStatus().equals(ServeSaleStatus.DISABLE.getStatus())) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + "服务已下架");
        }
        // 校验是否有该医院的管理权限
        this.verifyUserHasHospital(uid, serve.getHospitalId());
        // 服务项是否存在
        this.serveItemService.findServeItemById(serve.getServeItemId());
        // 下架服务
        serve.setSaleStatus(ServeSaleStatus.DISABLE.getStatus());
        return baseMapper.update(serve) > 0;
    }

    @Override
    public PageImpl<ServePageVo> adminFindServeByNameAndStatus(String serveName, Integer serveStatus, Integer page, Integer size) {
        Long uid = ThreadLocalUtils.getUid();
        // 查询 total
        long count = this.serveMapper.countSearchServe(serveName, serveStatus, uid);
        if (count == 0) {
            return new PageImpl<>(null, 0L);
        }
        // 分页查询具体服务列表视图集合
        List<ServePageVo> servePageVoList = this.serveMapper.querySearchServePage(serveName, serveStatus, uid, page, size)
                .stream().map(o -> {
                    ServePageVo servePageVo = new ServePageVo();
                    ServeItem serveItem = this.serveItemService.queryById(o.getServeItemId());
                    servePageVo.setServeItem(serveItem);
                    servePageVo.setServeType(this.serveTypeMapper.queryById(serveItem.getServeTypeId()));
                    servePageVo.setHospital(this.hospitalMapper.queryById(o.getHospitalId()));
                    servePageVo.setSold(this.serveToOrderListService.getSoldByServeId(o.getId()));
                    servePageVo.setCreateTime(o.getCreateTime());
                    return servePageVo;
                }).collect(Collectors.toList());
        return new PageImpl<>(servePageVoList, count);
    }

    /**
     * 根据分页大小 uid serve 信息返回分页集合
     * @param page 页数
     * @param size 大小
     * @param uid 用户 uid
     * @param serve 服务
     * @param total 总数
     * @return 分页信息
     */
    private PageImpl<?> getPage(Integer page, Integer size, Long uid, Serve serve, long total) {
        List<ServePageVo> servePageVoList = this.serveMapper.queryAllByLimit(serve, uid, page * size, size)
                .stream().map(o -> {
                    ServePageVo servePageVo = new ServePageVo();
                    ServeItem serveItem = this.serveItemService.queryById(o.getServeItemId());
                    servePageVo.setServeItem(serveItem);
                    servePageVo.setServeType(this.serveTypeMapper.queryById(serveItem.getServeTypeId()));
                    servePageVo.setHospital(this.hospitalMapper.queryById(o.getHospitalId()));
                    servePageVo.setSold(this.serveToOrderListService.getSoldByServeId(o.getId()));
                    servePageVo.setCreateTime(o.getCreateTime());
                    return servePageVo;
                }).collect(Collectors.toList());
        return new PageImpl<>(servePageVoList, total);
    }

    public Serve findServeById(Long id) {
        Serve serve = this.queryById(id);
        if (serve == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "不存在该服务");
        }
        return serve;
    }

    private void verifyUserHasHospital(Long uid, Long hospitalId) {
        // 医院是否为该用户管理
        LambdaQueryWrapper<ToHospital> queryWrapper = Wrappers.<ToHospital>lambdaQuery()
                .eq(ToHospital::getUserId, uid)
                .eq(ToHospital::getHospitalId, hospitalId);
        Long count = this.toHospitalMapper.selectCount(queryWrapper);
        if (count == 0) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + " 用户无该医院管理权限");
        }
    }
}
