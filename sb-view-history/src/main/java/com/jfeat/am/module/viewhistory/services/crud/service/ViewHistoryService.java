package com.jfeat.am.module.viewhistory.services.crud.service;
            
import com.jfeat.am.module.viewhistory.services.persistence.model.ViewHistory;

import com.jfeat.am.common.crud.CRUDServiceOnly;

import java.util.List;


/**
 * <p>
 * 浏览记录表 service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-01-23
 */

public interface ViewHistoryService  extends CRUDServiceOnly<ViewHistory> {


    List<ViewHistory> viewHistories(long userId);
}
