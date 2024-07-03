package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.DO.SingleFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/11 11:29
 */
@Mapper
public interface FileMapper extends BaseMapper<SingleFile> {
}
