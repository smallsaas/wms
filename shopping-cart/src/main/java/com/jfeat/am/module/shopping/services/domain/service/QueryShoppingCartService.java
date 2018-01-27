package com.jfeat.am.module.shopping.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.shopping.services.persistence.model.ShoppingCart;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryShoppingCartService {
    List<ShoppingCart> findShoppingCartPage(Page<ShoppingCart> page, ShoppingCart shoppingcart ,String orderBy );
}