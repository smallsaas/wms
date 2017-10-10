package com.jfeat.am.module.infrastructure.services.patch.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.OperationLogMapper;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jfeat.am.module.infrastructure.services.patch.PatchService;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Silent-Y on 2017/9/14.
 */
@Service
public class PatchServiceImpl implements PatchService {

    @Resource
    private OperationLogMapper operationLogMapper;

    public List<OperationLog> findOperationLogs(){
        return operationLogMapper.selectList(new EntityWrapper<>());
    }

}
