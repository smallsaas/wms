package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.LoginLog;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.LoginLogMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.LoginLogService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-09
 */
@Deprecated
@Service
public class LoginLogServiceImpl  implements LoginLogService {


    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    protected BaseMapper<LoginLog> getMasterMapper() {
        return loginLogMapper;
    }
}


