package com.example.baseaccompanying.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huice.health.common.core.PageImpl;
import com.huice.health.common.domain.Patientescortlist;
import com.huice.health.common.domain.vo.AdminGetPatientEscortListVo;

/**
 * (Patientescortlist)表服务接口
 *
 * @author Yida Yang
 * @since 2024-06-11 09:50:27
 */
public interface PatientescortlistService extends IService<Patientescortlist> {

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

    /**
     * 管理员获取陪诊师列表
     *
     * @param offset 页数
     * @param limit  大小
     * @return 陪诊师分页列表和总数
     */
    PageImpl<?> adminGetPatientEscortList(Integer offset, Integer limit);

    /**
     * 管理员分页条件搜索陪诊师列表
     *
     * @param escortName 陪诊师名字
     * @param escortSex  陪诊师年龄
     * @param hospitalId 医院id
     * @param offset     页数
     * @param limit      大小
     * @return 陪诊师列表
     */
    PageImpl<AdminGetPatientEscortListVo> adminFindEscortByNameAndSexAndHospital(String escortName, String escortSex, Long hospitalId, Integer offset, Integer limit);

    /**
     * 根据陪诊师 userId 启用陪诊师
     *
     * @param userId 陪诊师 userId
     * @return 是否成功
     */
    boolean activeByUserId(Long userId);

    /**
     * 根据陪诊师 userId 禁用陪诊师
     *
     * @param userId 陪诊师 userId
     * @return 是否成功
     */
    boolean deactiveByUserId(Long userId);

    /**
     * 审核陪诊师资质
     *
     * @param patientescortlist 陪诊师实体
     * @return 审核是否通过
     */
    boolean veryfyPatientEscort(Patientescortlist patientescortlist);
}
