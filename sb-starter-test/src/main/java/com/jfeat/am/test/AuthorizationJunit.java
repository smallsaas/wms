package com.jfeat.am.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jackyhuang on 2018/1/24.
 */
public class AuthorizationJunit extends BaseJunit {

    private String accessToken;

    protected String getAuthorization() {
        return "Bearer " + accessToken;
    }

    @Before
    public void login() throws Exception {
        Map<String, String> login = new HashMap<>();
        login.put("account", "admin");
        login.put("password", "111111");
        RequestBuilder request = post("/api/oauth/login").content(JSON.toJSONString(login));
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(result.getResponse().getContentAsString());
        logger.debug(result.getResponse().getContentAsString());
        accessToken = jsonObject.getJSONObject("data").getString("accessToken");
    }


}
