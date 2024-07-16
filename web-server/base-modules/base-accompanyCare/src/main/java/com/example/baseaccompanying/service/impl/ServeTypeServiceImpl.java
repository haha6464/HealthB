package com.example.baseaccompanying.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.baseaccompanying.dao.ServeItemMapper;
import com.example.baseaccompanying.dao.ServeTypeMapper;
import com.example.baseaccompanying.service.ServeTypeService;
import huice.accompaniment.common.constant.ErrorInfo;
import huice.accompaniment.common.domain.ServeItem;
import huice.accompaniment.common.domain.ServeType;
import huice.accompaniment.common.enums.DelFlagEnum;
import huice.accompaniment.common.enums.ServeEditStatus;
import huice.accompaniment.common.exception.BadRequestException;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import huice.accompaniment.common.utils.snowflake.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务类型表(ServeType)表服务实现类
 *
 * @author Doge2077
 * @since 2024-06-04 15:30:25
 */
@Service("serveTypeService")
public class ServeTypeServiceImpl extends ServiceImpl<ServeTypeMapper, ServeType> implements ServeTypeService {

    @Resource
    Snowflake snowflake;

    @Resource
    private ServeTypeMapper serveTypeMapper;
    @Autowired
    private ServeItemMapper serveItemMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ServeType queryById(Long id) {
        return this.serveTypeMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param serveType
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<ServeType> queryByPage(ServeType serveType, Integer page, Integer size) {
        long total = this.serveTypeMapper.count(serveType);
        return new PageImpl<>(this.serveTypeMapper.queryAllByLimit(serveType, page * size, size));
    }

    /**
     * 新增数据
     *
     * @param serveType 实例对象
     * @return 实例对象
     */
    @Override
    public ServeType insert(ServeType serveType) {
        Long uid = ThreadLocalUtils.getUid();

        // 补充新信息
        serveType.setCreateBy(uid);
        serveType.setUpdateBy(uid);
        serveType.setCode(snowflake.generate());

        this.serveTypeMapper.insert(serveType);
        return serveType;
    }

    /**
     * 修改数据
     *
     * @param serveType 实例对象
     * @return 实例对象
     */
    @Override
    public ServeType update(ServeType serveType) {
        Long uid = ThreadLocalUtils.getUid();

        // 补充信息
        serveType.setUpdateBy(uid);
        this.serveTypeMapper.update(serveType);
        return this.queryById(serveType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {

        LambdaQueryWrapper<ServeType> queryWrapper = Wrappers.<ServeType>lambdaQuery()
                .eq(ServeType::getId, id)
                .eq(ServeType::getDelFlag, 0);
        ServeType serveType = baseMapper.selectOne(queryWrapper);

        if (serveType == null) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "已删除或不存在服务类型");
        }
        serveType.setDelFlag(DelFlagEnum.IS_DELETE.getStatus());
        return serveTypeMapper.update(serveType) > 0;
    }

    @Override
    public boolean activeById(Long id) {
        // 查询是否存在该服务类型
        ServeType serveType = serveTypeMapper.queryById(id);
        // 校验服务是否存在且未启用
        if (serveType == null || ServeEditStatus.ENABLE.equals(serveType.getActiveStatus())) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "已启用或服务类型不存在");
        }
        serveType.setActiveStatus(ServeEditStatus.ENABLE.getStatus());
        return serveTypeMapper.update(serveType) > 0;
    }

    @Override
    public boolean deactivateById(Long id) {
        // 查询是否存在该服务类型
        ServeType serveType = serveTypeMapper.queryById(id);
        // 校验服务类型是否存在且启用
        if (serveType == null || ServeEditStatus.DISABLE.equals(serveType.getActiveStatus())) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "已禁用或服务类型不存在");
        }
        // 校验服务类型下是否存在已经启用的服务项目，且不能有在售的服务关联
        int count = serveTypeMapper.countServeItemAndServeUsedByServeTypeId(id);
        if (count > 0) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + "服务类型下存在已经启用的服务项目或有在售的服务");
        }
        // 禁用服务类型会同时禁用掉其下的服务项目
        LambdaQueryWrapper<ServeItem> queryWrapper = Wrappers.<ServeItem>lambdaQuery()
                .eq(ServeItem::getServeTypeId, serveType.getId());
        // 获取需要禁用掉的服务项集合
        List<ServeItem> serveItems = serveItemMapper.selectList(queryWrapper)
                .stream().peek(serveItem -> {
                    serveItem.setActiveStatus(ServeEditStatus.DISABLE.getStatus());
                }).collect(Collectors.toList());
        // 更新禁用服务项目
        serveItemMapper.insertOrUpdateBatch(serveItems);
        // 更新禁用服务类型
        serveType.setActiveStatus(ServeEditStatus.DISABLE.getStatus());
        return serveTypeMapper.update(serveType) > 0;
    }

    @Override
    public List<ServeType> adminGetAllActiveServeType() {
        Long uid = ThreadLocalUtils.getUid();
        // TODO 管理员权限校验
        // 构建查询条件，服务类型未删除且启用
        LambdaQueryWrapper<ServeType> serveTypeLambdaQueryWrapper = Wrappers.<ServeType>lambdaQuery()
                .eq(ServeType::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus())
                .eq(ServeType::getActiveStatus, ServeEditStatus.ENABLE.getStatus());
        return this.serveTypeMapper.selectList(serveTypeLambdaQueryWrapper);
    }
}
