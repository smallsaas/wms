package com.jfeat.am.module.shopping.services.domain.dao;

import com.jfeat.am.module.shopping.services.persistence.model.ShoppingCart;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-01-27
 */
public interface QueryShoppingCartDao extends BaseMapper<ShoppingCart> {
    List<ShoppingCart> findShoppingCartPage(Page<ShoppingCart> page,@Param("shoppingcart") ShoppingCart shoppingcart,@Param("orderBy") String orderBy);
}