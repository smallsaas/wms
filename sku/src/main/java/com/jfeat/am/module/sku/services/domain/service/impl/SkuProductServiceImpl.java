package com.jfeat.am.module.sku.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.module.product.services.persistence.dao.ProductMapper;
import com.jfeat.am.module.product.services.persistence.model.Product;
import com.jfeat.am.module.product.services.persistence.model.SpecificationGroup;
import com.jfeat.am.module.sku.services.crud.filter.SkuProductFilter;
import com.jfeat.am.module.sku.services.crud.model.SkuProductModel;
import com.jfeat.am.module.sku.services.crud.model.SkuSpecificationGroupModel;
import com.jfeat.am.module.sku.services.crud.service.CRUDSkuProductService;
import com.jfeat.am.module.sku.services.crud.service.impl.CRUDSkuProductServiceImpl;
import com.jfeat.am.module.sku.services.domain.model.CreateSkuProductModel;
import com.jfeat.am.module.sku.services.domain.service.SkuProductService;
import com.jfeat.am.module.sku.services.persistence.dao.*;
import com.jfeat.am.module.sku.services.persistence.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("skuProductService")
public class SkuProductServiceImpl extends CRUDSkuProductServiceImpl implements SkuProductService {

    @Resource
    CRUDSkuProductService crudSkuProductService;
    @Resource
    SkuPriceHistoryMapper skuPriceHistoryMapper;
    @Resource
    SkuSpecificationGroupMapper skuSpecificationGroupMapper;
    @Resource
    SkuSpecificationMapper skuSpecificationMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    SkuProductMapper skuProductMapper;
    @Resource
    SkuUnitMapper skuUnitMapper;
    @Resource
    SkuPhotoMapper skuPhotoMapper;
    @Resource
    SkuTagRelationMapper skuTagRelationMapper;


    @Transactional
    public Integer createSku(CreateSkuProductModel model) {
        // 插入 产品
        int affect = 0;

        Product product = model;

        affect += productMapper.insert(product);

        if (model.getSkus() != null && model.getSkus().size() > 0) {
            for (SkuProductModel entity : model.getSkus()) {

                entity.setSkuName(product.getName());
                entity.setBarCode(product.getBarCode());
                entity.setProductId(product.getId());
                SkuProductFilter skuProductFilter = new SkuProductFilter();
                affect += crudSkuProductService.createMaster(entity, skuProductFilter, null, null);

                if (entity.getSpecId() == null || entity.getSpecId().size() == 0) {

                } else {
                    for (Long id : entity.getSpecId()) {
                        SkuSpecification specification = new SkuSpecification();
                        specification.setSkuId(skuProductFilter.result().get("id") == null ? null : (Long) skuProductFilter.result().get("id"));
                        specification.setGroupId(id);
                        skuSpecificationMapper.insert(specification);
                    }
                }

                if (entity.getSkuPrice() != null) {
                    SkuPriceHistory history = new SkuPriceHistory();
                    // 初始 插入 的时候 原始 以及 修改 后的 价格 都一样
                    history.setOriginPrice(entity.getSkuPrice());
                    history.setSkuId(skuProductFilter.result().get("id") == null ? null : (Long) skuProductFilter.result().get("id"));
                    history.setAfterPrice(entity.getSkuPrice());
                    history.setUpdateTime(new Date());
                    affect += skuPriceHistoryMapper.insert(history);
                }
            }
        } else {

            SkuProductModel entity = new SkuProductModel();
            entity.setSkuPrice(product.getPrice());
            entity.setSkuCode(product.getProductCode());
            entity.setSkuName(product.getName());
            entity.setProductId(product.getId());
            SkuProductFilter skuProductFilter = new SkuProductFilter();
            affect += crudSkuProductService.createMaster(entity, skuProductFilter, null, null);

            if (entity.getSkuPrice() != null) {
                SkuPriceHistory history = new SkuPriceHistory();
                // 初始 插入 的时候 原始 以及 修改 后的 价格 都一样
                history.setOriginPrice(entity.getSkuPrice());
                history.setSkuId(skuProductFilter.result().get("id") == null ? null : (Long) skuProductFilter.result().get("id"));
                history.setAfterPrice(entity.getSkuPrice());
                history.setUpdateTime(new Date());
                affect += skuPriceHistoryMapper.insert(history);
            }
        }
        return affect;
    }


