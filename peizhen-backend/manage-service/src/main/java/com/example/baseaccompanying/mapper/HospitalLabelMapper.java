package com.example.baseaccompanying.mapper;

import huice.accompaniment.common.domain.HospitalLabel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 医院标标签
 * (HospitalLabel)表数据库访问层
 *
 * @author Yida Yang
 * @since 2024-06-03
 */
@Mapper
public interface HospitalLabelMapper {

    @Delete("delete  from hospital_label where hospital_id = #{hospitalid}")
    Long deleteByHospitalId(@Param("hospitalid") Long hospitalId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HospitalLabel queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param hospitalLabel 查询条件
     * @param page          页数
     * @param size          页大小
     * @return 对象列表
     */
    List<HospitalLabel> queryAllByLimit(@Param("obj") HospitalLabel hospitalLabel, @Param("offset") Integer page, @Param("pageSize") Integer size);

    /**
     * 统计总行数
     *
     * @param hospitalLabel 查询条件
     * @return 总行数
     */
    long count(HospitalLabel hospitalLabel);

    /**
     * 新增数据
     *
     * @param hospitalLabel 实例对象
     * @return 影响行数
     */
    int insert(HospitalLabel hospitalLabel);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HospitalLabel> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HospitalLabel> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HospitalLabel> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException
     */
    int insertOrUpdateBatch(@Param("entities") List<HospitalLabel> entities);

    /**
     * 修改数据
     *
     * @param hospitalLabel 实例对象
     * @return 影响行数
     */
    int update(HospitalLabel hospitalLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

