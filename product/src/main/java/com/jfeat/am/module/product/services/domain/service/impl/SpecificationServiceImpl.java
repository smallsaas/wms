package com.jfeat.am.module.product.services.domain.service.impl;

import com.jfeat.am.module.product.services.domain.service.SpecificationService;

import com.jfeat.am.module.product.services.crud.service.impl.CRUDSpecificationServiceImpl;
import com.jfeat.am.module.product.services.persistence.dao.SpecificationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("SpecificationService")
public class SpecificationServiceImpl extends CRUDSpecificationServiceImpl implements SpecificationService {


    @Resource
    SpecificationMapper specificationMapper;



}
