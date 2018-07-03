package com.jfeat.am.module.viewhistory.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.persistence.model.User;
import com.jfeat.am.module.viewhistory.services.persistence.model.ViewHistory;
import com.jfeat.am.module.viewhistory.services.persistence.dao.ViewHistoryMapper;
import com.jfeat.am.module.viewhistory.services.crud.service.ViewHistoryService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;

import java.util.List;

/**
 * <p>
 * 浏览记录表 implementation
 * </p>
 *
 * @author Code Generator
 * @since 2018-01-23
 */
@Service
public class ViewHistoryServiceImpl  extends CRUDServiceOnlyImpl<ViewHistory> implements ViewHistoryService {


    @Resource
    private ViewHistoryMapper viewHistoryMapper;

    @Override
    protected BaseMapper<ViewHistory> getMasterMapper() {
        return viewHistoryMapper;
    }


    public List<ViewHistory> viewHistories(long userId){
        return viewHistoryMapper.selectList(new EntityWrapper<ViewHistory>().eq("member_id",userId).orderBy("viewTime",false));
    }
}


