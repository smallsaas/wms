package com.jfeat.am.module.sku.services.domain.model;

import com.jfeat.am.module.product.services.persistence.model.ProductCategory;
import com.jfeat.am.module.sku.services.crud.model.SkuSpecificationGroupModel;

import java.util.List;

public class CategorySpecModel extends ProductCategory {


    List<SkuSpecificationGroupModel> groups;



    public List<SkuSpecificationGroupModel> getGroups() {
        return groups;
    }

    public void setGroups(List<SkuSpecificationGroupModel> groups) {
        this.groups = groups;
    }
}
