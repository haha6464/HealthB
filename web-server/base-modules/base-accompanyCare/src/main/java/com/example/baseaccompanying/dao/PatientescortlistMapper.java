package com.example.baseaccompanying.dao;

import huice.accompaniment.common.domain.Patientescortlist;
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
public interface PatientescortlistMapper {

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

}

