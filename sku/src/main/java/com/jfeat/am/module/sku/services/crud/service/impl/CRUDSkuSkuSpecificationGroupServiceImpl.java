package com.jfeat.am.module.sku.services.crud.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuSpecificationGroup;
import com.jfeat.am.module.sku.services.persistence.dao.SkuSpecificationGroupMapper;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuSpecificationGroupService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * implementation
 * </p>
 * CRUDSkuSpecificationGroupService
 *
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuSkuSpecificationGroupServiceImpl extends CRUDServiceOnlyImpl<SkuSpecificationGroup> implements CRUDSkuSpecificationGroupService {


    @Resource
    private SkuSpecificationGroupMapper skuSpecificationGroupMapper;

    @Override
    protected BaseMapper<SkuSpecificationGroup> getMasterMapper() {
        return skuSpecificationGroupMapper;
    }


}


