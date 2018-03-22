package com.jfeat.am.module.organization.kit;

import com.jfeat.am.module.organization.services.domain.service.QueryDepartmentService;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class OrganizationKit {

    @Resource
    QueryDepartmentService queryDepartmentService;

    public Boolean checkDepartmentCodeDuplicate(String code) {
        List<Department> departments = queryDepartmentService.queryDepartmentByCode(code);
        return departments.size() > 0;
    }
}
