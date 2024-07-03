package com.example.baseaccompanying.service;

import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.Patientescortlist;

/**
 * (Patientescortlist)表服务接口
 *
 * @author Yida Yang
 * @since 2024-06-11 09:50:27
 */
public interface PatientescortlistService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Patientescortlist queryById(Long id);

    /**
     * 分页查询
     *
     * @param patientescortlist 筛选条件
     * @param page              页数
     * @param size              页大小
     * @return 查询结果
     */
    PageImpl<?> queryByPage(Patientescortlist patientescortlist, Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param patientescortlist 实例对象
     * @return 实例对象
     */
    Patientescortlist insert(Patientescortlist patientescortlist);

    /**
     * 修改数据
     *
     * @param patientescortlist 实例对象
     * @return 实例对象
     */
    Patientescortlist update(Patientescortlist patientescortlist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
