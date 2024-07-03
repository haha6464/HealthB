package com.example.baseaccompanying.service;

import huice.accompaniment.common.domain.Serve;
import org.springframework.data.domain.Page;

/**
 * 服务表(Serve)表服务接口
 *
 * @author Doge2077
 * @since 2024-06-06 14:43:00
 */
public interface ServeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Serve queryById(Long id);

    /**
     * 分页查询
     *
     * @param serve
     * @param page
     * @param size
     * @return
     */
    Page<Serve> queryByPage(Serve serve, Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param serve 实例对象
     * @return 实例对象
     */
    Serve insert(Serve serve);

    /**
     * 修改数据
     *
     * @param serve 实例对象
     * @return 实例对象
     */
    Serve update(Serve serve);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过主键上架服务
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean onSaleById(Long id);
}
