package com.lian.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author lian
 * @date 2017/11/22.
 */
@Component
public class AsyncTask {
    @Async
    public void asyncSayHello() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am asyncSayHello,and i will be after sayHello");
    }
}