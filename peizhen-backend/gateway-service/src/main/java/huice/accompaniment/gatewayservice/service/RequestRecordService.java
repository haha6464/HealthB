package huice.accompaniment.gatewayservice.service;

import huice.accompaniment.gatewayservice.pojo.DO.RequestRecordDO;
import huice.accompaniment.gatewayservice.mapper.RequestRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class RequestRecordService {

    @Resource
    RequestRecordMapper requestRecordMapper;

    @Async
    public void insertRequestRecord(RequestRecordDO requestRecordDO){
        if (requestRecordMapper.insert(requestRecordDO) !=1) {
            log.error("请求记录插入失败. "+requestRecordDO);
        }
    }
}
