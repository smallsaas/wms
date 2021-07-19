package com.jfeat.am.module.sku.services.domain.dao;

import com.jfeat.am.module.sku.services.domain.model.SkuPhotoRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-18
 */
public interface QuerySkuPhotoDao extends BaseMapper<SkuPhotoRecord> {
    List<SkuPhotoRecord> findSkuPhotoPage(Page<SkuPhotoRecord> page, @Param("record") SkuPhotoRecord record, @Param("orderBy") String orderBy);
}