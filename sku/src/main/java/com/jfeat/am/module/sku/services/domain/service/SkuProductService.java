package com.jfeat.am.module.sku.services.domain.service;

import com.jfeat.am.common.constant.tips.Ids;
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

    /**
     * 删除 产品下所有的 sku
     *
     * */
    @Transactional
    Integer deleteSkus(Long productId);


    /**
     * 删除 产品下所有的 sku 及 产品
     * */
    @Transactional
    Integer deleteProduct(Long productId);

    /**
     *
     * 删除 单个 sku
     *
     */
    @Transactional
    Integer deleteSku(Ids ids);


    /**
     *
     * all sku in this product
     * */
    CreateSkuProductModel skuDetails(Long skuId);


    /**
     * all sku msg including product
     */
    CreateSkuProductModel skuTotalDetails(Long id);
}