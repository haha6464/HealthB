package com.example.baseaccompanying.service;

import huice.accompaniment.common.domain.ServeType;
import org.springframework.data.domain.Page;

/**
 * 服务类型表(ServeType)表服务接口
 *
 * @author Doge2077
 * @since 2024-06-04 15:30:25
 */
public interface ServeTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ServeType queryById(Long id);

    /**
     * 分页查询
     *
     * @param serveType
     * @param page
     * @param size
     * @return
     */
    Page<ServeType> queryByPage(ServeType serveType, Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param serveType 实例对象
     * @return 实例对象
     */
    ServeType insert(ServeType serveType);

    /**
     * 修改数据
     *
     * @param serveType 实例对象
     * @return 实例对象
     */
    ServeType update(ServeType serveType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过主键启用服务类型
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean activeById(Long id);

    /**
     * 通过主键禁用服务类型
     *
     * @param id
     * @return
     */
    boolean deactivateById(Long id);
}
