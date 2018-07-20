package com.jfeat.am.module.sku.services.crud.model;

import com.jfeat.am.module.sku.services.persistence.model.SkuSpecificationGroup;

import java.util.List;

/**
 * Created by Code Generator on 2018-07-18
 */
public class SkuSpecificationGroupModel extends SkuSpecificationGroup {

    List<SkuSpecificationGroup> children;


    public List<SkuSpecificationGroup> getChildren() {
        return children;
    }

    public void setChildren(List<SkuSpecificationGroup> children) {
        this.children = children;
    }
}
