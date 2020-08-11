package com.jfeat.am.module.sku.services.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zy on 2018/12/18.
 */
@Configuration
public class SkuMessageConfig {
    public static final String STORE_UPDATE_QUEUE = "wms-update-queue";
    public static final String DEFAULT_EXCHANGE = "";
   /* @Bean
    public Queue storeUpdateQueue() {
        return new Queue(STORE_UPDATE_QUEUE);
    }*/
}
