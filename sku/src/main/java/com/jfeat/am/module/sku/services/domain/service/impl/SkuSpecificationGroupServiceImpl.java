package com.jfeat.am.module.sku.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.sku.services.crud.model.SkuSpecificationGroupModel;
import com.jfeat.am.module.sku.services.domain.model.CategorySpecModel;
import com.jfeat.am.module.sku.services.domain.service.SkuSpecificationGroupService;

import com.jfeat.am.module.sku.services.crud.service.impl.CRUDSkuSkuSpecificationGroupServiceImpl;
import com.jfeat.am.module.sku.services.persistence.model.SkuSpecificationGroup;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.constant.tips.Ids;

import javax.annotation.Resource;

import com.jfeat.am.module.sku.services.persistence.dao.SkuSpecificationGroupMapper;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("SkuSpecificationGroupService")
public class SkuSpecificationGroupServiceImpl extends CRUDSkuSkuSpecificationGroupServiceImpl implements SkuSpecificationGroupService {
    @Resource
    private SkuSpecificationGroupMapper skuSpecificationGroupMapper;


    // 创建 某个 列表 节点 下的 多个 规格

    public Integer specChildren(CategorySpecModel entity) {
        int affect = 0;
        for (SkuSpecificationGroupModel group : entity.getGroups()) {
            group.setPid(entity.getCategoryId());
            group.setType("Category");
            SkuSpecificationGroup isExist = skuSpecificationGroupMapper.selectOne(group);
            if (isExist == null) {
                // 父级 为空
                affect += skuSpecificationGroupMapper.insert(group);
                if (group.getChildren() != null || group.getChildren().size() > 0) {
                    for (SkuSpecificationGroup child : group.getChildren()) {
                        // 插入 父节点
                        child.setType("Spec");
                        child.setPid(group.getId());
                        affect += skuSpecificationGroupMapper.insert(child);
                    }
                } else {

                }

            } else {
                if (group.getChildren() != null || group.getChildren().size() > 0) {
                    for (SkuSpecificationGroup child : group.getChildren()) {
                        // 子节点  不为空
                        child.setPid(isExist.getId());
                        child.setType("Spec");
                        affect += skuSpecificationGroupMapper.insert(child);
                    }
                } else {

                }
            }
        }
        return affect;
    }


    // 更新 某个 列表 节点 下的 多个 规格

    public Integer updateSpecChildren(Long specId, SkuSpecificationGroupModel entity) {

        int affect = 0;
        if (entity.getChildren() != null || entity.getChildren().size() > 0) {
            for (SkuSpecificationGroup child : entity.getChildren()) {
                // 子节点  不为空
                child.setPid(specId);
                child.setType("Spec");
                affect += skuSpecificationGroupMapper.insert(child);
            }
        } else {

        }
        return affect;
    }

    // 获取 某个 规格 父节点 下的 多个 子规格

    public SkuSpecificationGroupModel getSpecChildren(Long specId) {

        SkuSpecificationGroup group = skuSpecificationGroupMapper.selectById(specId);

        List<SkuSpecificationGroup> children = skuSpecificationGroupMapper.selectList(new EntityWrapper<SkuSpecificationGroup>().eq("pid",specId).like("type","Spec"));
        JSONObject object = JSON.parseObject(JSON.toJSONString(group));

        object.put("children",children);

        SkuSpecificationGroupModel model = JSONObject.parseObject(JSON.toJSONString(object),SkuSpecificationGroupModel.class);
        return model;

    }


    @Override
    public Integer bulkDelete(Ids ids) {
        Integer success = 0;
        for (Long id : ids.getIds()) {
            success += skuSpecificationGroupMapper.deleteById(id);
        }
        return success;
    }
}
