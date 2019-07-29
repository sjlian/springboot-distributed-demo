package com.lian.web;

import com.lian.common.pubsub.RedisReceiver;
import com.lian.web.response.RestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lian
 * @Date 2019-07-29
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("/send")
    public RestInfo send(String msg){
        stringRedisTemplate.convertAndSend(RedisReceiver.MESSAGE_CHANEL, msg);
        return new RestInfo<>();
    }

}
