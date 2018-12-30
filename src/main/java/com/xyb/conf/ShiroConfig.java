package com.xyb.conf;

import com.xyb.shiro.DataSourceShiroRealm;
import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    public Realm dataSourceRealm(){
        return new DataSourceShiroRealm();
    }
}
