package com.example.baseaccompanying.service;

import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.Hospital;
import huice.accompaniment.common.domain.bo.HospitalBo;
import huice.accompaniment.common.domain.vo.AdminGetHospitalListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * (Hospital)表服务接口
 *
 * @author Yida Yang
 * @since 2024-06-05 15:42:31
 */
public interface HospitalService {

    /**
     * 管理员获取医院集合
     * @return
     */
    public String adminGetHospitalList(Integer page, Integer size ,Integer status ,String name);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Hospital queryById(Long id);

    /**
     * 分页查询
     *
     * @param hospital 筛选条件
     * @param page     页数
     * @param size     页大小
     * @return 查询结果
     */
    PageImpl queryByPage(Hospital hospital, Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param hospital 实例对象
     * @return 实例对象
     */
    Hospital insert(Hospital hospital);

    /**
     * 修改数据
     *
     * @param hospital 实例对象
     * @return 实例对象
     */
    Hospital update(Hospital hospital);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
