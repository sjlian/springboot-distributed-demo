package com.lian.common.pubsub;

import org.springframework.stereotype.Component;

/**
 * @Author lian
 * @Date 2019-07-29
 */
@Component
public class RedisReceiver {
    public static final String MESSAGE_CHANEL = "com.lian.demo";

    /**
     * 接收消息的方法
     */
    public void receiveMessage(String message, String chanel) {
        System.out.println("收到一条消息：" + message);
        System.out.println("通道名称：" + chanel);
    }
}
