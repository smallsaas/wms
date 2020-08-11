package com.jfeat.am.module.product.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.product.services.crud.service.CRUDProductCategoryService;
import com.jfeat.am.module.product.services.domain.model.ProductCategoryModel;
import com.jfeat.am.module.product.services.persistence.model.ProductCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2017/10/19.
 */
public interface ProductCategoryService extends CRUDProductCategoryService{

    @Transactional
    Integer createChildren(Long categoryId, ProductCategoryModel model);


    List<Map<String, Object>> findAllToMap();
    List<Map<String, Object>> findAllGrouping();
    List<ProductCategory> queryPcdByName(String name);
    List<ProductCategory> queryPcdByPid(Long pid);
    List<ProductCategory> queryByName(Page<ProductCategory> page,String categoryName);

    List<ProductCategory> categories(Page<ProductCategory> page);
    
}