package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.product.services.persistence.model.Product;
import com.jfeat.am.module.warehouse.services.persistence.model.Suppliers;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public class SuppliersModel extends Suppliers{
    List<Product> supplierProducts;

    public List<Product> getSupplierProducts() {
        return supplierProducts;
    }

    public void setSupplierProducts(List<Product> supplierProducts) {
        this.supplierProducts = supplierProducts;
    }
}
