package com.jfeat.am.test;

import com.jfeat.am.base.BaseJunit;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jackyhuang
 * @date 2018/4/21
 */
public class NotifyTest extends BaseJunit {

    @Test
    public void testQueryNotifyNoArg() throws Exception {
        RequestBuilder request = get("/api/notification/notify");
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }

    @Test
    public void testQueryNotifyWithType() throws Exception {
        RequestBuilder request = get("/api/notification/notify?isRead=0&targetTypes[]=essay");
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }

    @Test
    public void testQueryNotifyWithTypes() throws Exception {
        RequestBuilder request = get("/api/notification/notify?isRead=0&targetTypes[]=essay&targetTypes[]=topic");
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }
}
