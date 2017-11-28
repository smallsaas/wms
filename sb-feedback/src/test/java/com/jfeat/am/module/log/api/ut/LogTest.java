package com.jfeat.am.module.log.api.ut;

import com.jfeat.am.base.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by jackyhuang on 2017/10/16.
 */
public class LogTest extends BaseJunit {


    @Before
    public void initData() {

    }

    @Test
    public void testGetLog() throws Exception {
        /*RequestBuilder request = get("/api/logs")
                .requestAttr("userId", "1345");
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        logger.debug(result.getResponse().getContentAsString());*/
    }
}
