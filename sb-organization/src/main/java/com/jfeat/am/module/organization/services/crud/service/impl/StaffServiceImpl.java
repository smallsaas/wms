package com.jfeat.am.module.organization.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.organization.services.persistence.dao.StaffMapper;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-13
 */
@Service
public class StaffServiceImpl extends CRUDServiceOnlyImpl implements StaffService {

    @Resource
    private StaffMapper staffMapper;

    protected BaseMapper<Staff> getMasterMapper() {
        return staffMapper;
    }

    @Override
    public List<Staff> getStaffsOfDepartment(Long departmentId) {
        return selectMasterList(Staff.DEPT_ID, departmentId);
    }
}


