package com.jfeat.am.module.organization.api.ut;

/**
 * Created by vincenthuang on 18/10/2017.
 */

import com.jfeat.am.common.crud.CRUD;
import com.jfeat.am.module.organization.services.crud.service.DepartmentService;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import com.jfeat.base.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jackyhuang on 2017/10/16.
 */
public class DepartmentTest extends BaseJunit {

    @Autowired
    DepartmentService departmentService;

    Department dev;

    @Before
    public void initData() {
        Department root = new Department();
        root.setCode("0");
        root.setName("广州分公司");
        root.setIsOrg(1);
        departmentService.createGroup(root);

        dev = new Department();
        dev.setCode("A001");
        dev.setName("开发部");
        dev.setPid(root.getId());
        departmentService.createGroup(dev);

        Department t11 = new Department();
        t11.setCode("A002");
        t11.setName("测试");
        //t11.setPid(dev.getId());
        departmentService.createGroup(t11);
        Department t12 = new Department();
        t12.setCode("A002");
        t12.setName("研发");
        //t12.setPid(dev.getId());
        departmentService.createGroup(t12);

        Department t2 = new Department();
        t2.setCode("B001");
        t2.setName("市场部");
        t2.setPid(root.getId());
        departmentService.createGroup(t2);

        Department t3 = new Department();
        t3.setCode("C001");
        t3.setName("财务部");
        t3.setPid(root.getId());
        departmentService.createGroup(t3);
    }

    @Test
    public void testCase()  throws Exception {

        dev.setName(dev.getName() + "xxx");
        String json = CRUD.toJSONObject(dev).toJSONString();

        RequestBuilder request = delete("/api/org/dept/" + dev.getId()).content(json);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }
}
