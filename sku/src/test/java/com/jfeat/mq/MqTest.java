package com.jfeat.mq;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfeat.AmApplication;
import com.jfeat.am.module.sku.services.domain.model.CreateSkuProductModel;
import com.jfeat.am.module.sku.services.domain.service.SkuProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zy on 2018/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AmApplication.class)
public class MqTest {
    @Resource
    SkuProductService skuProductService;
    /**
     * send message test
     **/
    @Test
    public void testMq() throws JsonProcessingException {
        String str = "{\"id\":\"22\",\"barCode\":\"wudangshan2\",\"costPrice\":100.12,\"createTime\":\"\",\"description\":\"\",\"englishName\":\"\",\"field1\":\"位\",\"field4\":\"\",\"field5\":\"\",\"name\":\"全真七子\",\"price\":\"\",\"productCategoryId\":\"34\",\"productCode\":\"P2018041200004Wxq\",\"productStandard\":\"\",\"purchaseAdvance\":\"\",\"quantities\":\"\",\"readjustCostPrice\":\"\",\"searchKeyWord\":\"\",\"sortValue\":\"\",\"spec\":\"全真七子全上\",\"specification\":\"\",\"status\":\"\",\"stockCost\":\"\",\"suggestedPrice\":\"\",\"updateTime\":\"\",\"volume\":\"1000磅\",\"weight\":\"人均180\"}";
        CreateSkuProductModel model = JSONObject.parseObject(str, CreateSkuProductModel.class);

        skuProductService.updateSkuMaster(20L, model);
    }
}
