package com.lian.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author lian
 * @Date 2019-07-26
 */
@Component
public class ScheduleTaskImpl implements ScheduleTask{
    //每隔3000000ms会执行一次
    @Override
    public void ratePrintTime() {
        System.out.println(new Date());
    }
    //可以具体到哪月哪几天周几几分几秒，具体配置请自行搜索Scheduled
    @Override
    public void schedulePrintTime() {
        System.out.println(new Date());
    }
}
