package com.jfeat.am.module.product.services.domain.model;

import com.jfeat.am.module.product.services.persistence.model.Product;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-19
 */
public class ProductRecord extends Product{


    String productCategoryName;
    List<String> conditionName;
    List<String> tagName;
    String supplierName;
    List<String> unitName;


    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public List<String> getConditionName() {
        return conditionName;
    }

    public void setConditionName(List<String> conditionName) {
        this.conditionName = conditionName;
    }

    public List<String> getTagName() {
        return tagName;
    }

    public void setTagName(List<String> tagName) {
        this.tagName = tagName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<String> getUnitName() {
        return unitName;
    }

    public void setUnitName(List<String> unitName) {
        this.unitName = unitName;
    }
}
