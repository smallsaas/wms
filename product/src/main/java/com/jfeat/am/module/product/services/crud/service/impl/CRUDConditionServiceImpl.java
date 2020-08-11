package com.jfeat.am.module.product.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.product.services.persistence.model.Condition;
import com.jfeat.am.module.product.services.persistence.dao.ConditionMapper;


import com.jfeat.am.module.product.services.crud.service.CRUDConditionService;
import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.crud.plus.QueryMasterDao;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import com.jfeat.crud.plus.model.IdVersions;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDConditionService
 * @author Code Generator
 * @since 2018-06-19
 */

@Service
public class CRUDConditionServiceImpl  extends CRUDServiceOnlyImpl<Condition> implements CRUDConditionService {


        @Resource
        private ConditionMapper conditionMapper;

        @Override
        protected BaseMapper<Condition> getMasterMapper() {
                return conditionMapper;
        }
}



