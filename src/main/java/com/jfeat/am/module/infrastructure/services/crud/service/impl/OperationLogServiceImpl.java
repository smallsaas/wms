package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.OperationLogMapper;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;
import com.jfeat.am.module.infrastructure.services.crud.service.OperationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */
@Service
public class OperationLogServiceImpl  extends CRUDServiceOnlyImpl implements OperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    protected BaseMapper<OperationLog> getMasterMapper() {
        return operationLogMapper;
    }

}


