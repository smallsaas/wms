package com.jfeat.am.module.organization.api.ut;

/**
 * Created by vincenthuang on 18/10/2017.
 */

import com.alibaba.fastjson.JSON;
import com.jfeat.am.module.organization.services.crud.service.PositionService;
import com.jfeat.am.module.organization.services.persistence.model.Position;
import com.jfeat.base.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jackyhuang on 2017/10/16.
 */
public class PositionTest extends BaseJunit {

    @Autowired
    PositionService positionService;

    Position pos;

    @Before
    public void initData() {
        pos = new Position();
        pos.setName("工程师");
        pos.setNote("高级");
        positionService.createMaster(pos);
    }

    @Test
    public void testCase()  throws Exception {
        //pos.setId(null);
        pos.setName(pos.getName()+"xxx");

        RequestBuilder request = get("/api/org/position").content(JSON.toJSONString(pos));
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }
}
