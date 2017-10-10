package com.jfeat.am.module.infrastructure.services.patch;

import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;

import java.util.List;

/**
 * Created by Silent-Y on 2017/9/14.
 */
public interface PatchService {

    public List<OperationLog> findOperationLogs();

}

