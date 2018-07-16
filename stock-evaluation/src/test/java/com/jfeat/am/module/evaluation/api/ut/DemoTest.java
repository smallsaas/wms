package com.jfeat.am.module.evaluation.api.ut;

/**
 * Created by vincenthuang on 18/10/2017.
 */

import com.jfeat.am.test.BaseJunit;
import org.junit.Before;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by jackyhuang on 2017/10/16.
 */
public class DemoTest extends BaseJunit {

    @Before
    public void initData() {

    }

    @Test
    public void testCase()  throws Exception {
        /*String json = "";
        RequestBuilder request = post("/api/applicants").content(json);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();*/

//        logger.debug(result.getResponse().getContentAsString());
    }
}
