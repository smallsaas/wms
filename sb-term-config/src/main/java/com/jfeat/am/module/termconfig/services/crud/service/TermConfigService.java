package com.jfeat.am.module.termconfig.services.crud.service;
            
import com.jfeat.am.module.termconfig.services.persistence.model.TermConfig;

import com.jfeat.am.common.crud.CRUDServiceOnly;


/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-09
 */

public interface TermConfigService  extends CRUDServiceOnly<TermConfig> {

    TermConfig getTermComfigByType(String type);

}
