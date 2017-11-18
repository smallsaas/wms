package com.jfeat.am.module.organization.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.CRUD;
import com.jfeat.am.common.crud.CRUDFilter;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.organization.services.crud.service.DepartmentChildService;
import com.jfeat.am.module.organization.services.crud.service.PositionChildService;
import com.jfeat.am.module.organization.services.crud.service.ProfileChildService;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.domain.model.StaffModel;
import com.jfeat.am.module.organization.services.persistence.dao.StaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import com.jfeat.am.module.organization.services.persistence.model.Position;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.profile.services.crud.service.ProfileService;
import com.jfeat.am.module.profile.services.persistence.model.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class StaffServiceImpl extends CRUDServiceOnlyImpl<Staff>
        implements StaffService {

    @Resource
    private StaffMapper staffMapper;

    protected BaseMapper<Staff> getMasterMapper() {
        return staffMapper;
    }

    @Resource
    private PositionChildService positionChildService;

    @Resource
    private ProfileChildService profileChildService;

    @Resource
    private DepartmentChildService departmentChildService;


    @Override
    public List<Staff> getStaffsOfDepartment(Long departmentId) {
        return selectMasterList(Staff.DEPT_ID, departmentId);
    }

    @Override
    public Staff getStaffByUserId(Long userId) {
        Staff entity = new Staff();
        entity.setUserId(userId);
        return staffMapper.selectOne(entity);
    }

    @Override
    @Transactional
    public Integer createModel(StaffModel staffModel, CRUDFilter<Staff> crudFilter) {
        Integer affected = 0;

        /// create master
        Staff staff = CRUD.castObject(staffModel);
        affected +=  super.createMaster(staff, crudFilter);

        /// create child
        /*Position staffPos = staffModel.getPosition();
        if(staffPos!=null){
            affected += positionChildService.updateChild(staffModel.getId(), staffPos);
        }*/

        Profile profile = staffModel.getProfile();
        if(profile!=null){
            affected += profileChildService.updateChild(staff.getId(), profile);
            staff.setProfileId(profile.getId());
            super.updateMaster(staff);

        }

        /// create slave

        /*/// create master
        Staff staff = CRUD.castObject(staffModel);
        affected +=  super.createMaster(staff, crudFilter);*/

        return affected;
    }

    @Override
    @Transactional
    public Integer updateModel(StaffModel staffModel, CRUDFilter<Staff> crudFilter) {
        Integer affected = 0;

        /// update child
        Position staffPos = staffModel.getPosition();
        if(staffPos!=null){
            affected += positionChildService.updateChild(staffModel.getId(), staffPos);
        }

        /// update slave
        Profile profile = staffModel.getProfile();
        if(profile!=null){
            affected += profileChildService.updateChild(staffModel.getId(), profile);
        }


        /// update master
        affected +=  super.updateMaster(staffModel, crudFilter);

        return affected;
    }

    @Override
    public CRUDObject<StaffModel> retrieveModel(long masterId, CRUDFilter<Staff> crudFilter) {
        Staff staff = super.retrieveMaster(masterId);
        StaffModel staffModel = CRUD.castObject(staff, StaffModel.class);

        /// append position child
        Position position = positionChildService.getChild(masterId);
        staffModel.setPosition(position);

        Profile profile = profileChildService.getChild(masterId);
        staffModel.setProfile(profile);

        Department department = departmentChildService.getChild(masterId);
        staffModel.setDepartment(department);

        /// append slaves


        return new CRUDObject<StaffModel>().from(staffModel).ignore(crudFilter.ignore(true));
    }


    @Override
    @Transactional
    public Integer deleteModel(long masterId) {
        Integer affected = 0;

        /// delete child

        affected += profileChildService.deleteChild(masterId);


        /// delete master
        affected += getMasterMapper().deleteById(masterId);

        return affected;
    }
}


