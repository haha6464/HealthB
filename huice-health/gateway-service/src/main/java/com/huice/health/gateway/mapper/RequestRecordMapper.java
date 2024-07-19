package com.huice.health.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huice.health.gateway.pojo.DO.RequestRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RequestRecordMapper extends BaseMapper<RequestRecordDO> {

}
