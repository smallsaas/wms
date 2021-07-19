package com.jfeat.am.module.sku.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.am.module.product.services.persistence.dao.ProductCategoryMapper;
import com.jfeat.am.module.sku.services.crud.model.SkuSpecificationGroupModel;
import com.jfeat.am.module.sku.services.crud.service.impl.CRUDSkuSkuSpecificationGroupServiceImpl;
import com.jfeat.am.module.sku.services.domain.model.CategorySpecModel;
import com.jfeat.am.module.sku.services.domain.service.SkuSpecificationGroupService;
import com.jfeat.am.module.sku.services.persistence.dao.SkuSpecificationGroupMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuSpecificationGroup;
import com.jfeat.crud.base.request.Ids;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    ProductCategoryMapper categoryMapper;


    /**
     * 创建 某个 列表 节点 下的 多个 规格
     */
    @Transactional
    public Integer specChildren(CategorySpecModel entity) {

        int affect = 0;
        affect += categoryMapper.insert(entity);

        if (entity.getGroups() != null && entity.getGroups().size() > 0) {
            for (SkuSpecificationGroupModel group : entity.getGroups()) {
                group.setPid(entity.getId());
                group.setType("Category");
                SkuSpecificationGroup isExist = skuSpecificationGroupMapper.selectOne(group);
                if (isExist == null) {
                    // 父级 为空
                    affect += skuSpecificationGroupMapper.insert(group);
                    if (group.getItems() != null && group.getItems().size() > 0) {
                        for (SkuSpecificationGroup child : group.getItems()) {
                            // 插入 父节点
                            child.setType("Spec");
                            child.setPid(group.getId());
                            affect += skuSpecificationGroupMapper.insert(child);
                        }
                    }
                }
                else {
                    if (group.getItems() != null && group.getItems().size() > 0) {
                        for (SkuSpecificationGroup child : group.getItems()) {
                            // 子节点  不为空
                            child.setPid(isExist.getId());
                            child.setType("Spec");
                            affect += skuSpecificationGroupMapper.insert(child);
                        }
                    }
                }
            }
        }

        return affect;
    }


    /**
     * 更新 某个 列表 节点 下的 多个 规格
     */
    public Integer updateSpecChildren(Long categoryId, CategorySpecModel entity) {

        int affect = 0;
        entity.setId(categoryId);
        affect += categoryMapper.updateById(entity);

        if (entity.getGroups() != null && entity.getGroups().size() > 0) {
            for (SkuSpecificationGroupModel group : entity.getGroups()) {
                group.setPid(categoryId);
                group.setType("Category");
                SkuSpecificationGroup isExist = skuSpecificationGroupMapper.selectOne(group);
                if (isExist == null) {
                    // 父级 为空
                    affect += skuSpecificationGroupMapper.insert(group);
                    if (group.getItems() != null && group.getItems().size() > 0) {
                        for (SkuSpecificationGroup child : group.getItems()) {
                            child.setType("Spec");
                            child.setPid(group.getId());
                            affect += skuSpecificationGroupMapper.insert(child);
                        }
                    }
                } else {
                    affect += skuSpecificationGroupMapper.updateById(group);
                    if (group.getItems() != null && group.getItems().size() > 0) {
                        for (SkuSpecificationGroup child : group.getItems()) {
                            // 子节点  不为空
                            child.setPid(isExist.getId());
                            child.setType("Spec");
                            SkuSpecificationGroup originChild = skuSpecificationGroupMapper.selectOne(child);
                            if (originChild==null){
                                affect += skuSpecificationGroupMapper.insert(child);
                            }else {
                                affect += skuSpecificationGroupMapper.updateById(child);
                            }
                        }
                    }
                }
            }
        } else {
            List<SkuSpecificationGroup> groups = skuSpecificationGroupMapper.selectList(new QueryWrapper<SkuSpecificationGroup>()
                    .eq("pid", categoryId).like("type", "Category"));
            for (SkuSpecificationGroup group : groups) {
                affect += skuSpecificationGroupMapper.delete(new QueryWrapper<SkuSpecificationGroup>()
                        .eq("pid", group.getId()).like("type", "Spec"));
                affect += skuSpecificationGroupMapper.deleteById(group.getId());
            }
        }
        return affect;
    }

    // 删除 类别  及类别下的所有的所有的规格

    public Integer deleteCategory(Long categoryId) {

        int affect = 0;
        List<SkuSpecificationGroup> groups = skuSpecificationGroupMapper.selectList(new QueryWrapper<SkuSpecificationGroup>()
                .eq("pid", categoryId).like("type", "Category"));
        if (groups == null || groups.size() == 0) {
            affect += categoryMapper.deleteById(categoryId);
            return affect;
        }

        for (SkuSpecificationGroup group : groups) {
            skuSpecificationGroupMapper.delete(new QueryWrapper<SkuSpecificationGroup>()
                    .eq("pid", group.getId()).like("type", "Spec"));
            affect += skuSpecificationGroupMapper.deleteById(group.getId());
        }
        return affect;
    }


    /**
     * 获取 某个 列表下 所有的规格，包括子规格
     * @Param Long categoryId
     */
    public List<SkuSpecificationGroupModel> getSpecChildren(Long categoryId) {

        List<SkuSpecificationGroup> groups = skuSpecificationGroupMapper.selectList(new QueryWrapper<SkuSpecificationGroup>()
                .eq("pid",categoryId).like("type","Category"));

        if (groups != null && groups.size() > 0) {

            List<SkuSpecificationGroupModel> models = new ArrayList<>();

            for (SkuSpecificationGroup group : groups) {
                List<SkuSpecificationGroup> children = skuSpecificationGroupMapper.selectList(new QueryWrapper<SkuSpecificationGroup>()
                        .eq("pid",group.getId()).like("type","Spec"));
                JSONObject json = JSON.parseObject(JSON.toJSONString(group));
                json.put("items",children);
                SkuSpecificationGroupModel model = JSON.parseObject(JSON.toJSONString(json),SkuSpecificationGroupModel.class);
                models.add(model);
            }
            return models;
        } else {
            return null;
        }
    }


    /**
     * 所有的 规格 包括 所有的子规格
     */

    public List<SkuSpecificationGroupModel> allSpec() {
        List<SkuSpecificationGroupModel> models = new ArrayList<>();

        List<SkuSpecificationGroup> specifications =
                skuSpecificationGroupMapper.selectList(
                        new QueryWrapper<SkuSpecificationGroup>().like("type", "Category"));
        // Fix: if no category
        //
        if (specifications != null && specifications.size() > 0) {
            for (SkuSpecificationGroup skuSpecificationGroup : specifications) {
                List<SkuSpecificationGroup> children = skuSpecificationGroupMapper.selectList(new QueryWrapper<SkuSpecificationGroup>()
                        .eq("pid",skuSpecificationGroup.getId()).like("type","Spec"));
                JSONObject json = JSON.parseObject(JSON.toJSONString(skuSpecificationGroup));
                json.put("items",children);
                SkuSpecificationGroupModel model = JSON.parseObject(JSON.toJSONString(json),SkuSpecificationGroupModel.class);
                models.add(model);
            }
        } else {
            /// get all spec group
            SkuSpecificationGroupModel model = new SkuSpecificationGroupModel();
            List<SkuSpecificationGroup> children = skuSpecificationGroupMapper.selectList(
                    new QueryWrapper<SkuSpecificationGroup>().like("type", "Spec"));
            model.setItems(children);
            /// add single spec group
            models.add(model);
        }

        return models;
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
