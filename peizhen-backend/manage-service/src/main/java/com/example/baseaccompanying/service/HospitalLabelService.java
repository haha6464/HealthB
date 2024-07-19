package com.example.baseaccompanying.service;

import com.huice.health.common.core.PageImpl;
import com.huice.health.common.domain.HospitalLabel;

/**
 * (HospitalLabel)表服务接口
 *
 * @author Yida Yang
 * @since 2024-06-03
 */
public interface HospitalLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HospitalLabel queryById(Long id);

    /**
     * 分页查询
     *
     * @param hospitalLabel 筛选条件
     * @param page          页数
     * @param size          页大小
     * @return 查询结果
     */
    PageImpl queryByPage(HospitalLabel hospitalLabel, Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param hospitalLabel 实例对象
     * @return 实例对象
     */
    HospitalLabel insert(HospitalLabel hospitalLabel);

    /**
     * 修改数据
     *
     * @param hospitalLabel 实例对象
     * @return 实例对象
     */
    HospitalLabel update(HospitalLabel hospitalLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
