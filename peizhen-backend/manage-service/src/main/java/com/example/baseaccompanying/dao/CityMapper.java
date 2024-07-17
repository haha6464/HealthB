package com.example.baseaccompanying.dao;

import huice.accompaniment.common.domain.City;
import huice.accompaniment.common.domain.vo.GetCityListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 城市(City)表数据库访问层
 *
 * @author Yida Yang
 * @since 2024-06-04 13:08:26
 */
@Mapper
public interface CityMapper {

    @Select("SELECT id as 'value' , city as 'label' from city")
    List<GetCityListVo> getCityList();

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
     * @param page 页数
     * @param size 页大小
     * @return 查询结果
     */
    List<City> queryAllByLimit(@Param("obj") City city, @Param("offset") Integer page, @Param("size") Integer size);

    /**
     * 统计总行数
     *
     * @param city 查询条件
     * @return 总行数
     */
    long count(City city);

    /**
     * 新增数据
     *
     * @param city 实例对象
     * @return 影响行数
     */
    int insert(City city);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<City> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<City> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<City> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<City> entities);

    /**
     * 修改数据
     *
     * @param city 实例对象
     * @return 影响行数
     */
    int update(City city);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