    /**
     * update sku 以及 产品 ，只有一个 sku
     */
    @Transactional
    public Integer updateSkuMaster(Long skuId, CreateSkuProductModel model) {
        // 更新 产品
        int affect = 0;
        SkuProduct originSkuProduct = crudSkuProductService.retrieveMaster(skuId);

        model.setId(originSkuProduct.getProductId());
        affect += productMapper.updateById(model);


        SkuPriceHistory history = new SkuPriceHistory();
        history.setSkuId(skuId);
        history.setAfterPrice(originSkuProduct.getSkuPrice());
        SkuPriceHistory originHistory = skuPriceHistoryMapper.selectOne(history);

        if (model.getSkus() != null && model.getSkus().size() > 0) {
            if (model.getSkus().get(0).getSkuPrice() != null && model.getSkus().get(0).getSkuPrice().compareTo(originSkuProduct.getSkuPrice()) != 0) {
                SkuPriceHistory updateHistory = new SkuPriceHistory();
                updateHistory.setOriginPrice(originHistory.getAfterPrice());
                updateHistory.setAfterPrice(model.getSkus().get(0).getSkuPrice());
                updateHistory.setUpdateTime(new Date());
                affect += skuPriceHistoryMapper.insert(history);
            }
            model.getSkus().get(0).setBarCode(model.getBarCode());
            model.getSkus().get(0).setSkuName(model.getName());
            model.getSkus().get(0).setSkuPrice(model.getPrice());
            affect += crudSkuProductService.updateMaster(model.getSkus().get(0), null, null, null);


            if (model.getSkus().get(0).getSpecId() == null || model.getSkus().get(0).getSpecId().size() == 0) {
                skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", skuId));
            } else {
                skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", skuId));
                for (Long id : model.getSkus().get(0).getSpecId()) {
                    SkuSpecification specification = new SkuSpecification();
                    specification.setSkuId(skuId);
                    specification.setGroupId(id);
                    affect += skuSpecificationMapper.insert(specification);
                }
            }
        }else {
            SkuPriceHistory updateHistory = new SkuPriceHistory();
            updateHistory.setOriginPrice(originHistory.getAfterPrice());
            updateHistory.setAfterPrice(model.getPrice());
            updateHistory.setSkuId(skuId);
            updateHistory.setUpdateTime(new Date());
            affect += skuPriceHistoryMapper.insert(updateHistory);

        }
        return affect;
    }

    /**
     * 删除该产品下所有的 sku
     */
    @Transactional
    public Integer deleteSkus(Long productId) {
        int affect = 0;
        List<SkuProduct> skus = skuProductMapper.selectList(new EntityWrapper<SkuProduct>().eq("product_id", productId));
        for (SkuProduct sku : skus) {
            affect += skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", sku.getId()));
            affect += skuPriceHistoryMapper.delete(new EntityWrapper<SkuPriceHistory>().eq("sku_id", sku.getId()));
            affect += skuPhotoMapper.delete(new EntityWrapper<SkuPhoto>().eq("sku_id", sku.getId()));
            affect += skuTagRelationMapper.delete(new EntityWrapper<SkuTagRelation>().eq("sku_id", sku.getId()));
            affect += skuUnitMapper.delete(new EntityWrapper<SkuUnit>().eq("sku_id", sku.getId()));
            affect += crudSkuProductService.deleteMaster(sku.getId());
        }
        return affect;
    }


