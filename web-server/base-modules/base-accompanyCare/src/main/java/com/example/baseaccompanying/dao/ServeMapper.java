package com.example.baseaccompanying.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huice.accompaniment.common.domain.Serve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务表(Serve)表数据库访问层
 *
 * @author Doge2077
 * @since 2024-06-06 14:43:00
 */
@Mapper
public interface ServeMapper extends BaseMapper<Serve> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Serve queryById(Long id);

    /**
     * 查询指定行的数据
     *
     * @param obj
     * @param page
     * @param size
     * @return
     */
    List<Serve> queryAllByLimit(@Param("obj") Serve obj, @Param("userId") Long uid, @Param("offset") Integer page, @Param("size") Integer size);

    /**
     * 统计总行数
     *
     * @param serve 查询条件
     * @return 总行数
     */
    Long count(@Param("obj") Serve serve, @Param("userId") Long userId);

    /**
     * 新增数据
     *
     * @param serve 实例对象
     * @return 影响行数
     */
    int insert(Serve serve);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Serve> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Serve> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Serve> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Serve> entities);

    /**
     * 修改数据
     *
     * @param serve 实例对象
     * @return 影响行数
     */
    int update(Serve serve);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 后台管理端-服务搜索-查询总数
     * @param serveName 服务名称关键字
     * @param status 服务状态
     * @return 记录数量
     */
    long countSearchServe(@Param("serveName") String serveName, @Param("status") Integer status, @Param("uid") Long uid);

}

