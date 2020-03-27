/*
package com.kevin.usc.pay.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AutoTaskFromDB implements SchedulingConfigurer {

//    @Autowired
//    protected CronMapper cronMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.addTriggerTask(() -> process(),
                triggerContext -> {
                    String cron = null;
                    if (cron.isEmpty()) {
                       log.info("cron 为空");
                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

    private  void process(){
        log.info("formDB ");
    }
}*/
