package

        com.jfeat.am.module.infrastructure.api.crud;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.module.infrastructure.constant.StaffPermission;
import com.jfeat.am.module.infrastructure.services.crud.filter.StaffFilter;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Staff;
import com.jfeat.am.module.infrastructure.services.crud.service.StaffService;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.infrastructure.services.crud.service.StaffService;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Staff;

import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */
@RestController
@RequestMapping("/api/infrastructure/infrastructure/staff")
public class StaffEndpoint extends BaseController {


    @Resource
    private StaffService staffService;

    @PostMapping
    public Tip createStaff(@RequestBody Staff entity) {
        return SuccessTip.create(staffService.createMaster(entity));
    }

    @GetMapping("/{id}")
    public Tip getStaff(@PathVariable Long id) {
        return SuccessTip.create(staffService.retrieveMaster(id));
    }


    @PutMapping("/{id}")
    public Tip updateStaff(@PathVariable Long id, @RequestBody Staff entity) {
        return SuccessTip.create(staffService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteStaff(@PathVariable Long id) {
        return SuccessTip.create(staffService.deleteMaster(id));
    }

    /*
        @GetMapping
        //此方法可能需要自行添加需要的参数,按需要使用
        public Tip queryStaffs(Page page,
                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

                 page.setCurrent(pageNum);
                page.setSize(pageSize);
                List<Staff> records = staffService.findStaffs(page);

                page.setRecords(records);

                return SuccessTip.create(page);
            }
            */
    @GetMapping
    @Permission({StaffPermission.Staff_VIEW})
    public Tip show(@RequestHeader("authorization") String token) {
        return null;
    }
}
