package com.jfeat.am.module.product.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.product.services.domain.model.ProductCategoryModel;
import com.jfeat.am.module.product.services.domain.service.ProductCategoryService;

import com.jfeat.am.module.product.services.crud.service.impl.CRUDProductCategoryServiceImpl;
import com.jfeat.am.module.product.services.persistence.dao.ProductCategoryMapper;
import com.jfeat.am.module.product.services.persistence.model.ProductCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl extends CRUDProductCategoryServiceImpl implements ProductCategoryService {


    @Resource
    ProductCategoryMapper productCategoryMapper;


    @Transactional
    public Integer createChildren(Long categoryId, ProductCategoryModel model){
        int affect = 0;

        for (ProductCategory category : model.getCategories()){
            category.setPid(categoryId);
            affect += productCategoryMapper.insert(category);
        }
        return affect;
    }


    @Override
    public List<Map<String, Object>> findAllToMap() {
        return productCategoryMapper.selectMaps(new QueryWrapper<>());
    }

    @Override
    public IPage<ProductCategory> categories(Page<ProductCategory> page) {
        return productCategoryMapper.selectPage(page, new QueryWrapper<>());
    }

    @Override
    public List<Map<String, Object>> findAllGrouping() {
        List<Map<String, Object>> list = findAllToMap();
        List<Map<String, Object>> provinces = list.stream().filter(pcd -> pcd.get("pid") == null).collect(Collectors.toList());
        provinces.forEach(p -> {
            List<Map<String, Object>> cities = list.stream().filter(pcd -> p.get("id").equals(pcd.get("pid"))).collect(Collectors.toList());
            cities.forEach(c -> {
                List<Map<String, Object>> districts = list.stream().filter(pcd -> c.get("id").equals(pcd.get("pid"))).collect(Collectors.toList());
                c.put("children", districts);
            });
            p.put("children", cities);
        });
        return provinces;
    }

    @Override
    public List<ProductCategory> queryPcdByName(String name) {
        return productCategoryMapper.selectList(new QueryWrapper<ProductCategory>().eq("categoryName",name));
    }

    @Override
    public IPage<ProductCategory> queryByName(Page<ProductCategory> page, String categoryName) {
        return productCategoryMapper.selectPage(page,new QueryWrapper<ProductCategory>().eq("category_name",categoryName));
    }

    @Override
    public List<ProductCategory> queryPcdByPid(Long pid) {
        return productCategoryMapper.selectList(new QueryWrapper<ProductCategory>().eq("pid",pid));
    }
}
