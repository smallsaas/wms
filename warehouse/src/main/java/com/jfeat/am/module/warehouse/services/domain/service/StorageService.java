package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.domain.model.StorageModel;

public interface StorageService {

    /**
     *  出入 库 汇总
     * */
    StorageModel totalStorage();
}
