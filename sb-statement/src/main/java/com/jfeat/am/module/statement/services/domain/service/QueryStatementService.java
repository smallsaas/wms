package com.jfeat.am.module.statement.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.statement.services.persistence.model.Statement;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryStatementService {
    List<Statement> findStatementPage(Page<Statement> page, Statement statement );
}