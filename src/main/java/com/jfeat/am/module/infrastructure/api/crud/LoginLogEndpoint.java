package

    com.jfeat.am.module.infrastructure.api.crud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

importcom.jfeat.am.module.infrastructure.services.crud.service.LoginLogService;
importcom.jfeat.am.module.infrastructure.services.crud.persistence.model.LoginLog;

        import org.springframework.web.bind.annotation.RestController;
            import com.jfeat.am.common.controller.BaseController;
    
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-10-09
 */
@Deprecated
@RestController
@RequestMapping("/api//infrastructure/login-log")
public class LoginLogEndpoint extends BaseController {


@Resource
private LoginLogService loginLogService;

@PostMapping
public Tip createLoginLog(@RequestBody LoginLog entity){
        return SuccessTip.create(loginLogService.createMaster(entity));
        }

@GetMapping("/{id}")
public Tip getLoginLog(@PathVariable Long id){
        return SuccessTip.create(loginLogService.retrieveMaster(id));
        }


@PutMapping("/{id}")
public Tip updateLoginLog(@PathVariable Long id,@RequestBody LoginLog entity){
        return SuccessTip.create(loginLogService.updateMaster(entity));
        }

@DeleteMapping("/{id}")
public Tip deleteLoginLog(@PathVariable Long id){
        return SuccessTip.create(loginLogService.deleteMaster(id));
        }

/*
    @GetMapping
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryLoginLogs(Page page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

             page.setCurrent(pageNum);
            page.setSize(pageSize);
            List<LoginLog> records = loginLogService.findLoginLogs(page);

            page.setRecords(records);

            return SuccessTip.create(page);
        }
        */
@GetMapping
@Permission({LoginLogPermission.LoginLog_VIEW})
public Tip show(@RequestHeader("authorization")String token){

        }
        }
