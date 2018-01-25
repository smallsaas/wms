package com.jfeat.am.module.state.services.crud.service;
            
import com.jfeat.am.module.state.services.persistence.model.Country;

import com.jfeat.am.common.crud.CRUDServiceOnly;

import java.util.List;


/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-01-25
 */

public interface CountryService  extends CRUDServiceOnly<Country> {
    List<Country> retrieveMasterList(String lang);
}
