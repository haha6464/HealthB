package com.example.baseaccompanying.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huice.health.common.domain.ServeItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务项表(ServeItem)表数据库访问层
 *
 * @author Doge2077
 * @since 2024-06-04 17:40:10
 */
@Mapper
public interface ServeItemMapper extends BaseMapper<ServeItem> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ServeItem queryById(Long id);

    /**
     * 查询指定行的数据
     *
     * @param serveItem
     * @param page
     * @param size
     * @return
     */
    List<ServeItem> queryAllByLimit(@Param("obj") ServeItem serveItem, @Param("offset") Integer page, @Param("size") Integer size);

    /**
     * 统计总行数
     *
     * @param serveItem 查询条件
     * @return 总行数
     */
    long count(ServeItem serveItem);

    /**
     * 新增数据
     *
     * @param serveItem 实例对象
     * @return 影响行数
     */
    int insert(ServeItem serveItem);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ServeItem> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ServeItem> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ServeItem> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ServeItem> entities);

    /**
     * 修改数据
     *
     * @param serveItem 实例对象
     * @return 影响行数
     */
    int update(ServeItem serveItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

