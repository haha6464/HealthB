package com.example.baseaccompanying.quartz.job;

import com.example.baseaccompanying.service.ServeService;
import huice.accompaniment.common.constant.ErrorInfo;
import huice.accompaniment.common.domain.Serve;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

/**
 * @author Doge2077 2024/7/11
 */
@Slf4j
public class AdminOnSaleServeJob extends QuartzJobBean {

    @Resource
    ServeService serveService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Long serveId = jobExecutionContext.getJobDetail().getJobDataMap().getLong("serveId");
        Serve serve = this.serveService.adminOnSaleServeById(serveId);
        if (serve != null) {
            log.info("定时任务" + jobExecutionContext.getJobDetail().getKey().getGroup() + ": " + jobExecutionContext.getJobDetail().getKey().getName() + " 调度成功");
        } else {
            log.error(jobExecutionContext.getJobDetail().getKey().getGroup() + ": " + jobExecutionContext.getJobDetail().getKey().getName() + ErrorInfo.Msg.PROCESS_FAILD);
        }
    }
}
