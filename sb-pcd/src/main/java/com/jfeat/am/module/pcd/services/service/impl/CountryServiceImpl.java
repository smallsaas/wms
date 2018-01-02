package com.jfeat.am.module.pcd.services.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.pcd.services.persistence.model.Country;
import com.jfeat.am.module.pcd.services.persistence.dao.CountryMapper;
import com.jfeat.am.module.pcd.services.service.CountryService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-22
 */
@Service
public class CountryServiceImpl  extends CRUDServiceOnlyImpl<Country> implements CountryService {


    @Resource
    private CountryMapper countryMapper;

    @Override
    protected BaseMapper<Country> getMasterMapper() {
        return countryMapper;
    }
}


