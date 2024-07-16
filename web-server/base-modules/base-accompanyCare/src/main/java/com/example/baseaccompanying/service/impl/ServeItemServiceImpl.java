package com.example.baseaccompanying.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.baseaccompanying.dao.ServeItemMapper;
import com.example.baseaccompanying.dao.ServeMapper;
import com.example.baseaccompanying.dao.ServeTypeMapper;
import com.example.baseaccompanying.service.ServeItemService;
import huice.accompaniment.common.constant.ErrorInfo;
import huice.accompaniment.common.domain.Serve;
import huice.accompaniment.common.domain.ServeItem;
import huice.accompaniment.common.domain.ServeType;
import huice.accompaniment.common.enums.DelFlagEnum;
import huice.accompaniment.common.enums.ServeEditStatus;
import huice.accompaniment.common.enums.ServeSaleStatus;
import huice.accompaniment.common.exception.BadRequestException;
import huice.accompaniment.common.exception.ForbiddenOperationException;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import huice.accompaniment.common.utils.snowflake.Snowflake;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static huice.accompaniment.common.constant.ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL;

/**
 * 服务项表(ServeItem)表服务实现类
 *
 * @author Doge2077
 * @since 2024-06-04 17:40:17
 */
@Service("serveItemService")
public class ServeItemServiceImpl extends ServiceImpl<ServeItemMapper, ServeItem> implements ServeItemService {

    @Resource
    Snowflake snowflake;

    @Resource
    private ServeItemMapper serveItemMapper;

    @Resource
    private ServeTypeMapper serveTypeMapper;

    @Resource
    private ServeMapper serveMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ServeItem queryById(Long id) {
        return this.serveItemMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param serveItem
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<ServeItem> queryByPage(ServeItem serveItem, Integer page, Integer size) {
        long total = this.serveItemMapper.count(serveItem);
        return new PageImpl<>(this.serveItemMapper.queryAllByLimit(serveItem, page * size, size));
    }

    /**
     * 新增数据
     *
     * @param serveItem 实例对象
     * @return 实例对象
     */
    @Override
    public ServeItem insert(ServeItem serveItem) {
        Long uid = ThreadLocalUtils.getUid();
        ServeType serveType = serveTypeMapper.queryById(serveItem.getServeTypeId());
        if (serveType == null) {
            throw new BadRequestException(REQUEST_PARAM_ILLEGAL);
        }

        // 补充信息
        serveItem.setCreateBy(uid);
        serveItem.setUpdateBy(uid);
        serveItem.setCode(snowflake.generate());

        this.serveItemMapper.insert(serveItem);
        return serveItem;
    }

    /**
     * 修改数据
     *
     * @param serveItem 实例对象
     * @return 实例对象
     */
    @Override
    public ServeItem update(ServeItem serveItem) {
        Long uid = ThreadLocalUtils.getUid();
        ServeItem serveItem1 = serveItemMapper.queryById(serveItem.getId());
        if (serveItem1 == null) {
            throw new BadRequestException(REQUEST_PARAM_ILLEGAL);
        }

        serveItem.setUpdateBy(uid);
        this.serveItemMapper.update(serveItem);
        return this.queryById(serveItem.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        // 查询是否存在该服务项目且未被删除
        LambdaQueryWrapper<ServeItem> queryWrapper = Wrappers.<ServeItem>lambdaQuery()
                .eq(ServeItem::getId, id)
                .eq(ServeItem::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus());
        ServeItem serveItem = baseMapper.selectOne(queryWrapper);
        if (serveItem == null) {
            throw new BadRequestException(REQUEST_PARAM_ILLEGAL + "已删除或服务项目不存在");
        }
        serveItem.setDelFlag(DelFlagEnum.IS_DELETE.getStatus());
        return serveItemMapper.update(serveItem) > 0;
    }

    @Override
    public boolean deactivateById(Long id) {
        // 查询是否存在该服务项
        ServeItem serveItem = serveItemMapper.queryById(id);
        if (serveItem == null) {
            throw new BadRequestException(REQUEST_PARAM_ILLEGAL + "不存在该服务项");
        }
        // 如果服务项已经禁用则无需操作
        if (serveItem.getActiveStatus().equals(ServeEditStatus.DISABLE.getStatus())) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + "已禁用");
        }
        // 查询对应服务有没有在售
        LambdaQueryWrapper<Serve> queryWrapper = Wrappers.<Serve>lambdaQuery()
                .eq(Serve::getServeItemId, serveItem.getId())
                .eq(Serve::getSaleStatus, ServeSaleStatus.ENABLE.getStatus())
                .eq(Serve::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus());
        Long count = serveMapper.selectCount(queryWrapper);
        //如果有在售的服务不能禁用该服务项
        if (count > 0) {
            throw new ForbiddenOperationException(ErrorInfo.Msg.FORBIDDEN_OPERATION + "存在在售的服务不能下架");
        }
        // 禁用服务项
        serveItem.setActiveStatus(ServeEditStatus.DISABLE.getStatus());
        return serveItemMapper.update(serveItem) > 0;
    }

    @Override
    public ServeItem addminAddServeItem(String img, String serveName, Long ServeTypeId, Integer onSaleFlag) {
        ServeItem serveItem = new ServeItem();
        Long uid = ThreadLocalUtils.getUid();
        // 补充信息
        serveItem.setImg(img);
        serveItem.setName(serveName);
        serveItem.setServeTypeId(ServeTypeId);
        serveItem.setActiveStatus(onSaleFlag);
        serveItem.setCreateBy(uid);
        serveItem.setUpdateBy(uid);
        serveItem.setCode(snowflake.generate());
        // 保存信息
        this.serveItemMapper.insert(serveItem);
        return serveItem;
    }

    @Override
    public boolean activeById(Long id) {
        // 查询是否存在该服务项
        ServeItem serveItem = serveItemMapper.queryById(id);
        if (serveItem == null) {
            throw new BadRequestException(REQUEST_PARAM_ILLEGAL + "不存在该服务项");
        }
        // 校验是否是禁用，且服务类型已经启用
        ServeType serveType = serveTypeMapper.queryById(serveItem.getServeTypeId());
        if (serveItem.getActiveStatus().equals(ServeEditStatus.ENABLE.getStatus())
                || serveType == null
                || serveType.getActiveStatus().equals(ServeEditStatus.DISABLE.getStatus())) {
            throw new BadRequestException(REQUEST_PARAM_ILLEGAL + "已启用或服务类型禁用");
        }
        // 启用服务项
        serveItem.setActiveStatus(ServeEditStatus.ENABLE.getStatus());
        return serveItemMapper.updateById(serveItem) > 0;
    }

    @Override
    public ServeItem findServeItemById(Long id) {
        ServeItem serveItem = this.queryById(id);
        if (serveItem == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "服务项不存在");
        }
        return serveItem;
    }
}
