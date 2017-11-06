package com.jfeat.am.module.statement.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.statement.services.persistence.model.Statement;
import com.jfeat.am.module.statement.services.persistence.dao.StatementMapper;
import com.jfeat.am.module.statement.services.crud.service.StatementService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-06
 */
@Deprecated
@Service
public class StatementServiceImpl  extends CRUDServiceOnlyImpl<Statement> implements StatementService {


    @Resource
    private StatementMapper statementMapper;

    @Override
    protected BaseMapper<Statement> getMasterMapper() {
        return statementMapper;
    }
}


