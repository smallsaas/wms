package com.jfeat.am.module.product.services.domain.model;

import com.jfeat.am.module.product.services.persistence.model.Product;
import com.jfeat.am.module.product.services.persistence.model.ProductCondition;
import com.jfeat.am.module.product.services.persistence.model.ProductPhoto;
import com.jfeat.am.module.product.services.persistence.model.ProductUnit;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-19
 */
public class ProductModel extends Product {
    private List<ProductPhoto> productPhotos;
    private List<ProductUnit> productUnits;
    private List<ProductCondition> productConditions;

    /*private List<ProductTagRelation> productTagRelations;*/



    String productCategoryName;
    List<String> conditionName;
    List<String> tagName;
    // TODO needs dependency


    /*public List<ProductTagRelation> getProductTagRelations() {
        return productTagRelations;
    }

    public void setProductTagRelations(List<ProductTagRelation> productTagRelations) {
        this.productTagRelations = productTagRelations;
    }*/

    public List<ProductCondition> getProductConditions() {
        return productConditions;
    }

    public void setProductConditions(List<ProductCondition> productConditions) {
        this.productConditions = productConditions;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public List<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }

    public List<ProductUnit> getProductUnits() {
        return productUnits;
    }

    public void setProductUnits(List<ProductUnit> productUnits) {
        this.productUnits = productUnits;
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
}
