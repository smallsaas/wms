package com.jfeat.am.module.organization.services.service.patch;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by kang on 2018/4/24.
 */
public interface PatchDepartmentService {

    public JSONArray putDatas(JSONArray jsonArray);

    //检查检查上级部门是否存在循环链，即上级部门一直找上去，不能出现自己 （仅在更新部门时需要检查）
    public boolean hasPidChain(Long id, Long pid);
}
