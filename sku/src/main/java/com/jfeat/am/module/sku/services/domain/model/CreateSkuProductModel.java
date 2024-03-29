package com.jfeat.am.module.sku.services.domain.model;

import com.jfeat.am.module.product.services.persistence.model.Product;
import com.jfeat.am.module.sku.services.crud.model.SkuProductModel;

import java.util.List;

public class CreateSkuProductModel extends Product {
    List<SkuProductModel> skus;
    /**
     * 产品类别名称
     */
    private String categoryName;

    public List<SkuProductModel> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuProductModel> skus) {
        this.skus = skus;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
