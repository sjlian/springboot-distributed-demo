package com.xyb.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        List<Map<String, Object>> srcInstanceData = new LinkedList();

        Map<String, Object> srcInstanceValue = new HashMap<String, Object>();
        String srcCommonKey = "";

        for(Map<String, Object> srcInstance: srcInstanceData){
            srcInstanceValue = (Map<String, Object>) srcInstance.get("entityAttributes");
            if (srcInstanceValue.get("E.CommonKey")!=null){
                srcCommonKey += srcInstanceValue.get("E.CommonKey")+",";
            }
        }
        System.out.println("I am asyncSayHello,and i will be after sayHello");
    }
}