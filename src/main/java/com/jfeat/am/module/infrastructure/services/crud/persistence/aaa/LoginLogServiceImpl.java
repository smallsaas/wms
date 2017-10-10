package com.jfeat.am.module.infrastructure.services.crud.persistence.aaa;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.CRUDFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */
@Deprecated
@Service
public class LoginLogServiceImpl  implements LoginLogService {


    @Resource
    private LoginLogMapper loginLogMapper;

    protected BaseMapper<LoginLog> getMasterMapper() {
        return loginLogMapper;
    }

    @Override
    public Integer createMaster(Object o) {
        return null;
    }

    @Override
    public Integer createMaster(Object o, CRUDFilter crudFilter) {
        return null;
    }

    @Override
    public Integer updateMaster(Object o) {
        return null;
    }

    @Override
    public Integer updateMaster(Object o, CRUDFilter crudFilter) {
        return null;
    }

    @Override
    public Object retrieveMaster(long l) {
        return null;
    }

    @Override
    public Integer deleteMaster(long l) {
        return null;
    }

    @Override
    public List queryMasterList(Map map) {
        return null;
    }

    @Override
    public List selectMasterList(String s, Object o) {
        return null;
    }

    @Override
    public Integer bulkDeleteMasterList(List list) {
        return null;
    }
}