    /**
     * 删除单个 sku
     */
    @Transactional
    public Integer deleteSku(Long skuId) {
        int affect = 0;
        affect += skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", skuId));
        affect += skuPriceHistoryMapper.delete(new EntityWrapper<SkuPriceHistory>().eq("sku_id", skuId));
        affect += skuPhotoMapper.delete(new EntityWrapper<SkuPhoto>().eq("sku_id", skuId));
        affect += skuTagRelationMapper.delete(new EntityWrapper<SkuTagRelation>().eq("sku_id", skuId));
        affect += skuUnitMapper.delete(new EntityWrapper<SkuUnit>().eq("sku_id", skuId));
        affect += crudSkuProductService.deleteMaster(skuId);

        return affect;
    }


    /**
     * 删除产品
     */
    @Transactional
    public Integer deleteProduct(Long productId) {
        int affect = deleteSkus(productId);
        affect += productMapper.deleteById(productId);
        return affect;
    }


    /**
     * 批量删除 sku
     */
    @Transactional
    public Integer bulkDeleteSku(Ids ids) {
        int affect = 0;
        for (Long skuId : ids.getIds()) {
            affect += skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", skuId));
            affect += skuPriceHistoryMapper.delete(new EntityWrapper<SkuPriceHistory>().eq("sku_id", skuId));
            affect += skuPhotoMapper.delete(new EntityWrapper<SkuPhoto>().eq("sku_id", skuId));
            affect += skuTagRelationMapper.delete(new EntityWrapper<SkuTagRelation>().eq("sku_id", skuId));
            affect += skuUnitMapper.delete(new EntityWrapper<SkuUnit>().eq("sku_id", skuId));
            affect += crudSkuProductService.deleteMaster(skuId);
        }
        return affect;
    }


    /**
     * all sku msg including product
     */
    @Transactional
    public CreateSkuProductModel skuTotalDetails(Long id) {

        SkuProduct model = crudSkuProductService.retrieveMaster(id);
        JSONObject object = JSON.parseObject(JSON.toJSONString(model));

        List<SkuPhoto> photos = skuPhotoMapper.selectList(new EntityWrapper<SkuPhoto>().eq("sku_id", id));
        object.put("skuPhotos", photos);

        List<SkuSpecification> specifications = skuSpecificationMapper.selectList((new EntityWrapper<SkuSpecification>().eq("sku_id", id)));
        List<SkuSpecificationGroupModel> groups = new ArrayList<>();
        for (SkuSpecification skuSpecification : specifications) {
            SkuSpecificationGroup group = skuSpecificationGroupMapper.selectById(skuSpecification.getGroupId());
            SkuSpecificationGroup parentGroup = skuSpecificationGroupMapper.selectById(group.getPid());
            if (groups == null || groups.size() == 0) {
                JSONObject parent = JSON.parseObject(JSON.toJSONString(parentGroup));
                parent.put("items", group);
                SkuSpecificationGroupModel parentModel = JSONObject.parseObject(JSONObject.toJSONString(parent), SkuSpecificationGroupModel.class);
                groups.add(parentModel);
            } else {
                SkuSpecificationGroupModel fixed = new SkuSpecificationGroupModel();
                for (SkuSpecificationGroupModel isExist : groups) {
                    if (!parentGroup.getId().equals(isExist.getId())) {
                        JSONObject parent = JSON.parseObject(JSON.toJSONString(parentGroup));
                        parent.put("items", group);
                        fixed = JSONObject.parseObject(JSONObject.toJSONString(parent), SkuSpecificationGroupModel.class);
                    } else {
                        fixed.getItems().add(group);
                    }
                }
                groups.add(fixed);
            }
        }
        object.put("items", groups == null ? null : groups);

        SkuProductModel skuProductModel = JSONObject.parseObject(JSONObject.toJSONString(object), SkuProductModel.class);

        Product product = productMapper.selectById(skuProductModel.getProductId());
        JSONObject productObject = JSON.parseObject(JSON.toJSONString(product));

        List<SkuProductModel> skuProductModels = new ArrayList<>();
        skuProductModels.add(skuProductModel);
        productObject.put("skus", skuProductModels == null ? null : skuProductModels);

        CreateSkuProductModel productModel = JSONObject.parseObject(JSONObject.toJSONString(productObject), CreateSkuProductModel.class);
        return productModel;
    }


