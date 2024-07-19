package com.example.baseaccompanying.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huice.health.common.domain.Patientescortlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Patientescortlist)表数据库访问层
 *
 * @author Yida Yang
 * @since 2024-06-11 09:50:27
 */
@Mapper
public interface PatientescortlistMapper extends BaseMapper<Patientescortlist> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Patientescortlist queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param patientescortlist 查询条件
     * @param offset            跳过
     * @param size              数量
     * @return 对象列表
     */
    List<Patientescortlist> queryAllByLimit(@Param("obj") Patientescortlist patientescortlist, @Param("offset") Integer offset, @Param("size") Integer size);

    /**
     * 统计总行数
     *
     * @param patientescortlist 查询条件
     * @return 总行数
     */
    long count(Patientescortlist patientescortlist);

    /**
     * 新增数据
     *
     * @param patientescortlist 实例对象
     * @return 影响行数
     */
    int insert(Patientescortlist patientescortlist);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Patientescortlist> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Patientescortlist> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Patientescortlist> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Patientescortlist> entities);

    /**
     * 修改数据
     *
     * @param patientescortlist 实例对象
     * @return 影响行数
     */
    int update(Patientescortlist patientescortlist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 管理员获取其创建的陪诊师列表
     *
     * @param uid    管理员 uid
     * @param offset 页数
     * @param limit  大小
     * @return
     */
    List<Patientescortlist> addminGetPatientescortListByUidPage(@Param("uid") Long uid, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 获取数量
     *
     * @param escortName
     * @param escortSex
     * @param hospitalId
     * @param uid
     * @return
     */
    long countAdminFindEscort(@Param("uid") Long uid, @Param("name") String escortName, @Param("gender") String escortSex, @Param("hospital_id") Long hospitalId);

    /**
     * 分页返回条件查询的陪诊师列表
     *
     * @param uid
     * @param escortName
     * @param escortSex
     * @param hospitalId
     * @param offset
     * @param limit
     * @return
     */
    List<Patientescortlist> getAdminFindEscortPage(@Param("uid") Long uid, @Param("name") String escortName, @Param("gender") String escortSex, @Param("hospital_id") Long hospitalId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 管理员所属医院下所有的陪诊师
     *
     * @param uid
     * @return
     */
    long countPatientescortList(Long uid);
}

