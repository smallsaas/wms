package com.jfeat.am.module.organization.services.service.patch.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.CRUD;
import com.jfeat.am.common.persistence.model.User;
import com.jfeat.am.modular.system.service.UserService;
import com.jfeat.am.module.organization.constant.IsManager;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.persistence.mapper.DepartmentMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.DepartmentStaffMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.PositionMapper;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import com.jfeat.am.module.organization.services.persistence.model.DepartmentStaff;
import com.jfeat.am.module.organization.services.persistence.model.Position;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.organization.services.service.patch.PatchDepartmentService;
import com.jfeat.am.module.profile.services.persistence.dao.ProfileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kang on 2018/4/24.
 */
@Service
public class PatchDepartmentServiceImpl implements PatchDepartmentService {

    @Resource
    StaffService staffService;
    @Resource
    DepartmentStaffMapper departmentStaffMapper;
    @Resource
    ProfileMapper profileMapper;
    @Resource
    PositionMapper positionMapper;
    @Resource
    UserService userService;
    @Resource
    DepartmentMapper departmentMapper;

    @Override
    public JSONArray putDatas(JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.size() <= 0) {
            return jsonArray;
        }
        JSONArray result = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject newJsonObject = new JSONObject();
            CRUD.copyFrom(newJsonObject, jsonObject, true);
            putDatas(newJsonObject);
            result.add(newJsonObject);
        }
        return result;
    }

    /**
     * jsonObject：
     * {
     * id,
     * name,
     * code,
     * items: [{},{}]
     * }
     */
    public void putDatas(JSONObject jsonObject) {
        List<DepartmentStaff> departmentStaffs = departmentStaffMapper.selectList(
                new EntityWrapper<DepartmentStaff>()
                        .eq(DepartmentStaff.DEPARTMENT_ID, jsonObject.getLong("id"))
                        .and().eq(DepartmentStaff.IS_MANAGER, IsManager.YES)
        );

        //给该department加上主管信息
        Long userId = null;
        String userName = null;
        Long staffId = null;
        String staffName = null;
        Long positionId = null;
        String positionName = null;

        if (departmentStaffs != null && departmentStaffs.size() > 0) {
            DepartmentStaff departmentStaff = departmentStaffs.get(0);
            Staff staff = staffService.getById(departmentStaff.getStaffId());
            if (staff != null) {
                staffId = staff.getId();
                staffName = staff.getName();
                if (staff.getUserId() != null) {
                    User user = userService.getById(staff.getUserId());
                    userId = user == null ? null : user.getId();
                    userName = user == null ? null : user.getName();
                }
                if (staff.getPositionId() != null) {
                    Position position = positionMapper.selectById(staff.getPositionId());
                    positionId = position == null ? null : position.getId();
                    positionName = position == null ? null : position.getName();
                }
            }
        }
        jsonObject.put("staffId", staffId);
        jsonObject.put("staffName", staffName);
        jsonObject.put("positionId", positionId);
        jsonObject.put("positionName", positionName);
        jsonObject.put("userId", userId);
        jsonObject.put("userName", userName);

        //对该department的items进行同样操作
        JSONArray items = jsonObject.getJSONArray("items");
        if (items == null || items.size() <= 0) {
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            putDatas(items.getJSONObject(i));
        }
    }

    //检查检查上级部门是否存在循环链，即上级部门一直找上去，不能出现自己 （仅在更新部门时需要检查）
    public boolean hasPidChain(Long id, Long pid) {
        //没传id或pid，或所传id在数据库中找不到相应记录，都视为不存在循环链（通过此校验）
        if (id == null || pid == null) {
            return false;
        }
        Department department = departmentMapper.selectById(id);
        if (department == null) {
            return false;
        }

        Long parentId = pid;
        while (parentId != null) {
            Department tempParent = departmentMapper.selectById(parentId);
            if (tempParent == null) {
                return false;
            } else {
                if (tempParent.getId().equals(id)) {
                    return true;
                } else {
                    parentId = tempParent.getPid();
                }
            }
        }
        return false;
    }
}
