package com.jfeat.am.module.sku.services.domain.service;

import com.jfeat.am.module.sku.services.crud.model.SkuProductModel;
import com.jfeat.am.module.sku.services.crud.service.CRUDSkuProductService;
import com.jfeat.am.module.sku.services.domain.model.CreateSkuProductModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface SkuProductService extends CRUDSkuProductService{


    @Transactional
    Integer createSku(CreateSkuProductModel model);

    @Transactional
    Integer updateSku(Long skuId,CreateSkuProductModel entity);

    @Transactional
    Integer deleteSku(Long skuId);
}