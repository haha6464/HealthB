package com.example.baseaccompanying.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ToHospitalMapper {
    //查看所属医院
    @Select("select hospital_id from to_hospital where user_id = #{id} limit 1")
    public Long getIdByUserId(@Param("id")Long id);
}
