package com.jfeat.am.module.sku.services.domain.model;

import com.jfeat.am.module.product.services.persistence.model.Product;
import com.jfeat.am.module.sku.services.crud.model.SkuProductModel;

import java.util.List;

public class CreateSkuProductModel {
    Product product;
    List<SkuProductModel> skus;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<SkuProductModel> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuProductModel> skus) {
        this.skus = skus;
    }
}
