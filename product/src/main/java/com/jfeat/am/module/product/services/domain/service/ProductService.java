package com.jfeat.am.module.product.services.domain.service;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.module.product.services.crud.service.CRUDProductService;
import com.jfeat.am.module.product.services.domain.model.ProductDetailsModel;
import com.jfeat.am.module.product.services.domain.model.ProductModel;

/**
 * Created by vincent on 2017/10/19.
 */
public interface ProductService extends CRUDProductService{
    ProductModel getProductDetails(long id);


    /**
     * 返回属于产品的所有的属性对象
     * */
    ProductDetailsModel productDetails(Long id);
}