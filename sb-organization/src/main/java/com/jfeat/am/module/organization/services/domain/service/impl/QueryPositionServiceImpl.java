package com.jfeat.am.module.organization.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.domain.dao.QueryPositionDao;
import com.jfeat.am.module.organization.services.domain.service.QueryPositionService;
import com.jfeat.am.module.organization.services.persistence.model.Position;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class QueryPositionServiceImpl implements QueryPositionService {

    @Resource
    QueryPositionDao queryPositionDao;

    @Override
    public List<Position> findPositionPage(Page<Position> page, Position position) {
        return queryPositionDao.findPositionPage(page, position);
    }
}
