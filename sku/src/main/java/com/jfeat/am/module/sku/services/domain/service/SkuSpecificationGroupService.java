package com.jfeat.am.module.sku.services.domain.service;

import com.jfeat.am.module.sku.services.crud.model.SkuSpecificationGroupModel;
import com.jfeat.am.module.sku.services.crud.service.CRUDSkuSpecificationGroupService;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.module.sku.services.domain.model.CategorySpecModel;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface SkuSpecificationGroupService extends CRUDSkuSpecificationGroupService {
        Integer bulkDelete(Ids ids);


        // 创建 某个 列表 节点 下的 多个 规格
        Integer specChildren(CategorySpecModel entity);


        // 更新 某个 列表 节点 下的 多个 规格

        Integer updateSpecChildren(Long specId, SkuSpecificationGroupModel entity);

        // 获取 某个 规格 父节点 下的 多个 子规格

        SkuSpecificationGroupModel getSpecChildren(Long specId);


        /**
         *  所有的 规格 包括 所有的子规格
         * */

        List<SkuSpecificationGroupModel> allSpec();
}