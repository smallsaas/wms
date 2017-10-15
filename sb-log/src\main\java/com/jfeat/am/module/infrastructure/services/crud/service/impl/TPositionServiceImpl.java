package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.TPosition;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.TPositionMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.TPositionService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-15
 */
@Deprecated
@Service
public class TPositionServiceImpl  implements TPositionService {


    @Resource
    private TPositionMapper tPositionMapper;

    @Override
    protected BaseMapper<TPosition> getMasterMapper() {
        return tPositionMapper;
    }
}


