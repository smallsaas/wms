package com.jfeat.am.module.product.services.crud.service.impl;
            
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.product.services.persistence.model.SpecificationGroup;
import com.jfeat.am.module.product.services.persistence.dao.SpecificationGroupMapper;


import com.jfeat.am.module.product.services.crud.service.CRUDSpecificationGroupService;
import com.jfeat.crud.plus.impl.CRUDServiceGroupImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDSpecificationGroupService
 * @author Code Generator
 * @since 2018-06-19
 */

@Service
public class CRUDSpecificationGroupServiceImpl  extends CRUDServiceGroupImpl<SpecificationGroup> implements CRUDSpecificationGroupService {


        @Resource
        private SpecificationGroupMapper specificationGroupMapper;

        @Override
        protected BaseMapper<SpecificationGroup> getGroupMapper() {
                return specificationGroupMapper;
        }


}


