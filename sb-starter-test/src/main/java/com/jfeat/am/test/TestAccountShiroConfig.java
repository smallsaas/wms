package com.jfeat.am.test;

import com.jfeat.am.config.web.AccountShiroConfig;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jackyhuang on 2018/1/24.
 */
@Configuration
public class TestAccountShiroConfig extends AccountShiroConfig {

    @Bean
    public DefaultSecurityManager getDefaultSecurityManager() {
        logger.debug("#### TestAccountShiroConfig.DefaultSecurityManager");
        return new DefaultSecurityManager();
    }
}
