package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.common.persistence.model.User;
import com.jfeat.am.core.shiro.ShiroKit;
import com.jfeat.am.module.warehouse.api.permission.TraderPermission;
import com.jfeat.am.module.warehouse.services.definition.TraderStatus;
import com.jfeat.am.module.warehouse.services.domain.service.TraderService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDTraderServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.model.Trader;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.constant.tips.Ids;

import javax.annotation.Resource;

import com.jfeat.am.module.warehouse.services.persistence.dao.TraderMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class TraderServiceImpl extends CRUDTraderServiceImpl implements TraderService {
    @Resource
    private TraderMapper traderMapper;

    @Override
    public Integer bulkDelete(Ids ids) {
        Integer success = 0;
        for (Long id : ids.getIds()) {
            success += traderMapper.deleteById(id);
        }
        return success;
    }


    /**
     *  1. 修改 供应商 状态
     *  2. 为正常状态的时候，调用次 服务 变为禁止状态
     *  3. 为禁止状态的时候，调用次 服务 变为正常状态
     * */
    @Transactional
    public Integer changeTraderStatus(Long id){
        Trader trader = traderMapper.selectById(id);
        if (ShiroKit.hasPermission(TraderPermission.TRADER_EDIT)){
            if (trader.getTraderStatus().compareTo(TraderStatus.Forbidden.toString())==0){
                trader.setTraderStatus(TraderStatus.Normal.toString());
            };
            trader.setTraderStatus(TraderStatus.Forbidden.toString());
            return traderMapper.updateById(trader);
        }
        throw new BusinessException(BusinessCode.NoPermission);
    }
}
