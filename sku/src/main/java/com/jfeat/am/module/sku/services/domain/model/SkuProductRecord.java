package com.jfeat.am.module.sku.services.domain.model;

import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;

import java.util.List;

/**
 * Created by Code Generator on 2018-07-18
 */
public class SkuProductRecord extends SkuProduct{

    List<String> tagName;

    List<String> specName;

    String categoryName;


    public List<String> getTagName() {
        return tagName;
    }

    public void setTagName(List<String> tagName) {
        this.tagName = tagName;
    }

    public List<String> getSpecName() {
        return specName;
    }

    public void setSpecName(List<String> specName) {
        this.specName = specName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
