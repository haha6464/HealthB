package com.example.baseaccompanying.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huice.accompaniment.common.domain.ServeType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务类型表(ServeType)表数据库访问层
 *
 * @author Doge2077
 * @since 2024-06-04 15:30:25
 */
@Mapper
public interface ServeTypeMapper extends BaseMapper<ServeType> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ServeType queryById(Long id);

    /**
     * 查询指定行的数据
     *
     * @param serveType
     * @param page
     * @param size
     * @return
     */
    List<ServeType> queryAllByLimit(@Param("obj") ServeType serveType, @Param("offset") Integer page, @Param("size") Integer size);

    /**
     * 统计总行数
     *
     * @param serveType 查询条件
     * @return 总行数
     */
    long count(ServeType serveType);

    /**
     * 新增数据
     *
     * @param serveType 实例对象
     * @return 影响行数
     */
    int insert(ServeType serveType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ServeType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ServeType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ServeType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ServeType> entities);

    /**
     * 修改数据
     *
     * @param serveType 实例对象
     * @return 影响行数
     */
    int update(ServeType serveType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过服务类型id查询是否包含已启用的服务项目和在售的服务的记录数量
     *
     * @param id 服务类型 id
     * @return 记录数量
     */
    int countServeItemAndServeUsedByServeTypeId(Long id);
}

