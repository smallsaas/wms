package com.jfeat.am.module.log.api.ut;

import com.jfeat.base.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by jackyhuang on 2017/10/16.
 */
public class LogTest extends BaseJunit {

    @Test
    public void testGetLog() throws Exception {
        RequestBuilder request = get("/api/logs");
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
