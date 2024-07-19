package com.huice.health.manage.service;

import com.huice.health.common.core.PageImpl;
import com.huice.health.common.domain.City;

/**
 * 城市(City)表服务接口
 *
 * @author Yida Yang
 * @since 2024-06-04 13:08:26
 */
public interface CityService {
    /**
     * 获取城市集合
     *
     * @return
     */
    public String getCityList();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    City queryById(Long id);

    /**
     * 分页查询
     *
     * @param city 筛选条件
     * @param page 第几页
     * @param size 页大小
     * @return 查询结果
     */
    PageImpl queryByPage(City city, Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    City insert(City city);

    /**
     * 修改数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    City update(City city);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
