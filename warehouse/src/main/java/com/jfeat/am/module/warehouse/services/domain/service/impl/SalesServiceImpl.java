package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.AmApplication;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDSalesServiceImpl;
import com.jfeat.am.module.warehouse.services.definition.ItemEnumType;
import com.jfeat.am.module.warehouse.services.definition.SalesStatus;
import com.jfeat.am.module.warehouse.services.definition.StorageOutStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.dao.QuerySalesDao;
import com.jfeat.am.module.warehouse.services.domain.model.SalesDetails;
import com.jfeat.am.module.warehouse.services.domain.model.SalesModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutRecord;
import com.jfeat.am.module.warehouse.services.domain.service.SalesService;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.SalesMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutItemMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.Sales;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOut;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    protected final static Logger logger = LoggerFactory.getLogger(AmApplication.class);

    @Transactional
    public Integer createSales(Long userId, SalesModel model) {
        int affected = 0;
        int totalCount = 0;
        if (model.getOutItems() == null || model.getOutItems().size() <= 0) {
            throw new BusinessException(5000, "请先选择需要出库的商品！");
        }
        BigDecimal totalSpend = BigDecimal.valueOf(0);
        model.setOriginatorId(userId);
        model.setTransactionTime(new Date());
        model.setSalesStatus(SalesStatus.Draft.toString());
        for (StorageOutItem item : model.getOutItems()) {
            if (item.getTransactionQuantities() == 0) {
            } else {
                BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
                sum = sum.multiply(item.getTransactionSkuPrice());
                totalSpend = totalSpend.add(sum);
                totalCount += item.getTransactionQuantities();
            }
        }
        model.setSalesTotal(totalSpend);
        model.setTotalCount(totalCount);
        affected += salesMapper.insert(model);
        for (StorageOutItem item : model.getOutItems()) {
            item.setDemandQuantities(item.getTransactionQuantities());
            item.setRelationCode(model.getSalesCode());
            item.setStorageOutId(model.getId());
            item.setType(ItemEnumType.TRADER.toString());
            affected += outItemMapper.insert(item);
        }
        return affected;
    }


    @Transactional
    public Integer updateSales(Long userId, Long salesId, SalesModel model) {
        int affected = 0;
        Sales sales = salesMapper.selectById(salesId);
        if (sales == null) {
            throw new BusinessException(5500, "无ID为" + salesId + "的分销商出库订单");
        }
        // 草稿的情况下才能执行更新的操作
        if (sales.getSalesStatus().compareTo(SalesStatus.Draft.toString()) == 0) {
            model.setId(salesId);
            model.setTransactionTime(new Date());
            int totalCount = 0;
            BigDecimal totalSpend = BigDecimal.valueOf(0);
            if (model.getOutItems() == null || model.getOutItems().size() == 0) {
                throw new BusinessException(5002, "请至少选择一种需要出库的商品");
            } else {
                // 执行删除，防止重复插入
                outItemMapper.delete(new EntityWrapper<StorageOutItem>()
                        .eq(StorageOutItem.STORAGE_OUT_ID, salesId)
                        .eq(StorageOutItem.TYPE, ItemEnumType.TRADER));
                for (StorageOutItem item : model.getOutItems()) {
                    if (item.getTransactionQuantities() > 0) {
                        item.setDemandQuantities(item.getTransactionQuantities());
                        item.setRelationCode(sales.getSalesCode());
                        item.setStorageOutId(sales.getId());
                        item.setType(ItemEnumType.TRADER.toString());
                        affected += outItemMapper.insert(item);
                        BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
                        sum = sum.multiply(item.getTransactionSkuPrice());
                        totalSpend = totalSpend.add(sum);
                        totalCount += item.getTransactionQuantities();
                    } else {
                        // 需求数量为 0 的时候，不作任何的处理
                    }
                }
            }
            model.setTotalCount(totalCount);
            model.setSalesTotal(totalSpend);
            model.setOriginatorName(sales.getOriginatorName());
            model.setOriginatorId(sales.getOriginatorId());
            affected += salesMapper.updateById(model);
            return affected;
        }
        throw new BusinessException(BusinessCode.ErrorStatus);
    }

    @Transactional
    public Integer updateAndCommitSales(Long userId, Long salesId, SalesModel model) {
        int affected = 0;
        Sales sales = salesMapper.selectById(salesId);
        if (sales == null) {
            throw new BusinessException(5500, "无ID为" + salesId + "的分销商出库订单");
        }
        // 等待入库的情况下才能执行更新的操作
        if (sales.getSalesStatus().compareTo(SalesStatus.Draft.toString()) == 0) {
            model.setId(sales.getId());
            model.setTransactionTime(new Date());
            int totalCount = 0;
            BigDecimal totalSpend = BigDecimal.valueOf(0);
            if (model.getOutItems() == null || model.getOutItems().size() == 0) {
                throw new BusinessException(5002, "请至少选择一种需要出库的商品");
            } else {
                outItemMapper.delete(new EntityWrapper<StorageOutItem>()
                        .eq(StorageOutItem.STORAGE_OUT_ID, salesId)
                        .eq(StorageOutItem.TYPE, ItemEnumType.TRADER));
                for (StorageOutItem item : model.getOutItems()) {
                    if (item.getTransactionQuantities() > 0) {
                        item.setDemandQuantities(item.getTransactionQuantities());
                        item.setRelationCode(model.getSalesCode());
                        item.setStorageOutId(model.getId());
                        item.setType(ItemEnumType.TRADER.toString());
                        affected += outItemMapper.insert(item);

                        BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
                        sum = sum.multiply(item.getTransactionSkuPrice());
                        totalSpend = totalSpend.add(sum);
                        totalCount += item.getTransactionQuantities();
                    } else {
                        // 需求数量为 0 的时候，不作任何的处理
                    }
                }
            }
            model.setTotalCount(totalCount);
            model.setSalesTotal(totalSpend);
            model.setSalesStatus(SalesStatus.Wait_To_Audit.toString());
            model.setId(salesId);
            model.setOriginatorName(sales.getOriginatorName());
            model.setOriginatorId(sales.getOriginatorId());
            affected += salesMapper.updateById(model);
            return affected;
        }
        throw new BusinessException(BusinessCode.ErrorStatus);
    }


    @Transactional
    public Integer executionStorageOut(Long userId, Long salesId, SalesModel model) {
        int affected = 0;
        Sales sales = salesMapper.selectById(salesId);
        if (sales.getSalesStatus().compareTo(SalesStatus.WaitForStorageOut.toString()) != 0
                && sales.getSalesStatus().compareTo(SalesStatus.SectionStorageOut.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        model.setId(salesId);
        if (model.getOutItems() != null && model.getOutItems().size() > 0) {
            List<String> noInventory = new ArrayList<>();
            // 判断所有的商品是否都已经入库
            StorageOut out = new StorageOut();
            // field1 去接收最上层的ID  作跳转使用
            out.setField1(salesId.toString());
            out.setOriginatorId(userId);
            out.setStorageOutTime(new Date());
            out.setTransactionTime(new Date());
            out.setOriginatorName(model.getOriginatorName());
            // 使用field1去接收 warehouseId 字段
            out.setWarehouseId(Long.valueOf(model.getField1()));
            //使用 field2 去接收 出库 code
            out.setTransactionCode(model.getField2().replace("IN","OUT"));
            out.setSalesId(salesId);
            out.setTransactionType(TransactionType.CustomerStorageOut.toString());
            out.setStatus(StorageOutStatus.Wait_To_Audit.toString());
            // field1 去接收最上层的ID  作跳转使用
            out.setField1(salesId.toString());
            outMapper.insert(out);
            for (StorageOutItem item : model.getOutItems()) {
                if (item.getTransactionQuantities() > 0) {
                    SkuProduct skuProduct = skuProductMapper.selectById(item.getSkuId());
                    // 某个 sku 的 sales 的数量
                    Integer skuSalesCount = querySalesDao.totalSkuCount(salesId, item.getSkuId());
                    //某个 sku 入库历史数量
                    Integer finishedCount = querySalesDao.finishedCount(salesId, item.getSkuId());
                    if (finishedCount == null) {
                        finishedCount = 0;
                    }
                    // 已经完成审核但未出库的出库数
                    Integer auditStorageOutPass = querySalesDao.auditStorageOutPass(salesId, item.getSkuId());
                    if (auditStorageOutPass == null) {
                        auditStorageOutPass = 0;
                    }
                    //待审核的出库数
                    Integer auditStorageOutCount = querySalesDao.auditStorageOutCount(salesId, item.getSkuId());
                    if (auditStorageOutCount == null) {
                        auditStorageOutCount = 0;
                    }
                    if (item.getTransactionQuantities() >
                            (skuSalesCount
                                    - finishedCount
                                    - auditStorageOutPass
                                    - auditStorageOutCount)) {
                        throw new BusinessException(4500,
                                "\"请先对已提交的出库单执行\"出库\"或\"审核\"操作！"
                                        +
                                        "\""
                                        + skuProduct.getSkuName()
                                        + "\""
                                        + "出库数不能大于订单数，请先核对出库数！"
                                        + "其中总数为:"
                                        + skuSalesCount
                                        + "，已出库数为:"
                                        + finishedCount
                                        + "，审核通过但未出库数量为:"
                                        + auditStorageOutPass
                                        + "，待审核出库总数为:"
                                        + auditStorageOutCount
                                        + "!");
                    }

                    item.setStorageOutId(out.getId());
                    item.setDemandQuantities(item.getTransactionQuantities());
                    item.setType(ItemEnumType.STORAGEOUT.toString());
                    item.setTransactionTime(out.getStorageOutTime());
                    item.setRelationCode(sales.getSalesCode());
                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(item.getSkuId());
                    isExistInventory.setWarehouseId(Long.valueOf(model.getField1()));
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    if (originInventory != null) {
                        logger.info("### 出库前的数量：###" + originInventory.getValidSku());
                        if (originInventory.getValidSku() < item.getTransactionQuantities()) {
                            throw new BusinessException(5500, skuProduct.getSkuName()
                                    + "数量不足，" + "出库数"
                                    + item.getTransactionQuantities()
                                    + "大于可用库存数"
                                    + originInventory.getValidSku());
                        }

                        /*Integer afterCount = originInventory.getValidSku() - item.getTransactionQuantities();
                        logger.info("### 出库后的数量：###" + originInventory.getValidSku());
                        originInventory.setValidSku(afterCount);
                        inventoryMapper.updateById(originInventory);// 更新库存
                        item.setAfterTransactionQuantities(afterCount);
                        logger.info("### 出库子项JSON数据：###" + JSON.toJSONString(item));*/
                        outItemMapper.insert(item);
                    } else {
                        noInventory.add(skuProduct.getSkuName() + ":" + skuProduct.getBarCode() + "\n"
                        );
                    }
                } else {
                    //while transaction quantities = 0 ,do nothing
                }
            }
            if (noInventory.size() > 0) {
                throw new BusinessException(4501, "已选中的仓库中无以下商品" + "\"" + noInventory + "\"");
            }
        }
        //affected += salesMapper.updateById(model);
        return affected;
    }

    @Transactional
    public Integer auditPass(Long salesId, SalesModel model) {
        Integer affected = 0;
        int totalCount = 0;
        BigDecimal totalSpend = BigDecimal.valueOf(0);
        Sales sales = salesMapper.selectById(salesId);
        if (sales == null) {
            throw new BusinessException(5500, "无ID为" + salesId + "的分销商出库订单");
        }
        if (sales.getSalesStatus().compareTo(SalesStatus.Wait_To_Audit.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        for (StorageOutItem item : model.getOutItems()) {
            item.setType(ItemEnumType.TRADER.toString());
            totalCount += item.getTransactionQuantities();
            affected += outItemMapper.updateById(item);
            BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
            sum = sum.multiply(item.getTransactionSkuPrice());
            totalSpend = totalSpend.add(sum);
        }
        model.setTotalCount(totalCount);
        model.setSalesStatus(SalesStatus.WaitForStorageOut.toString());
        model.setId(salesId);
        model.setOriginatorName(sales.getOriginatorName());
        model.setOriginatorId(sales.getOriginatorId());
        affected += salesMapper.updateById(model);
        return affected;
    }


    public SalesDetails salesDetails(Long salesId) {
        SalesDetails salesDetails = querySalesDao.salesDetails(salesId);
        if (salesDetails == null) {
            throw new BusinessException(5500, "单据为空！");
        }
        List<StorageOutItemRecord> itemRecords = new ArrayList<>();
        for (StorageOutItemRecord record : salesDetails.getOutItems()) {
            Integer totalCount = querySalesDao.totalSkuCount(salesId, record.getSkuId());
            Integer finishedCount = querySalesDao.finishedCount(salesId, record.getSkuId());
            if (finishedCount == null) {
                finishedCount = 0;
            }
            record.setTotalCount(totalCount);
            record.setFinishedCount(finishedCount);
            itemRecords.add(record);
        }
        salesDetails.setOutItems(itemRecords);
        JSONObject details = JSON.parseObject(JSON.toJSONString(salesDetails));
        List<StorageOut> outs = outMapper.selectList(new EntityWrapper<StorageOut>()
                .eq("sales_id", salesDetails.getId())
                .eq(StorageOut.TRANSACTION_TYPE, TransactionType.CustomerStorageOut));
        List<StorageOutRecord> records = new ArrayList<>();
        if (outs != null || outs.size() > 0) {
            for (StorageOut out : outs) {
                StorageOutRecord searchRecords = querySalesDao.storagesOutDetails(out.getId());
                records.add(searchRecords);
            }
        }
        List<StorageOutItem> outItems = new ArrayList<>();
        outItems.addAll(itemRecords);
        details.put("storageOutItemRecords", records);
        details.put("outItems", outItems);
        SalesDetails result = JSON.parseObject(JSON.toJSONString(details), SalesDetails.class);
        return result;
    }

    @Transactional
    public Integer deleteSales(Long id) {
        Integer result = 0;
        result += outItemMapper.delete(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, id)
                .eq(StorageOutItem.TYPE, ItemEnumType.TRADER));
        List<StorageOut> outs = outMapper.selectList(new EntityWrapper<StorageOut>()
                .eq("sales_id", id)
                .eq(StorageOut.TRANSACTION_TYPE, TransactionType.CustomerStorageOut));
        for (StorageOut out : outs) {
            result += outItemMapper.delete(new EntityWrapper<StorageOutItem>()
                    .eq(StorageOutItem.STORAGE_OUT_ID, out.getId())
                    .eq(StorageOutItem.TYPE, ItemEnumType.STORAGEOUT));
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
