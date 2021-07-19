package com.jfeat.am.module.sku.services.crud.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuTag;
import com.jfeat.am.module.sku.services.persistence.dao.SkuTagMapper;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuTagService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * implementation
 * </p>
 * CRUDSkuTagService
 *
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuTagServiceImpl extends CRUDServiceOnlyImpl<SkuTag> implements CRUDSkuTagService {


    @Resource
    private SkuTagMapper skuTagMapper;

    @Override
    protected BaseMapper<SkuTag> getMasterMapper() {
        return skuTagMapper;
    }


}


