package com.jfeat.am.module.sku.services.domain.service;

import com.jfeat.am.module.sku.services.crud.model.SkuSpecificationGroupModel;
import com.jfeat.am.module.sku.services.crud.service.CRUDSkuSpecificationGroupService;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.module.sku.services.domain.model.CategorySpecModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface SkuSpecificationGroupService extends CRUDSkuSpecificationGroupService {
    Integer bulkDelete(Ids ids);


    // 创建 某个 列表 节点 下的 多个 规格
    @Transactional
    Integer specChildren(CategorySpecModel entity);



    /**
     * 更新 某个 列表 节点 下的 多个 规格
     */
    Integer updateSpecChildren(Long categoryId, CategorySpecModel entity);

    /**
     *     获取 某个类别下 所有的规格
     */
    List<SkuSpecificationGroupModel> getSpecChildren(Long specId);


    /**
     * 所有的 规格 包括 所有的子规格
     */
    List<SkuSpecificationGroupModel> allSpec();


    /**
     * 删除 类别  及类别下的所有的所有的规格
     */
    Integer deleteCategory(Long categoryId);

}