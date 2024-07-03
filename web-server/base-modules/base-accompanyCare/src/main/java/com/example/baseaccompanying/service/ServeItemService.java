package com.example.baseaccompanying.service;

import huice.accompaniment.common.domain.ServeItem;
import org.springframework.data.domain.Page;

/**
 * 服务项表(ServeItem)表服务接口
 *
 * @author Doge2077
 * @since 2024-06-04 17:40:17
 */
public interface ServeItemService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ServeItem queryById(Long id);

    /**
     * 分页查询
     *
     * @param serveItem
     * @param page
     * @param size
     * @return
     */
    Page<ServeItem> queryByPage(ServeItem serveItem, Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param serveItem 实例对象
     * @return 实例对象
     */
    ServeItem insert(ServeItem serveItem);

    /**
     * 修改数据
     *
     * @param serveItem 实例对象
     * @return 实例对象
     */
    ServeItem update(ServeItem serveItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过主键启用服务项
     *
     * @param id 服务项 id
     * @return 是否成功
     */
    boolean activeById(Long id);
    /**
     * 通过主键禁用服务项
     *
     * @param id 服务项 id
     * @return 禁用是否成功
     */
    boolean deactivateById(Long id);
}
