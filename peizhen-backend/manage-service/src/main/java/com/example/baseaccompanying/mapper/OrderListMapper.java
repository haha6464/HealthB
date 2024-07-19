package com.example.baseaccompanying.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huice.health.common.domain.OrderList;
import com.huice.health.common.domain.bo.AdminGetListDataBo;
import com.huice.health.common.domain.vo.AdminGetListDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单(OrderList)表数据库访问层
 *
 * @author Yida Yang
 * @since 2024-06-14 14:51:34
 */
@Mapper
public interface OrderListMapper extends BaseMapper<OrderList> {

    /**
     * 管理员获取集合数据
     *
     * @param adminGetListDataBo
     * @return
     */
    public List<AdminGetListDataVo> adminGetListData(AdminGetListDataBo adminGetListDataBo);

    /**
     * 管理员获取集合数量
     *
     * @param adminGetListDataBo
     * @return
     */
    public Long adminGetCount(AdminGetListDataBo adminGetListDataBo);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderList queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param orderList 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<OrderList> queryAllByLimit(OrderList orderList, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param orderList 查询条件
     * @return 总行数
     */
    long count(OrderList orderList);

    /**
     * 新增数据
     *
     * @param orderList 实例对象
     * @return 影响行数
     */
    int insert(OrderList orderList);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderList> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OrderList> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderList> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<OrderList> entities);

    /**
     * 修改数据
     *
     * @param orderList 实例对象
     * @return 影响行数
     */
    int update(OrderList orderList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

