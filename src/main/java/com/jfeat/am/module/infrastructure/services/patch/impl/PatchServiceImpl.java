package com.jfeat.am.module.infrastructure.services.patch.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.infrastructure.services.domain.dao.QueryDepartmentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jfeat.am.module.infrastructure.services.patch.PatchService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Silent-Y on 2017/9/14.
 */
@Service
public class PatchServiceImpl implements PatchService {

    @Resource
    QueryDepartmentDao queryDepartmentDao;
    @Override
    public List<Map<String,String>> findDepartmentWithStaff(Page<Map<String,String>> page,Long id) {
        return queryDepartmentDao.findDepartmentWithStaff(page,id);
    }
}
