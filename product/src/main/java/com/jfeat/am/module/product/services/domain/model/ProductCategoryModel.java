package com.jfeat.am.module.product.services.domain.model;

import com.jfeat.am.module.product.services.persistence.model.ProductCategory;

import java.util.List;

/**
 * Created by leuo on 2018/7/18.
 */
public class ProductCategoryModel {

    List<ProductCategory> categories;

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories) {
        this.categories = categories;
    }
}
