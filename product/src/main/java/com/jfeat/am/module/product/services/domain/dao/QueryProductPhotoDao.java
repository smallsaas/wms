package com.jfeat.am.module.product.services.domain.dao;

import com.jfeat.am.module.product.services.domain.model.ProductPhotoRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-19
 */
public interface QueryProductPhotoDao extends BaseMapper<ProductPhotoRecord> {
    List<ProductPhotoRecord> findProductPhotoPage(Page<ProductPhotoRecord> page, @Param("record") ProductPhotoRecord record, @Param("orderBy") String orderBy);
}