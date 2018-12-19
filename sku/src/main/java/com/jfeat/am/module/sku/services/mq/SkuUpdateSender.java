package com.jfeat.am.module.sku.services.mq;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zy on 2018/12/18.
 */
@Component
public class SkuUpdateSender {
    private static final Logger logger = LoggerFactory.getLogger(SkuUpdateSender.class);
    public static final String MESSAGE_TYPE = "SKU";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendUpdateMessage(SkuMessage obj) throws JsonProcessingException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setRoutingKey(SkuMessageConfig.STORE_UPDATE_QUEUE);
        Message msg= MessageBuilder.withBody(objectMapper.writeValueAsBytes(obj)).build();
        logger.debug("send message = {}", JSONObject.toJSONString(obj));
        rabbitTemplate.convertAndSend(msg);
    }
}

