package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Refund;
import com.jfeat.am.module.warehouse.services.persistence.dao.RefundMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDRefundService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRefundService
 * @author Code Generator
 * @since 2018-06-22
 */

@Service
public class CRUDRefundServiceImpl  extends CRUDServiceOnlyImpl<Refund> implements CRUDRefundService {





        @Resource
        private RefundMapper refundMapper;

        @Override
        protected BaseMapper<Refund> getMasterMapper() {
                return refundMapper;
        }







}


