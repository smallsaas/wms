package com.jfeat.am.module.pcd.api.ut;

/**
 * Created by vincenthuang on 18/10/2017.
 */

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfeat.base.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jackyhuang on 2017/10/16.
 */
public class PcdTest extends BaseJunit {

    @Before
    public void initData() {

    }

    @Test
    public void testCase()  throws Exception {
        RequestBuilder request = get("/api/pub/pcd");
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }


    private Map<String, Object> map(Integer id, String name, Integer pid) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("pid", pid);
        return map;
    }

    @Test
    public void test() {
        List<Map<String, Object>> list = Lists.newArrayList();
        list.add(map(1, "GD", null));
        list.add(map(2, "SC", null));
        list.add(map(3, "GZ", 1));
        list.add(map(4, "LW", 3));
        list.add(map(5, "YX", 3));
        list.add(map(6, "CD", 2));

        List<Map<String, Object>> provinces = list.stream().filter(pcd -> pcd.get("pid") == null).collect(Collectors.toList());
        provinces.forEach(p -> {
            List<Map<String, Object>> cities = list.stream().filter(pcd -> p.get("id").equals(pcd.get("pid"))).collect(Collectors.toList());
            cities.forEach(c -> {
                List<Map<String, Object>> districts = list.stream().filter(pcd -> c.get("id").equals(pcd.get("pid"))).collect(Collectors.toList());
                c.put("children", districts);
            });
            p.put("children", cities);
        });

        System.out.println(JSON.toJSON(provinces));
    }
}
