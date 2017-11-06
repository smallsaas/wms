package com.jfeat.am.module.statement.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.statement.services.domain.dao.QueryStatementDao;
import com.jfeat.am.module.statement.services.domain.service.QueryStatementService;
import com.jfeat.am.module.statement.services.persistence.model.Statement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class QueryStatementServiceImpl implements QueryStatementService {

    @Resource
    QueryStatementDao queryStatementDao;

    @Override
    public List<Statement> findStatementPage(Page<Statement> page, Statement statement) {
        return queryStatementDao.findStatementPage(page, statement);
    }
}
