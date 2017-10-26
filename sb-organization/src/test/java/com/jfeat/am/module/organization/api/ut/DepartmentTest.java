package com.jfeat.am.module.organization.api.ut;

/**
 * Created by vincenthuang on 18/10/2017.
 */

import com.alibaba.fastjson.JSON;
import com.jfeat.base.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jackyhuang on 2017/10/16.
 */
public class DepartmentTest extends BaseJunit {

    @Before
    public void initData() {

    }

    @Test
    public void testCase()  throws Exception {
        String json = "";
        RequestBuilder request = get("/api/org/dept").content(json);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }
}
