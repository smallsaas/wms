package com.jfeat.am.module.organization.api.ut;

import com.alibaba.fastjson.JSON;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.domain.model.StaffModel;
import com.jfeat.am.module.profile.services.persistence.model.Profile;
import com.jfeat.base.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Silent-Y on 2017/10/28.
 */
public class StaffTest extends BaseJunit{

    @Autowired
    StaffService staffService;
    //private StaffFilter staffFilter = new StaffFilter();

    @Before
    public void initData() {
        /*StaffModel staffModel = new StaffModel();
        staffModel.setDeptId(924151610853089281L);
        staffModel.setPositionId(924151610853089281L);
        Profile profile = new Profile();
        profile.setName("Quinlan");
        profile.setSex("1");
        staffModel.setProfile(profile);
        staffService.createModel(staffModel, null);*/
    }

    @Test
    public void testCreateStaff()  throws Exception {
        StaffModel staffModel = new StaffModel();
        staffModel.setDeptId(924151610853089281L);
        staffModel.setPositionId(924151610853089281L);
        Profile profile = new Profile();
        profile.setName("Quinlan");
        profile.setSex("1");
        staffModel.setProfile(profile);

        RequestBuilder request = post("/api/org/staffs").content(JSON.toJSONString(staffModel));
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        logger.debug(result.getResponse().getContentAsString());
    }
}
