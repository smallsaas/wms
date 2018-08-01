package com.jfeat.am.module.sku.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.module.product.services.persistence.dao.ProductMapper;
import com.jfeat.am.module.product.services.persistence.model.Product;
import com.jfeat.am.module.sku.services.crud.filter.SkuProductFilter;
import com.jfeat.am.module.sku.services.crud.model.SkuProductModel;
import com.jfeat.am.module.sku.services.crud.service.CRUDSkuProductService;
import com.jfeat.am.module.sku.services.crud.service.impl.CRUDSkuProductServiceImpl;
import com.jfeat.am.module.sku.services.domain.model.CreateSkuProductModel;
import com.jfeat.am.module.sku.services.domain.service.SkuProductService;
import com.jfeat.am.module.sku.services.persistence.dao.SkuPriceHistoryMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuSpecificationGroupMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuSpecificationMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuPriceHistory;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.sku.services.persistence.model.SkuSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    @Transactional
    public Integer createSku(CreateSkuProductModel model) {
        // 插入 产品
        int affect = 0;

        Product product = model;

        affect += productMapper.insert(product);

        if (model.getSkus() != null && model.getSkus().size() > 0) {
            for (SkuProductModel entity : model.getSkus()) {

                entity.setSkuName(product.getName());
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


    @Transactional
    public Integer updateSku(Long productId, CreateSkuProductModel model) {
        // 更新 产品
        int affect = 0;
        affect += productMapper.updateById(model);

        List<SkuProductModel> models = model.getSkus();

        for (SkuProductModel entity : models){
            if(entity.getId() != null){

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
                    affect += skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id",entity.getId()));
                } else {
                    for (Long id : entity.getSpecId()) {
                        affect += skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id",entity.getId()));
                        SkuSpecification specification = new SkuSpecification();
                        specification.setSkuId(entity.getId());
                        specification.setGroupId(id);
                        affect += skuSpecificationMapper.insert(specification);
                    }
                }

                affect += crudSkuProductService.updateMaster(entity,null,null,null);
            }else {
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

            return affect;

        }




        /*SkuProduct originSkuProduct = crudSkuProductService.retrieveMaster(skuId);

        SkuPriceHistory history = new SkuPriceHistory();
        history.setSkuId(skuId);
        history.setAfterPrice(originSkuProduct.getSkuPrice());
        SkuPriceHistory originHistory = skuPriceHistoryMapper.selectOne(history);


        if (model.getSkus().get(0).getSkuPrice() != null && model.getSkus().get(0).getSkuPrice().compareTo(originSkuProduct.getSkuPrice()) != 0) {
            SkuPriceHistory updateHistory = new SkuPriceHistory();
            updateHistory.setOriginPrice(originHistory.getAfterPrice());
            updateHistory.setAfterPrice(model.getSkus().get(0).getSkuPrice());
            updateHistory.setUpdateTime(new Date());
            affect += skuPriceHistoryMapper.insert(history);
        }
        affect += crudSkuProductService.updateMaster(model.getSkus().get(0), null, null, null);


        if (model.getSkus().get(0).getSpecId() == null && model.getSkus().get(0).getSpecId().size() == 0) {
            skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", skuId));
        } else {
            skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", skuId));
            for (Long id : model.getSkus().get(0).getSpecId()) {
                SkuSpecification specification = new SkuSpecification();
                specification.setSkuId(skuId);
                specification.setGroupId(id);
                affect += skuSpecificationMapper.insert(specification);
            }
        }*/
        return affect;
    }


    @Transactional
    public Integer deleteSku(Long skuId) {
        int affect = 0;
        affect += skuSpecificationMapper.delete(new EntityWrapper<SkuSpecification>().eq("sku_id", skuId));
        affect += skuPriceHistoryMapper.delete(new EntityWrapper<SkuPriceHistory>().eq("sku_id", skuId));
        affect += crudSkuProductService.deleteMaster(skuId);
        return affect;
    }


    /**
     * all sku in this product
     */
    public CreateSkuProductModel productsTotalDetails(Long id) {

        Product product = productMapper.selectById(id);

        JSONObject object = JSON.parseObject(JSON.toJSONString(product));

        List<SkuProductModel> models = new ArrayList<>();


        List<SkuProduct> skus = skuProductMapper.selectList(new EntityWrapper<SkuProduct>().eq("product_id", id));

        for (SkuProduct sku : skus) {
            CRUDObject<SkuProductModel> model = crudSkuProductService.retrieveMaster(sku.getId(), null, null, null);
            SkuProductModel skuDetails = model.toJavaObject(SkuProductModel.class);
            models.add(skuDetails);
        }

        object.put("skus", models == null ? null : models);

        CreateSkuProductModel model = JSONObject.parseObject(JSONObject.toJSONString(object), CreateSkuProductModel.class);
        return model;
    }
}
