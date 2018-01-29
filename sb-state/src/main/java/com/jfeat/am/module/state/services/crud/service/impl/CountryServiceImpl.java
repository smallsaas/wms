package com.jfeat.am.module.state.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.state.services.crud.service.CountryService;
import com.jfeat.am.module.state.services.persistence.dao.CountryMapper;
import com.jfeat.am.module.state.services.persistence.model.Country;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2018-01-25
 */
@Service
public class CountryServiceImpl  extends CRUDServiceOnlyImpl<Country> implements CountryService {

    @Resource
    private CountryMapper countryMapper;

    @Override
    protected BaseMapper<Country> getMasterMapper() {
        return countryMapper;
    }

    @Override
    public List<Country> retrieveMasterList(String lang) {
        return countryMapper.selectList(new EntityWrapper<Country>().eq(Country.LANG, lang));
    }
}


