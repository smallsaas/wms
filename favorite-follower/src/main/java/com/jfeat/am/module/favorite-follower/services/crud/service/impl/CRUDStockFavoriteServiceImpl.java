package com.jfeat.am.module.product.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.module.product.services.persistence.model.StockFavorite;
import com.jfeat.am.module.product.services.persistence.dao.StockFavoriteMapper;


import com.jfeat.am.module.product.services.crud.service.CRUDStockFavoriteService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStockFavoriteService
 * @author Code Generator
 * @since 2018-07-16
 */

@Service
public class CRUDStockFavoriteServiceImpl  extends CRUDServiceOnlyImpl<StockFavorite> implements CRUDStockFavoriteService {





        @Resource
        private StockFavoriteMapper stockFavoriteMapper;

        @Override
        protected BaseMapper<StockFavorite> getMasterMapper() {
                return stockFavoriteMapper;
        }







}


