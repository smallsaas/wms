package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Check;
import com.jfeat.am.module.warehouse.services.persistence.dao.CheckMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDCheckService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDCheckService
 * @author Code Generator
 * @since 2018-06-23
 */

@Service
public class CRUDCheckServiceImpl  extends CRUDServiceOnlyImpl<Check> implements CRUDCheckService {





        @Resource
        private CheckMapper checkMapper;

        @Override
        protected BaseMapper<Check> getMasterMapper() {
                return checkMapper;
        }







}


