package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDCheckService;
import com.jfeat.am.module.warehouse.services.domain.model.CheckModel;
import com.jfeat.am.module.warehouse.services.domain.model.CheckRecord;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface CheckService extends CRUDCheckService{

    /**
     * 新建盘点单
     * */
    @Transactional
    Integer createCheckList(Long userId, CheckModel model);

    /**
     * 开始盘点
     * */
    @Transactional
    Integer actionCheck(Long checkId,CheckModel model);

    /**
     * finish check modified data
     * */
    @Transactional
    Integer checkedCheck(Long checkId,CheckModel model);


    CheckRecord checkDetails(Long checkId);


    @Transactional
    Integer deleteCheck(Long checkId);
}