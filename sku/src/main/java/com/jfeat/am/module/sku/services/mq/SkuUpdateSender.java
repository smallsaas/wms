package com.jfeat.am.module.sku.services.mq;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfeat.am.core.util.JsonKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zy on 2018/12/18.
 */
@Component
public class SkuUpdateSender {
    private static final Logger logger = LoggerFactory.getLogger(SkuUpdateSender.class);
    public static final String MESSAGE_TYPE = "SKU";

    //@Autowired
    //private RabbitTemplate rabbitTemplate;


    public void sendUpdateMessage(SkuMessage obj) {
        String jsonMessage = JsonKit.toJson(obj);
        logger.debug("send message to mq {}, msg = {}", SkuMessageConfig.STORE_UPDATE_QUEUE, jsonMessage);
        //rabbitTemplate.convertAndSend(SkuMessageConfig.STORE_UPDATE_QUEUE, SerializationUtils.serialize(jsonMessage));
    }
}

