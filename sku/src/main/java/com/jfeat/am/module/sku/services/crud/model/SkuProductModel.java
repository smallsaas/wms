package com.jfeat.am.module.sku.services.crud.model;

import java.util.List;

import com.jfeat.am.module.product.services.persistence.model.Product;
import com.jfeat.am.module.sku.services.persistence.model.*;

/**
 * Created by Code Generator on 2018-07-18
 */
public class SkuProductModel extends SkuProduct {
    private List<SkuUnit> skuUnits;
    private List<SkuPhoto> skuPhotos;
    private List<SkuTagRelation> skuTags;
    private List<SkuConditionRelation> skuConditions;
    List<Long> specId;


    public List<SkuUnit> getSkuUnits() {
        return skuUnits;
    }

    public void setSkuUnits(List<SkuUnit> skuUnits) {
        this.skuUnits = skuUnits;
    }


    public List<SkuPhoto> getSkuPhotos() {
        return skuPhotos;
    }

    public void setSkuPhotos(List<SkuPhoto> skuPhotos) {
        this.skuPhotos = skuPhotos;
    }

    public List<Long> getSpecId() {
        return specId;
    }

    public void setSpecId(List<Long> specId) {
        this.specId = specId;
    }

    public List<SkuTagRelation> getSkuTags() {
        return skuTags;
    }

    public void setSkuTags(List<SkuTagRelation> skuTags) {
        this.skuTags = skuTags;
    }

    public List<SkuConditionRelation> getSkuConditions() {
        return skuConditions;
    }

    public void setSkuConditions(List<SkuConditionRelation> skuConditions) {
        this.skuConditions = skuConditions;
    }
}
