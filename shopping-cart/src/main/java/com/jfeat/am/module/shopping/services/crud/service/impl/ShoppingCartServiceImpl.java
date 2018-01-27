package com.jfeat.am.module.shopping.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.shopping.services.persistence.model.ShoppingCart;
import com.jfeat.am.module.shopping.services.persistence.dao.ShoppingCartMapper;
import com.jfeat.am.module.shopping.services.crud.service.ShoppingCartService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2018-01-27
 */
@Service
public class ShoppingCartServiceImpl  extends CRUDServiceOnlyImpl<ShoppingCart> implements ShoppingCartService {


    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    protected BaseMapper<ShoppingCart> getMasterMapper() {
        return shoppingCartMapper;
    }


}


