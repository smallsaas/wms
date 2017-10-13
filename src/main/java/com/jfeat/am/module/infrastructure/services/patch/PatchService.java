package com.jfeat.am.module.infrastructure.services.patch;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Silent-Y on 2017/9/14.
 */
public interface PatchService {

    public List<Map<String,String>> findDepartmentWithStaff(Page<Map<String,String>> page,Long id);


}

