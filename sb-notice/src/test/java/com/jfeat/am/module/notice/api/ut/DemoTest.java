package com.jfeat.am.module.notice.api.ut;

/**
 * Created by vincenthuang on 18/10/2017.
 */

import com.alibaba.fastjson.JSON;
import com.jfeat.am.module.notice.services.crud.service.NoticeService;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.base.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jackyhuang on 2017/10/16.
 */
public class DemoTest extends BaseJunit {

    @Autowired
    private NoticeService noticeService;

    @Before
    public void initData() {
        Notice notice = new Notice();
        notice.setId(1245231533451L);
        notice.setEnable(1);
        notice.setType("notice");
        notice.setTitle("公告");
        notice.setContent("全场五折");
        notice.setCreateTime(new Date());
        noticeService.createMaster(notice);
    }

    @Test
    public void testPost()  throws Exception {
        String json = "{\n" +
                "    \"type\":\"1\",\n" +
                "    \"enable\":\"1\",\n" +
                "    \"title\":\"公告\",\n" +
                "    \"content\":\"大减价,所有商品一律五折\",\n" +
                "}";
        RequestBuilder request = post("/api/notice/notice").content(json);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }
    @Test
    public void testGet() throws Exception {
        RequestBuilder request = get("/api/notice/notice/1245231533451");
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }

    @Test
    public void testPut() throws Exception {
        String json = "{\n" +
                "    \"type\":\"1\",\n" +
                "    \"enable\":\"1\",\n" +
                "    \"title\":\"公告1\",\n" +
                "    \"content\":\"大减价,所有商品一律五折1\",\n" +
                "    \"createTime\":\"\",\n" +
                "    \"updateTime\":\"2017-11-02 12:01:47\"\n" +
                "}";
        RequestBuilder request = put("/api/notice/notice/1245231533451").content(json);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }

    @Test
    public void testDelete() throws Exception {
        RequestBuilder request = delete("/api/notice/notice/1245231533451");
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }


}
