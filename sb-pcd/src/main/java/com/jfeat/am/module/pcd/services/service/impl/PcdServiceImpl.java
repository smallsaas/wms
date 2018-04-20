package com.jfeat.am.module.pcd.services.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.pcd.services.persistence.model.Pcd;
import com.jfeat.am.module.pcd.services.persistence.dao.PcdMapper;
import com.jfeat.am.module.pcd.services.service.PcdService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;

import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-22
 */
@Service
public class PcdServiceImpl  extends CRUDServiceOnlyImpl<Pcd> implements PcdService {


    @Resource
    private PcdMapper pcdMapper;

    @Override
    protected BaseMapper<Pcd> getMasterMapper() {
        return pcdMapper;
    }


}