    @Transactional
    @Deprecated
    public Integer updateSku(Long productId, CreateSkuProductModel model) {
        // 更新 产品
        int affect = 0;
        affect += productMapper.updateById(model);

        List<SkuProductModel> models = model.getSkus();

        for (SkuProductModel entity : models) {
            if (entity.getId() != null) {

                SkuPriceHistory history = new SkuPriceHistory();
                history.setSkuId(entity.getId());
                SkuPriceHistory originHistory = skuPriceHistoryMapper.selectOne(history);
                if (entity.getSkuPrice() != null && entity.getSkuPrice().compareTo(originHistory.getAfterPrice()) != 0) {
                    SkuPriceHistory updateHistory = new SkuPriceHistory();
                    updateHistory.setSkuId(entity.getId());
                    updateHistory.setOriginPrice(originHistory.getAfterPrice());
                    updateHistory.setAfterPrice(entity.getSkuPrice());
                    updateHistory.setUpdateTime(new Date());
                    affect += skuPriceHistoryMapper.insert(history);
                }
                if (entity.getSpecId() == null || entity.getSpecId().size() == 0) {
                    affect += skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", entity.getId()));
                } else {
                    for (Long id : entity.getSpecId()) {
                        affect += skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", entity.getId()));
                        SkuSpecification specification = new SkuSpecification();
                        specification.setSkuId(entity.getId());
                        specification.setGroupId(id);
                        affect += skuSpecificationMapper.insert(specification);
                    }
                }

                affect += crudSkuProductService.updateMaster(entity, null, null, null);
            } else {
                // 修改前不存在 的SKU 执行 重新插入 操作
                entity.setSkuName(model.getName());
                entity.setProductId(productId);
                SkuProductFilter skuProductFilter = new SkuProductFilter();
                affect += crudSkuProductService.createMaster(entity, skuProductFilter, null, null);

                if (entity.getSpecId() == null || entity.getSpecId().size() == 0) {

                } else {
                    for (Long id : entity.getSpecId()) {
                        SkuSpecification specification = new SkuSpecification();
                        specification.setSkuId(skuProductFilter.result().get("id") == null ? null : (Long) skuProductFilter.result().get("id"));
                        specification.setGroupId(id);
                        affect += skuSpecificationMapper.insert(specification);
                    }
                }

                if (entity.getSkuPrice() != null) {
                    SkuPriceHistory history = new SkuPriceHistory();
                    // 初始 插入 的时候 原始 以及 修改 后的 价格 都一样
                    history.setOriginPrice(entity.getSkuPrice());
                    history.setSkuId(skuProductFilter.result().get("id") == null ? null : (Long) skuProductFilter.result().get("id"));
                    history.setAfterPrice(entity.getSkuPrice());
                    history.setUpdateTime(new Date());
                    affect += skuPriceHistoryMapper.insert(history);
                }
            }
        }
        return affect;
    }


    /**
     * all sku in this product
     */
    public CreateSkuProductModel skuDetails(Long skuId) {


        List<SkuProductModel> models = new ArrayList<>();
        CRUDObject<SkuProductModel> skus = crudSkuProductService.retrieveMaster(skuId, null, null, null);

        SkuProductModel skuDetails = skus.toJavaObject(SkuProductModel.class);
        models.add(skuDetails);

        Product product = productMapper.selectById(skuDetails.getId());
        JSONObject object = JSON.parseObject(JSON.toJSONString(product));

        object.put("skus", models == null ? null : models);

        CreateSkuProductModel model = JSONObject.parseObject(JSONObject.toJSONString(object), CreateSkuProductModel.class);
        return model;
    }
}

