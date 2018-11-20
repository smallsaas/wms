package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageOutFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.definition.SalesStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.dao.QuerySalesDao;
import com.jfeat.am.module.warehouse.services.domain.model.*;
import com.jfeat.am.module.warehouse.services.domain.service.SalesService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDSalesServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutItemMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.Sales;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOut;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.constant.tips.Ids;

import javax.annotation.Resource;

import com.jfeat.am.module.warehouse.services.persistence.dao.SalesMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
@Service
public class SalesServiceImpl extends CRUDSalesServiceImpl implements SalesService {
    @Resource
    private SalesMapper salesMapper;


    @Resource
    StorageOutItemMapper outItemMapper;
    @Resource
    SkuProductMapper skuProductMapper;
    @Resource
    QuerySalesDao querySalesDao;
    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    CRUDStorageOutService crudStorageOutService;
    @Resource
    StorageOutMapper outMapper;

    @Transactional
    public Integer createSales(Long userId, SalesModel model) {

        int affected = 0;

        if (model.getOutItems() == null || model.getOutItems().size() <= 0) {
            throw new BusinessException(5000, "请先选择需要出库的商品！");
        }
        BigDecimal totalSpend = BigDecimal.valueOf(0);
        model.setOriginatorId(userId);
        model.setTransactionTime(new Date());
        model.setSalesStatus(SalesStatus.WaitForStorageOut.toString());

        int totalCount = 0;
        for (StorageOutItem item : model.getOutItems()) {
            BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
            sum = sum.multiply(item.getTransactionSkuPrice());
            totalSpend = totalSpend.add(sum);

            totalCount += item.getTransactionQuantities();
        }
        model.setSalesTotal(totalSpend);
        model.setTotalCount(totalCount);
        affected += salesMapper.insert(model);


        for (StorageOutItem item : model.getOutItems()) {
            if (item.getTransactionQuantities() == 0) {
                throw new BusinessException(4501, "出库数量不能为0，请重新输入！");
            }
            item.setRelationCode(model.getSalesCode());
            item.setStorageOutId(model.getId());
            item.setType(TransactionType.CustomerStorageOut.toString());
            affected += outItemMapper.insert(item);
        }
        return affected;

    }


    @Transactional
    public Integer updateSales(Long userId, Long salesId, SalesModel model) {
        int affected = 0;

        Sales sales = salesMapper.selectById(salesId);
        // 等待入库的情况下才能执行更新的操作
        if (sales.getSalesStatus().compareTo(SalesStatus.WaitForStorageOut.toString()) == 0) {
            model.setId(salesId);
            model.setTransactionTime(new Date());
            if (model.getOutItems() == null || model.getOutItems().size() == 0) {
                throw new BusinessException(5002, "请至少选择一种需要出库的商品");
            } else {
                outItemMapper.delete(new EntityWrapper<StorageOutItem>().eq(StorageOutItem.STORAGE_OUT_ID, salesId).eq(StorageOutItem.TYPE, TransactionType.SalesOut.toString()));

                int totalCount = 0;
                BigDecimal totalSpend = BigDecimal.valueOf(0);
                for (StorageOutItem item : model.getOutItems()) {
                    BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
                    sum = sum.multiply(item.getTransactionSkuPrice());
                    totalSpend = totalSpend.add(sum);
                    totalCount += item.getTransactionQuantities();
                }
                model.setTotalCount(totalCount);
                model.setSalesTotal(totalSpend);
                for (StorageOutItem item : model.getOutItems()) {
                    item.setRelationCode(sales.getSalesCode());
                    item.setStorageOutId(salesId);
                    item.setType(TransactionType.CustomerStorageOut.toString());
                    affected += outItemMapper.insert(item);
                }
                affected += salesMapper.updateById(model);
            }
            return affected;
        }
        throw new BusinessException(BusinessCode.ErrorStatus);
    }


