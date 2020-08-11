package com.jfeat.am.module.product.services.domain.dao;

import com.jfeat.am.module.product.services.domain.model.ProductDetailsModel;
import com.jfeat.am.module.product.services.domain.model.ProductRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-19
 */
public interface QueryProductDao extends BaseMapper<ProductRecord> {
    List<ProductRecord> findProductPage(Page<ProductRecord> page, @Param("record") ProductRecord record, @Param("orderBy") String orderBy);

    ProductDetailsModel productDetails(@Param("id")Long id);
}