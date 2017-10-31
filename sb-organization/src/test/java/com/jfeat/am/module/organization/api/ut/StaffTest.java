package com.jfeat.am.module.organization.api.ut;

import com.jfeat.am.module.organization.services.crud.filter.StaffFilter;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.domain.model.StaffModel;
import com.jfeat.am.module.profile.services.persistence.model.Profile;
import com.jfeat.base.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;

/**
 * Created by Silent-Y on 2017/10/28.
 */
public class StaffTest extends BaseJunit{

    @Autowired
    StaffService staffService;
    private StaffFilter staffFilter = new StaffFilter();


    @Before
    public void initData() {
        StaffModel staffModel = new StaffModel();
        staffModel.setDeptId(924151610853089281L);
        staffModel.setPositionId(924151610853089281L);
        Profile profile = new Profile();
        profile.setName("Quinlan");
        profile.setSex("1");
        staffModel.setProfile(profile);
        staffService.createModel(staffModel, staffFilter);
    }

    @Test
    public void testCase()  throws Exception {
        StaffModel staffModel = new StaffModel();
        staffModel.setDeptId(924151610853089281L);
        staffModel.setPositionId(924151610853089281L);
        Profile profile = new Profile();
        profile.setName("Quinlan");
        profile.setSex("1");
        staffModel.setProfile(profile);

//        RequestBuilder request = post("/api/org/position").content(JSON.toJSONString(pos));
//        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//
//        logger.debug(result.getResponse().getContentAsString());
    }
}
