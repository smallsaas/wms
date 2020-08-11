package com.jfeat.am.module.product.services.domain.service.impl;

import com.jfeat.am.module.product.services.crud.service.CRUDProductService;
import com.jfeat.am.module.product.services.crud.service.impl.CRUDProductServiceImpl;
import com.jfeat.am.module.product.services.domain.dao.QueryProductDao;
import com.jfeat.am.module.product.services.domain.model.ProductDetailsModel;
import com.jfeat.am.module.product.services.domain.model.ProductModel;
import com.jfeat.am.module.product.services.domain.service.ProductService;
import com.jfeat.am.module.product.services.persistence.dao.ConditionMapper;
import com.jfeat.am.module.product.services.persistence.dao.ProductCategoryMapper;
import com.jfeat.am.module.product.services.persistence.dao.ProductUnitMapper;
import com.jfeat.am.module.product.services.persistence.model.Condition;
import com.jfeat.crud.plus.CRUDObject;
import org.springframework.stereotype.Service;

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
@Service("ProductService")
public class ProductServiceImpl extends CRUDProductServiceImpl implements ProductService {

    @Resource
    CRUDProductService productService;
    @Resource
    ProductCategoryMapper productCategoryMapper;
    @Resource
    ConditionMapper conditionMapper;
    /*@Resource
    ProductTagMapper tagMapper;*/
    @Resource
    ProductUnitMapper productUnitMapper;
    @Resource
    QueryProductDao queryProductDao;


    public ProductModel getProductDetails(long id) {
        CRUDObject<ProductModel> master = productService.retrieveMasterModel(id);

        if (master != null) {
            ProductModel model = master.toJavaObject(ProductModel.class);
            // 查找 condition Name
            if (model.getProductConditions() != null || model.getProductConditions().size() > 0) {
                List<String> conditionName = new ArrayList<>();
                for (int i = 0; i < model.getProductConditions().size(); i++) {
                    Condition condition = conditionMapper.selectById(model.getProductConditions().get(i).getConditionId());
                    conditionName.add(condition.getConditionName());
                }
                model.setConditionName(conditionName);
            }
            // 查找 tag Name
            /*if (model.getProductTagRelations() != null || model.getProductTagRelations().size() > 0) {
                List<String> tagName = new ArrayList<>();
                for (int i = 0; i < model.getProductTagRelations().size(); i++) {
                    ProductTag tag = tagMapper.selectById(model.getProductTagRelations().get(i).getTagId());
                    tagName.add(tag.getTagName());
                }
                model.setTagName(tagName);
            }*/
            // 查找 类别 名称
            if (productService.retrieveMaster(id).getProductCategoryId() != null) {
                String productCategoryName = productCategoryMapper.selectById(productService.retrieveMaster(id).getProductCategoryId()).getCategoryName();
                model.setProductCategoryName(productCategoryName == null ? null : productCategoryName);
            }
            // 查找 单位
            /*if (model.getProductUnits() != null || model.getProductUnits().size() > 0) {
                for (int i = 0; i < model.getProductTagRelations().size(); i++) {
                    ProductUnit unit = productUnitMapper.selectById(model.getProductUnits().get(i));
                    model.getProductUnits().add(unit);
                }
                return model;
            }*/

            return model;
        } else {
            return null;
        }
    }


    /**
     * 返回属于产品的所有的属性对象
     * */
    public ProductDetailsModel productDetails(Long id) {
        return queryProductDao.productDetails(id);
    }

}
