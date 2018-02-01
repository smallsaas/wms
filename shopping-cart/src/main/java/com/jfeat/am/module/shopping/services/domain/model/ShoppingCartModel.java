package com.jfeat.am.module.shopping.services.domain.model;
import com.jfeat.am.module.shopping.services.persistence.model.ShoppingCart;
/**
 * Created by Code Generator on 2018-01-27
 */
public class ShoppingCartModel{
    Long proId;
    Integer quantity;

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
