package com.jfeat.am.module.sku.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.module.sku.services.persistence.model.SkuPriceHistory;
import com.jfeat.am.module.sku.services.persistence.dao.SkuPriceHistoryMapper;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuPriceHistoryService;
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
 *CRUDSkuPriceHistoryService
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuPriceHistoryServiceImpl  extends CRUDServiceOnlyImpl<SkuPriceHistory> implements CRUDSkuPriceHistoryService {





        @Resource
        private SkuPriceHistoryMapper skuPriceHistoryMapper;

        @Override
        protected BaseMapper<SkuPriceHistory> getMasterMapper() {
                return skuPriceHistoryMapper;
        }







}


