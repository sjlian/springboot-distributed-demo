package com.lian.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lian
 * @date 2017/12/13
 */
public interface ScheduleTask {

    @Scheduled(fixedRate = 300000000)
    void ratePrintTime();
    @Scheduled(cron = "0 0/10 * * * ?")
    void schedulePrintTime();
}