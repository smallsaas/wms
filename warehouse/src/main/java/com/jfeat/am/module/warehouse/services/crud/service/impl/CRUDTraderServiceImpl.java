package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Trader;
import com.jfeat.am.module.warehouse.services.persistence.dao.TraderMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDTraderService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDTraderService
 * @author Code Generator
 * @since 2018-11-14
 */

@Service
public class CRUDTraderServiceImpl  extends CRUDServiceOnlyImpl<Trader> implements CRUDTraderService {

        @Resource
        private TraderMapper traderMapper;

        @Override
        protected BaseMapper<Trader> getMasterMapper() {
                return traderMapper;
        }

}


