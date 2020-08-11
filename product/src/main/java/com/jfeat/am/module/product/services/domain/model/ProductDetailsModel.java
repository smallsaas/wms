package com.jfeat.am.module.product.services.domain.model;

import com.jfeat.am.module.product.services.persistence.model.*;

import java.util.List;

public class ProductDetailsModel extends Product {

    String productCategoryName;
    List<Condition> conditions;
    List<ProductPhoto> photos;
    /*List<ProductTag> tags;*/
    List<ProductUnit> units;
    List<Specification> specifications;


    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<ProductPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ProductPhoto> photos) {
        this.photos = photos;
    }

    /*public List<ProductTag> getTags() {
        return tags;
    }

    public void setTags(List<ProductTag> tags) {
        this.tags = tags;
    }*/

    public List<ProductUnit> getUnits() {
        return units;
    }

    public void setUnits(List<ProductUnit> units) {
        this.units = units;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }
}
