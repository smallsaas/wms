package com.jfeat.am.module.systemconfig.services.crud.service;
            
import com.jfeat.am.module.systemconfig.services.persistence.model.SystemConfig;

import com.jfeat.am.common.crud.CRUDServiceOnly;


/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-02-22
 */

public interface SystemConfigService  extends CRUDServiceOnly<SystemConfig> {

    SystemConfig getSystemConfigByKey(String key);

}
