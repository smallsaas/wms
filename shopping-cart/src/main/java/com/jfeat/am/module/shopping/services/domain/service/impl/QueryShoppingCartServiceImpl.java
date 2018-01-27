package com.jfeat.am.module.shopping.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.shopping.services.domain.dao.QueryShoppingCartDao;
import com.jfeat.am.module.shopping.services.domain.service.QueryShoppingCartService;
import com.jfeat.am.module.shopping.services.persistence.model.ShoppingCart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class QueryShoppingCartServiceImpl implements QueryShoppingCartService {

    @Resource
    QueryShoppingCartDao queryShoppingCartDao;

    @Override
    public List<ShoppingCart> findShoppingCartPage(Page<ShoppingCart> page, ShoppingCart shoppingcart,String orderBy) {
        return queryShoppingCartDao.findShoppingCartPage(page, shoppingcart, orderBy);
    }
}
