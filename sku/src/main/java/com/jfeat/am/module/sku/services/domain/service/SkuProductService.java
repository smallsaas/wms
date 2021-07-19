package com.jfeat.am.module.sku.services.domain.service;

import com.jfeat.am.module.sku.services.crud.service.CRUDSkuProductService;
import com.jfeat.am.module.sku.services.domain.model.CreateSkuProductModel;
import com.jfeat.crud.base.request.Ids;
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
     * update sku 以及 产品 ，只有一个 sku
     * */
    @Transactional
    public Integer updateSkuMaster(Long skuId, CreateSkuProductModel model);

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
    Integer deleteProduct(Long skuId);


    /**
     * 删除单个 sku
     * */
    @Transactional
    Integer deleteSku(Long skuId);

    /**
     *  批量删除sku
     */
    @Transactional
    Integer bulkDeleteSku(Ids ids);


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