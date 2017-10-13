package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.CRUDFilter;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Staff;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.StaffMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.StaffService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
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
 * @since 2017-10-13
 */
@Service
public class StaffServiceImpl  implements StaffService {


    @Resource
    private StaffMapper staffMapper;

    protected BaseMapper<Staff> getMasterMapper() {
        return staffMapper;
    }

    @Override
    public List<Staff> getStaffsOfDepartment(Long departmentId) {
        return null;
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


