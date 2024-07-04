package com.example.baseaccompanying.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.baseaccompanying.dao.HospitalMapper;
import com.example.baseaccompanying.dao.ServeItemMapper;
import com.example.baseaccompanying.dao.ServeMapper;
import com.example.baseaccompanying.dao.ServeTypeMapper;
import com.example.baseaccompanying.service.ServeService;
import huice.accompaniment.common.constant.ErrorInfo;
import huice.accompaniment.common.domain.Hospital;
import huice.accompaniment.common.domain.Serve;
import huice.accompaniment.common.domain.ServeItem;
import huice.accompaniment.common.domain.ServeType;
import huice.accompaniment.common.enums.DelFlagEnum;
import huice.accompaniment.common.enums.ServeEditStatus;
import huice.accompaniment.common.enums.ServeSaleStatus;
import huice.accompaniment.common.exception.BadRequestException;
import huice.accompaniment.common.exception.ForbiddenOperationException;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    private ServeItemMapper serveItemMapper;

    @Resource
    private HospitalMapper hospitalMapper;
    @Autowired
    private ServeTypeMapper serveTypeMapper;

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
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Serve> queryByPage(Serve serve, Integer page, Integer size) {
        long total = this.serveMapper.count(serve);
        return new PageImpl<>(this.serveMapper.queryAllByLimit(serve, page * size, size));
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

        ServeItem serveItem = serveItemMapper.queryById(serve.getServeItemId());
        if (serveItem == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "服务项不存在");
        }

        Hospital hospital = hospitalMapper.queryById(serve.getHospitalId());
        if (hospital == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "医院不存在");
        }

        LambdaQueryWrapper<Serve> queryWrapper = Wrappers.<Serve>lambdaQuery()
                .eq(Serve::getServeItemId, serve.getServeItemId())
                .eq(Serve::getHospitalId, serve.getHospitalId());

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
        Long uid = ThreadLocalUtils.getUid();

        ServeItem serveItem = serveItemMapper.queryById(serve.getServeItemId());
        if (serveItem == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "服务项不存在");
        }

        Hospital hospital = hospitalMapper.queryById(serve.getHospitalId());
        if (hospital == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "医院不存在");
        }

        LambdaQueryWrapper<Serve> queryWrapper = Wrappers.<Serve>lambdaQuery()
                .eq(Serve::getServeItemId, serve.getServeItemId())
                .eq(Serve::getHospitalId, serve.getHospitalId());

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
        LambdaQueryWrapper<Serve> queryWrapper = Wrappers.<Serve>lambdaQuery()
                .eq(Serve::getId, id)
                .eq(Serve::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus());
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
        Serve serve = serveMapper.queryById(id);
        if (serve == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "不存在该服务");
        }
        // 校验是否已经上架
        if (serve.getSaleStatus().equals(ServeSaleStatus.ENABLE.getStatus())) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + "服务已上架");
        }
        // 校验服务项和对应的服务类型是否已经启用
        LambdaQueryWrapper<ServeItem> serveItemLambdaQueryWrapper = Wrappers.<ServeItem>lambdaQuery()
                .eq(ServeItem::getId, serve.getServeItemId())
                .eq(ServeItem::getActiveStatus, ServeEditStatus.ENABLE.getStatus())
                .eq(ServeItem::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus());
        ServeItem serveItem = serveItemMapper.selectOne(serveItemLambdaQueryWrapper);
        if (serveItem == null) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + "服务项禁用");
        }
        LambdaQueryWrapper<ServeType> serveTypeLambdaQueryWrapper = Wrappers.<ServeType>lambdaQuery()
                .eq(ServeType::getId, serveItem.getServeTypeId())
                .eq(ServeType::getActiveStatus, ServeEditStatus.ENABLE.getStatus())
                .eq(ServeType::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus());
        ServeType serveType = serveTypeMapper.selectOne(serveTypeLambdaQueryWrapper);
        if (serveType == null) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "服务类型禁用");
        }
        // 上架
        serve.setSaleStatus(ServeSaleStatus.ENABLE.getStatus());
        return serveMapper.update(serve) > 0;
    }
}