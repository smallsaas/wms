package com.jfeat.am.module.statement.api.ut;

/**
 * Created by vincenthuang on 18/10/2017.
 */

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.jfeat.am.module.statement.services.EquipmentStatus;
import com.jfeat.am.module.statement.services.service.TableColumnRatesService;
import com.jfeat.base.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by jackyhuang on 2017/10/16.
 */
public class DemoTest extends BaseJunit {

    @Autowired
    private TableColumnRatesService tableColumnRatesService;

    @Before
    public void initData() {

    }

    /*@Test
    public void testCase()  throws Exception {
        String json = "";
        RequestBuilder request = post("/api/applicants").content(json);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }*/

    @Test
    public void testService()  throws Exception {
        String tableName = "equipment";
        String colmnName = "status";
        List<String> colmnContents = Lists.newArrayList();
        colmnContents.add(EquipmentStatus.DISABLE);
        colmnContents.add(EquipmentStatus.INNOVATION);
        colmnContents.add(EquipmentStatus.SCRAP);
        colmnContents.add(EquipmentStatus.SEAL);
        String type = "pie";
        Map<String,Object> map = tableColumnRatesService.queryEquipmentCountByStatus(tableName, colmnName, colmnContents, type);
        System.out.print("================"+map.toString()+"======================");
    }

    @Test
    public void testService1()  throws Exception {
        String tableName = "equipment";
        String colmnName = "status";
        List<Map<String,Object>> map = tableColumnRatesService.queryTableColumnRates(tableName, colmnName);
        System.out.print("================"+ map.toString()+"======================");
    }

}
