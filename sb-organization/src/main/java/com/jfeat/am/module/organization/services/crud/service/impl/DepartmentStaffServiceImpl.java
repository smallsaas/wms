package com.jfeat.am.module.organization.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.organization.constant.IsManager;
import com.jfeat.am.module.organization.services.crud.service.DepartmentStaffService;
import com.jfeat.am.module.organization.services.persistence.mapper.DepartmentStaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.DepartmentStaff;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Silent-Y on 2018/1/17.
 */
@Service
public class DepartmentStaffServiceImpl implements DepartmentStaffService {

    @Resource
    DepartmentStaffMapper departmentStaffMapper;

    @Override
    public Integer save(DepartmentStaff departmentStaff) {
        return departmentStaffMapper.insert(departmentStaff);
    }

    @Override
    public Integer delete(Long id) {
        return departmentStaffMapper.deleteById(id);
    }

    @Override
    public DepartmentStaff get(DepartmentStaff departmentStaff) {
        return departmentStaffMapper.selectOne(departmentStaff);
    }

    @Override
    public Integer update(DepartmentStaff departmentStaff) {
        return departmentStaffMapper.updateById(departmentStaff);
    }

    @Override
    public List<DepartmentStaff> selectList(DepartmentStaff departmentStaff) {
        return departmentStaffMapper.selectList(new EntityWrapper<DepartmentStaff>().eq(DepartmentStaff.DEPARTMENT_ID,departmentStaff.getDepartmentId()).eq(DepartmentStaff.IS_MANAGER, departmentStaff.getIsManager()));
    }

    @Override
    public Integer deleteList(DepartmentStaff departmentStaff) {
        return departmentStaffMapper.delete(new EntityWrapper<DepartmentStaff>().eq(DepartmentStaff.DEPARTMENT_ID,departmentStaff.getDepartmentId()).eq(DepartmentStaff.IS_MANAGER,departmentStaff.getIsManager()));
    }
}
