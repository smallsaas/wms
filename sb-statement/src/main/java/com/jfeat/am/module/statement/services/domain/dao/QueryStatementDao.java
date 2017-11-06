package com.jfeat.am.module.statement.services.domain.dao;

import com.jfeat.am.module.statement.services.persistence.model.Statement;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-11-06
 */
public interface QueryStatementDao extends BaseMapper<Statement> {
    List<Statement> findStatementPage(Page<Statement> page,Statement statement);
}