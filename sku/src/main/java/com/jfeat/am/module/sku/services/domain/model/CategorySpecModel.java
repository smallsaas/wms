package com.jfeat.am.module.sku.services.domain.model;

import com.jfeat.am.module.sku.services.crud.model.SkuSpecificationGroupModel;
import com.jfeat.am.module.sku.services.persistence.model.SkuSpecificationGroup;

import java.util.List;

public class CategorySpecModel {


    Long categoryId;
    List<SkuSpecificationGroupModel> groups;


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<SkuSpecificationGroupModel> getGroups() {
        return groups;
    }

    public void setGroups(List<SkuSpecificationGroupModel> groups) {
        this.groups = groups;
    }
}
