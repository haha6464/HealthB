package huice.accompaniment.gatewayservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huice.accompaniment.gatewayservice.pojo.DO.RequestRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RequestRecordMapper extends BaseMapper<RequestRecordDO> {

}
