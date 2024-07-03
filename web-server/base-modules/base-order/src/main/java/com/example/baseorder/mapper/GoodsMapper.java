package com.example.baseorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.baseorder.pojo.DO.GoodsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/2 9:14
 */
public interface GoodsMapper extends BaseMapper<GoodsDO> {

    @Select("UPDATE hc_goods\n" +
            "SET remain_num = CASE WHEN remain_num >= 1 THEN remain_num - 1 ELSE -1 END\n" +
            "WHERE id = #{goodsId};\n" +
            "\n" +
            "SELECT *\n" +
            "FROM hc_goods\n" +
            "WHERE id = #{goodsId} AND remain_num >= 0;")
    GoodsDO selectGoodsIfRemainByJoinGroup(Long goodsId);

    @Select("UPDATE hc_goods SET remain_num = CASE WHEN remain_num == 1 THEN 0 ELSE -1 END" +
            "WHERE id = #{goodsId};" +
            "SELECT * FROM hc_goods WHERE id = #{goodsId} and remain_num==0;")
    GoodsDO selectGoodsIfRemainByOneToOne(Long goodsId);
}
