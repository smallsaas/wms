package com.jfeat.am.base;

import com.jfeat.AmApplication;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.service.ProcurementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


/**
 * 基础测试类
 *
 * @author Admin
 * @Date 2017/5/21 16:10
 */
@ActiveProfiles(profiles = "test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmApplication.class)
@WebAppConfiguration
@Transactional //测试之后数据可回滚
public class BaseJunit {

    protected static final Logger logger = LoggerFactory.getLogger(BaseJunit.class);

    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void initDatabase(){
    }

    @Autowired
    private ProcurementService procurementService;

    @Test
    public void testDemo(){
        ProcurementModel procurementModel = new ProcurementModel();
        procurementModel.setOriginatorId(41L);
        StorageInModel storage = new StorageInModel();
        storage.setBatchNo("asdfsadf");
    }
}