    @Transactional
    public Integer executionStorageOut(Long userId, Long salesId, SalesModel model) {

        int affected = 0;
        int inSuccess = 0;
        int outCount = 0;
        // 总需要入库的商品的数量

        Sales sales = salesMapper.selectById(salesId);
        model.setId(salesId);

        if (model.getOutItems() != null && model.getOutItems().size() > 0) {
            // 判断所有的商品是否都已经入库
            StorageOutModel out = new StorageOutModel();
            out.setOriginatorId(userId);
            out.setStorageOutTime(new Date());
            out.setTransactionTime(new Date());
            out.setOriginatorName(model.getOriginatorName());
            // 使用field1去接收 warehouseId 字段
            out.setWarehouseId(Long.valueOf(model.getField1()));
            //使用 field 去接收 入库 code
            out.setTransactionCode(model.getField2());
            out.setSalesId(salesId);
            out.setTransactionType(TransactionType.CustomerStorageOut.toString());
            StorageOutFilter storageOutFilter = new StorageOutFilter();

            List<StorageOutItem> storageOutItems = new ArrayList<>();
            for (StorageOutItem item : model.getOutItems()) {
                if (item.getTransactionQuantities() > 0) {
                    SkuProduct skuProduct = skuProductMapper.selectById(item.getSkuId());
                    item.setRelationCode(sales.getSalesCode());
                    // 某个 sku 的 sales 的数量
                    Integer skuSalesCount = querySalesDao.totalCount(salesId, item.getSkuId());
                    //某个 sku 入库历史数量
                    Integer skuSalesOutCount = querySalesDao.finishedCount(salesId, item.getSkuId());
                    if (skuSalesOutCount == null) {
                        skuSalesOutCount = 0;
                    }

                    if (item.getTransactionQuantities() > (skuSalesCount - skuSalesOutCount)) {
                        throw new BusinessException(4500, "\"" + skuProduct.getSkuName() + "\"" + "出库数不能大于订单数，请先核对出库数！");
                    }

                    outCount += item.getTransactionQuantities() + skuSalesOutCount;

                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(item.getSkuId());
                    isExistInventory.setWarehouseId(Long.valueOf(model.getField1()));
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    if (originInventory != null) {
                        Integer validSku = originInventory.getValidSku() - item.getTransactionQuantities();
                        // 操作后的 数量
                        item.setAfterTransactionQuantities(validSku);
                        originInventory.setValidSku(validSku);
                        affected += inventoryMapper.updateById(originInventory);

                    } else {
                        throw new BusinessException(BusinessCode.DatabaseUpdateError);
                    }
                }
                item.setTransactionTime(out.getStorageOutTime());
                storageOutItems.add(item);
            }
            out.setStorageOutItems(storageOutItems);
            inSuccess = crudStorageOutService.createMaster(out, storageOutFilter, null, null);

            if (outCount == sales.getTotalCount()) {
                model.setSalesStatus(SalesStatus.TotalStorageOut.toString());
            } else {
                model.setSalesStatus(SalesStatus.SectionStorageOut.toString());

            }
        }
        affected += salesMapper.updateById(model);
        return affected;

    }


    public SalesDetails salesDetails(Long salesId) {

        SalesDetails salesDetails = querySalesDao.salesDetails(salesId);

        List<StorageOutItemRecord> itemRecords = new ArrayList<>();
        for (StorageOutItemRecord record : salesDetails.getItemRecords()){
            Integer totalCount = querySalesDao.totalCount(salesId,record.getSkuId());
            Integer finishedCount= querySalesDao.finishedCount(salesId,record.getSkuId());
            record.setTotalCount(totalCount);
            record.setFinishedCount(finishedCount);
            itemRecords.add(record);
        }

        salesDetails.setItemRecords(itemRecords);
        JSONObject details = JSON.parseObject(JSON.toJSONString(salesDetails));

        List<StorageOut> outs = outMapper.selectList(new EntityWrapper<StorageOut>().eq("sales_id", salesDetails.getId()));

        List<StorageOutRecord> records = new ArrayList<>();

        if (outs != null || outs.size() > 0) {
            for (StorageOut out : outs){

                StorageOutRecord searchRecords = querySalesDao.storagesOutDetails(out.getId());
                records.add(searchRecords);
            }

        }

        details.put("storageOutItemRecords",records);

        SalesDetails result = JSON.parseObject(JSON.toJSONString(details),SalesDetails.class);

        return result;
    }

    @Transactional
    public Integer deleteSales(Long id){

        Integer result = 0;
        result += outItemMapper.delete(new EntityWrapper<StorageOutItem>().eq("storage_out_id",id).eq("type","CustomerStorageOut"));
        List<StorageOut> outs = outMapper.selectList(new EntityWrapper<StorageOut>().eq("sales_id", id));
        for (StorageOut out : outs){
            result += outItemMapper.delete(new EntityWrapper<StorageOutItem>().eq("storage_out_id",out.getId()).eq("type","Others"));
            result += outMapper.deleteById(out.getId());
        }

        return result;

    }


    @Override
    public Integer bulkDelete(Ids ids) {
        Integer success = 0;
        for (Long id : ids.getIds()) {
            success += salesMapper.deleteById(id);
        }
        return success;
    }
}
