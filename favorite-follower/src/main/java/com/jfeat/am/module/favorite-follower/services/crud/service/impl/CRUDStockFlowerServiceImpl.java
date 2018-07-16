package com.jfeat.am.module.product.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.module.product.services.persistence.model.StockFlower;
import com.jfeat.am.module.product.services.persistence.dao.StockFlowerMapper;


import com.jfeat.am.module.product.services.crud.service.CRUDStockFlowerService;
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
 *CRUDStockFlowerService
 * @author Code Generator
 * @since 2018-07-16
 */

@Service
public class CRUDStockFlowerServiceImpl  extends CRUDServiceOnlyImpl<StockFlower> implements CRUDStockFlowerService {





        @Resource
        private StockFlowerMapper stockFlowerMapper;

        @Override
        protected BaseMapper<StockFlower> getMasterMapper() {
                return stockFlowerMapper;
        }







}


