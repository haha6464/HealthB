package com.example.baseaccompanying.quartz.trigger;

import org.quartz.JobDataMap;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author Doge2077 2024/7/11
 */
@Configuration
public class AdminOnSaleServeTrigger {

    public Trigger createTrigger(Long serveId, Date publishTime) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("serveId", serveId);

        return TriggerBuilder.newTrigger()
                .withIdentity("adminOnSaleServeTrigger-" + serveId)
                .startAt(publishTime)
                .usingJobData(jobDataMap)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
