package com.lian.common.pubsub;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @Author lian
 * @Date 2019-07-29
 */
@Component
public class RabbitReceiver {
    public static final String topicExchangeName = "com.lian.demo.exchange";

    public static final String queueName = "com.lian.demo";


    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
