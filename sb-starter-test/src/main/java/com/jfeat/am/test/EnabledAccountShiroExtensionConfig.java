package com.jfeat.am.test;

import com.jfeat.am.config.web.EnabledAccountShiroExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jackyhuang on 2018/1/25.
 */
@Configuration
public class EnabledAccountShiroExtensionConfig {

    @Bean
    public EnabledAccountShiroExtension enabledAccountShiroExtension() {
        return new EnabledAccountShiroExtension();
    }
}
