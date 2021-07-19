package com.jfeat.am.module.product.services.crud.service.impl;
            
import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.module.product.services.persistence.model.SpecificationGroup;
import com.jfeat.am.module.product.services.persistence.dao.SpecificationGroupMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.product.services.persistence.model.Specification;
import com.jfeat.am.module.product.services.persistence.dao.SpecificationMapper;


import com.jfeat.am.module.product.services.crud.service.CRUDSpecificationService;
import com.jfeat.crud.plus.impl.CRUDServiceGroupByImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDSpecificationService
 * @author Code Generator
 * @since 2018-06-19
 */

@Service
public class CRUDSpecificationServiceImpl  extends CRUDServiceGroupByImpl<SpecificationGroup,Specification> implements CRUDSpecificationService {



        @Resource
        private SpecificationMapper groupByMapper;

        @Resource
        private SpecificationGroupMapper groupMapper;

        @Override
        protected BaseMapper<SpecificationGroup> getGroupMapper() {
                return groupMapper;
        }

        @Override
        protected BaseMapper<Specification> getGroupByMapper() {
                return groupByMapper;
        }

        @Override
        protected String groupByFieldName() {
                throw new RuntimeException("Please specifiy the field name here .");
        }

}


