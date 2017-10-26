package com.jfeat.am.module.organization.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.CRUD;
import com.jfeat.am.common.crud.CRUDFilter;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.common.crud.impl.CRUDServiceModelImpl;
import com.jfeat.am.module.organization.services.crud.service.PositionChildService;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.domain.model.StaffModel;
import com.jfeat.am.module.organization.services.persistence.dao.StaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.Position;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
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
public class StaffServiceImpl extends CRUDServiceModelImpl<Staff, StaffModel>
        implements StaffService {

    @Resource
    private StaffMapper staffMapper;

    @Resource
    private PositionChildService positionChildService;

    protected BaseMapper<Staff> getMasterMapper() {
        return staffMapper;
    }

    @Override
    public List<Staff> getStaffsOfDepartment(Long departmentId) {
        return selectMasterList(Staff.DEPT_ID, departmentId);
    }

    @Override
    public Integer createMasterModel(StaffModel staffModel, CRUDFilter<Staff> crudFilter) {
        Integer affected = 0;

        /// create child
        Position staffPos = staffModel.getPosition();
        if(staffPos!=null){
            affected += positionChildService.updateChild(staffModel.getId(), staffPos);
        }

        /// create slave

        /// create master
        affected +=  super.createMaster(staffModel, crudFilter);

        return affected;
    }

    @Override
    @Transactional
    public Integer updateMasterModel(StaffModel staffModel, CRUDFilter<Staff> crudFilter) {
        Integer affected = 0;

        /// update child
        Position staffPos = staffModel.getPosition();
        if(staffPos!=null){
            affected += positionChildService.updateChild(staffModel.getId(), staffPos);
        }

        /// update slave


        /// update master
        affected +=  super.updateMaster(staffModel, crudFilter);

        return affected;
    }

    @Override
    public CRUDObject<StaffModel> retrieveMasterModel(long masterId, CRUDFilter<Staff> crudFilter) {
        Staff staff = super.retrieveMaster(masterId);
        StaffModel staffModel = CRUD.castObject(staff, StaffModel.class);

        /// append position child
        Position position = positionChildService.getChild(masterId);
        staffModel.setPosition(position);

        /// append slaves


        return new CRUDObject<StaffModel>().from(staffModel).ignore(crudFilter.ignore(true));
    }

    @Override
    @Transactional
    public Integer deleteMasterModel(long masterId) {
        Integer affected = 0;

        /// delete child
        affected += positionChildService.deleteChild(masterId);

        /// delete master
        affected += getMasterMapper().deleteById(masterId);

        return affected;
    }
}


