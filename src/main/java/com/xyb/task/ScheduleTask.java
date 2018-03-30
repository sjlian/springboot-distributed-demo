package com.xyb.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lian
 * @date 2017/12/13
 */
@Component
public class ScheduleTask {

    //每隔3000000ms会执行一次
    @Scheduled(fixedRate = 3000000)
    public void ratePrintTime() {
        System.out.println(new Date());
    }

    //可以具体到哪月哪几天周几几分几秒，具体配置请自行搜索Scheduled
    @Scheduled(cron = "0 0/1 * * * ?")
    public void schedulePrintTime() {
        System.out.println(new Date());
    }

}