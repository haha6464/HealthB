package com.huice.health.manage.quartz.scheduler;

import com.huice.health.common.constant.ErrorInfo;
import com.huice.health.manage.quartz.job.AdminOnSaleServeJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Doge2077 2024/7/11
 */
@Slf4j
@Component
public class AdminOnSaleServeScheduler {

    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

    @Resource
    private com.huice.health.manage.trigger.AdminOnSaleServeTrigger adminOnSaleServeTrigger;

    public void adminOnSaleServeScheduler(Long serveId, Date publishTime) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(AdminOnSaleServeJob.class)
                .withIdentity("adminOnSaleServeJob-" + serveId)
                .usingJobData("serveId", serveId)
                .build();

        Trigger trigger = adminOnSaleServeTrigger.createTrigger(serveId, publishTime);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("定时任务：" + jobDetail.getKey().getName() + " 调度" + ErrorInfo.Msg.PROCESS_FAILD + e.getMessage());
        }
    }
}
