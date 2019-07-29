package com.lian.conf;

import com.lian.common.pubsub.RedisReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Author lian
 * @Date 2019-07-29
 */
@Configuration
public class RedisConfig {
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        //订阅通道
        container.addMessageListener(listenerAdapter, new PatternTopic(RedisReceiver.MESSAGE_CHANEL));
        return container;
    }


    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * @param cs
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(RedisReceiver cs) {
        return new MessageListenerAdapter(cs, "receiveMessage");
    }
}
