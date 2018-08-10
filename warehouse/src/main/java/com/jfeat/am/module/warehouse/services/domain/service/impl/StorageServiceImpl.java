package com.jfeat.am.module.warehouse.services.domain.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryStorageInDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryStorageOutDao;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInRecord;
import com.jfeat.am.module.warehouse.services.domain.model.StorageModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutRecord;
import com.jfeat.am.module.warehouse.services.domain.service.StorageService;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageIn;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOut;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    StorageOutMapper outMapper;
    @Resource
    StorageInMapper inMapper;
    @Resource
    QueryStorageOutDao queryStorageOutDao;
    @Resource
    QueryStorageInDao queryStorageInDao;


    /**
    *  出入 库 汇总
    * */
    @Deprecated
    public StorageModel totalStorage(){
        StorageModel model = new StorageModel();
        List<StorageOut> outs = outMapper.selectList(new EntityWrapper<StorageOut>().orderBy("transaction_time",false));
        List<StorageIn> ins = inMapper.selectList(new EntityWrapper<StorageIn>().orderBy("transaction_time",false));
        List<StorageOutRecord> outRecords = new ArrayList<>();
        List<StorageInRecord>  inRecords = new ArrayList<>();
        for (StorageOut out : outs){
            StorageOutRecord outRecord  =  queryStorageOutDao.storagesOutDetails(out.getId());
            outRecords.add(outRecord);
        }
        for (StorageIn in : ins){
            StorageInRecord record = queryStorageInDao.storagesInDetails(in.getId());
            inRecords.add(record);
        }
        model.setInRecords(inRecords);
        model.setOutRecords(outRecords);
        return model;
    }
}
