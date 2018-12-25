package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.sku.services.persistence.dao.SkuPriceHistoryMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDProcurementService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDProcurementServiceImpl;
import com.jfeat.am.module.warehouse.services.definition.ProcurementStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryInventoryDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryProcurementDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryRefundDao;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.service.ProcurementService;
import com.jfeat.am.module.warehouse.services.domain.service.StorageInService;
import com.jfeat.am.module.warehouse.services.persistence.dao.*;
import com.jfeat.am.module.warehouse.services.persistence.model.*;
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
@Service("procurementService")
public class ProcurementServiceImpl extends CRUDProcurementServiceImpl implements ProcurementService {

    @Resource
    StorageInService storageInService;
    @Resource
    CRUDProcurementService procurementService;
    @Resource
    ProcurementMapper procurementMapper;
    @Resource
    StorageInItemMapper storageInItemMapper;
    @Resource
    StorageInMapper storageInMapper;
    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    CRUDStorageInService crudStorageInService;
    @Resource
    SkuProductMapper skuProductMapper;
    @Resource
    SuppliersMapper suppliersMapper;
    @Resource
    QueryProcurementDao queryProcurementDao;
    @Resource
    SkuPriceHistoryMapper skuPriceHistoryMapper;
    @Resource
    QueryRefundDao queryRefundDao;
    @Resource
    QueryInventoryDao queryInventoryDao;

    /**
     * 重构 procurement 问题
     */
    @Transactional
    public Integer addProcurement(Long userId, ProcurementModel model) {

        int affected = 0;
        if (model.getItems() == null || model.getItems().size() == 0) {
            throw new BusinessException(5000, "请先选择需要采购的商品！");
        }
        BigDecimal totalSpend = BigDecimal.valueOf(0);
        model.setOriginatorId(userId);
        model.setTransactionTime(new Date());
        model.setProcureStatus(ProcurementStatus.Draft.toString());
        for (StorageInItem item : model.getItems()) {
            BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
            sum = sum.multiply(item.getTransactionSkuPrice());
            totalSpend = totalSpend.add(sum);
        }
        model.setProcurementTotal(totalSpend);
        affected += procurementMapper.insert(model);

        for (StorageInItem item : model.getItems()) {
            if (item.getTransactionQuantities() == 0) {
                throw new BusinessException(4501, "采购数量不能为0，请重新输入！");
            }
            item.setRelationCode(model.getProcurementCode());
            item.setStorageInId(model.getId());
            item.setType(TransactionType.Procurement.toString());
            affected += storageInItemMapper.insert(item);
        }
        return affected;
    }


    /**
     *  提交并审核
     * */
    @Transactional
    public Integer updateProcurement(Long userId, Long procurementId, ProcurementModel model) {
        int affected = 0;

        Procurement procurement = procurementMapper.selectById(procurementId);
        // 等待入库的情况下才能执行更新的操作
        if (procurement.getProcureStatus().compareTo(ProcurementStatus.Draft.toString()) == 0) {
            model.setId(procurementId);
            model.setTransactionTime(new Date());
            model.setProcureStatus(ProcurementStatus.Draft.toString());
            if (model.getItems() == null || model.getItems().size() == 0) {
                throw new BusinessException(5002,"请至少选择一种需要采购的商品");
            } else {
                storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID,procurementId).eq(StorageInItem.TYPE,TransactionType.Procurement.toString()));
                BigDecimal totalSpend = BigDecimal.valueOf(0);
                for (StorageInItem item : model.getItems()) {
                    BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
                    sum = sum.multiply(item.getTransactionSkuPrice());
                    totalSpend = totalSpend.add(sum);
                }
                model.setProcurementTotal(totalSpend);
                for (StorageInItem item : model.getItems()) {
                    item.setRelationCode(procurement.getProcurementCode());
                    item.setStorageInId(procurementId);
                    item.setType(TransactionType.Procurement.toString());
                    affected += storageInItemMapper.insert(item);
                }
                affected += procurementMapper.updateById(model);
            }
            return affected;
        }
        throw new BusinessException(BusinessCode.ErrorStatus);
    }

    /**
     *  提交并审核
     * */
    @Transactional
    public Integer updateAndAuditProcurement(Long userId, Long procurementId, ProcurementModel model) {
        int affected = 0;

        Procurement procurement = procurementMapper.selectById(procurementId);
        // 等待入库的情况下才能执行更新的操作
        if (procurement.getProcureStatus().compareTo(ProcurementStatus.Draft.toString()) == 0) {
            model.setId(procurementId);
            model.setTransactionTime(new Date());
            model.setProcureStatus(ProcurementStatus.Wait_To_Audit.toString());
            if (model.getItems() == null || model.getItems().size() == 0) {
                throw new BusinessException(5002,"请至少选择一种需要采购的商品");
            } else {
                storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID,procurementId).eq(StorageInItem.TYPE,TransactionType.Procurement.toString()));
                BigDecimal totalSpend = BigDecimal.valueOf(0);
                for (StorageInItem item : model.getItems()) {
                    BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
                    sum = sum.multiply(item.getTransactionSkuPrice());
                    totalSpend = totalSpend.add(sum);
                }
                model.setProcurementTotal(totalSpend);
                for (StorageInItem item : model.getItems()) {
                    item.setRelationCode(procurement.getProcurementCode());
                    item.setStorageInId(procurementId);
                    item.setType(TransactionType.Procurement.toString());
                    affected += storageInItemMapper.insert(item);
                }
                affected += procurementMapper.updateById(model);
            }
            return affected;
        }
        throw new BusinessException(BusinessCode.ErrorStatus);
    }



    /**
     * 执行入库 可以多次执行 ，但是不能超出总和
     */
    @Transactional
    public Integer executionStorageIn(Long userId, Long procurementId, ProcurementModel model) {

        int affected = 0;
        int inSuccess = 0;
        // 总需要入库的商品的数量
        int totalCount = queryProcurementDao.totalCount(procurementId);

        Procurement procurement = procurementMapper.selectById(procurementId);

        if (procurement.getProcureStatus().compareTo(ProcurementStatus.Audit_Passed.toString())!=0){
            if (procurement.getProcureStatus().compareTo(ProcurementStatus.SectionStorageIn.toString())!=0){
                throw new BusinessException(5200,"非\"部分入库|审核通过\"状态下无法执行入库操作");
            }
        }else {

        }

        model.setId(procurementId);
        if (model.getItems() != null && model.getItems().size() > 0) {
            // 判断所有的商品是否都已经入库
            StorageInModel in = new StorageInModel();
            in.setOriginatorId(userId);
            in.setStorageInTime(new Date());
            in.setTransactionTime(new Date());

            // field1 去接收最上层的ID  作跳转使用
            in.setField1(procurementId.toString());

            in.setOriginatorName(model.getOriginatorName());
            // 使用field1去接收 warehouseId 字段
            in.setWarehouseId(Long.valueOf(model.getField1()));
            //使用 field 去接收 入库 code
            in.setTransactionCode(model.getField2());
            in.setProcurementId(procurementId);
            in.setTransactionType(TransactionType.Procurement.toString());
            StorageInFilter storageInFilter = new StorageInFilter();

            List<StorageInItem> storageInItems = new ArrayList<>();
            for (StorageInItem item : model.getItems()) {
                if (item.getTransactionQuantities() > 0) {

                    SkuProduct skuProduct = skuProductMapper.selectById(item.getSkuId());
                    item.setType("Others");
                    item.setRelationCode(procurement.getProcurementCode());

                    // 某个 sku 的采购的数量
                    Integer skuProcurementCount = queryProcurementDao.skuProcurementCount(procurementId, item.getSkuId());
                    //某次采购 某个 sku 入库历史数量
                    Integer storageInCount = queryProcurementDao.storageInCount(procurementId, item.getSkuId());
                    if (storageInCount == null) {
                        storageInCount = 0;
                    }
                    if (item.getTransactionQuantities() > (skuProcurementCount - storageInCount)) {
                        throw new BusinessException(4500, "\""+skuProduct.getSkuName()+"\""+"入库数不能大于采购数，请先核对入库数！");
                    }

                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(item.getSkuId());
                    isExistInventory.setWarehouseId(Long.valueOf(model.getField1()));
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    if (originInventory != null) {
                        Integer validSku = originInventory.getValidSku() + item.getTransactionQuantities();
                        // 操作后的 数量
                        item.setAfterTransactionQuantities(validSku);
                        originInventory.setValidSku(validSku);
                        affected += inventoryMapper.updateById(originInventory);

                    } else {
                        // 操作后的 数量
                        item.setAfterTransactionQuantities(item.getTransactionQuantities());
                        isExistInventory.setTransmitQuantities(0);
                        isExistInventory.setAdvanceQuantities(0);
                        isExistInventory.setMinInventory(0);
                        isExistInventory.setValidSku(item.getTransactionQuantities());
                        isExistInventory.setMaxInventory(item.getTransactionQuantities());
                        affected += inventoryMapper.insert(isExistInventory);
                    }
                    item.setTransactionTime(in.getStorageInTime());
                    storageInItems.add(item);
                }else {
                    //while transaction quantities = 0 ,do nothing
                }

            }
            in.setStorageInItems(storageInItems);
            inSuccess = crudStorageInService.createMaster(in, storageInFilter, null, null);
            if (inSuccess > 0) {
                int sectionCount = queryProcurementDao.sectionCount(procurementId);
                //假设入库数量等于需入库的数量，则设定入库完成，不等于则是部分入库
                if (sectionCount == totalCount) {
                    model.setProcureStatus(ProcurementStatus.TotalStorageIn.toString());
                } else {
                    model.setProcureStatus(ProcurementStatus.SectionStorageIn.toString());
                }
            }
        }
        affected += procurementMapper.updateById(model);
        return affected;
    }

    /**
     * 详情
     */
    public ProcurementModel procurementDetails(Long procurementId) {

        Procurement procurement = procurementMapper.selectById(procurementId);

        JSONObject object = JSON.parseObject(JSON.toJSONString(procurement));

        Suppliers suppliers = suppliersMapper.selectById(procurement.getSupplierId());
        object.put("supplierName", suppliers == null ? null : suppliers.getSupplierName());

        //采购的商品
        List<StorageInItem> items = storageInItemMapper.selectList(new EntityWrapper<StorageInItem>()
                .eq(StorageInItem.TYPE, TransactionType.Procurement.toString()).eq(StorageInItem.STORAGE_IN_ID, procurementId));
        //采购的商品

        // 入库历史记录
        List<StorageInItemRecord> procurementItems = new ArrayList<>();
        // 入库历史记录

        //采购的商品
        List<ProcurementItemRecord> records = new ArrayList<>();
        //采购的商品

        if (items != null && items.size() > 0) {
            // 采购的 商品
            for (StorageInItem item : items) {

                int remainderCount = item.getTransactionQuantities();
                int sectionCount = 0;
                int canRefundCount = sectionCount; // ke tui huo shu
                Integer finishedRefundCount = queryRefundDao.finishedRefundCount(item.getSkuId(),procurementId);//tui huo shu

                ProcurementItemRecord record = new ProcurementItemRecord();

                SkuProduct sku = skuProductMapper.selectById(item.getSkuId());

                record.setTotalCount(item.getTransactionQuantities()); // cai gou zong shu
                record.setSkuCode(sku.getSkuCode());
                record.setSkuName(sku.getSkuName());
                record.setSkuBarcode(sku.getBarCode());
                record.setId(item.getId());
                record.setSkuId(item.getSkuId());
                record.setTransactionTime(item.getTransactionTime());
                record.setTransactionQuantities(item.getTransactionQuantities());
                // 使用 t_sku_product 表中的field1 来接收 单个单位，多单位使用多单位表
                record.setSkuUnit(sku.getField1());
                record.setTransactionSkuPrice(item.getTransactionSkuPrice());

                // 入库记录
                List<StorageIn> ins = storageInMapper.selectList(new EntityWrapper<StorageIn>().eq(StorageIn.PROCUREMENT_ID, procurementId)
                        .like(StorageIn.TRANSACTION_TYPE, TransactionType.Procurement.toString()));
                if (ins != null && ins.size() > 0) {
                    // 有入库记录
                    // 查找 入库 记录下已经入库的商品及数量
                    for (StorageIn in : ins) {
                        // 查找是否存在 这个 商品已经入库
                        List<StorageInItem> originItems = queryProcurementDao.originItems(in.getId(), item.getSkuId(), TransactionType.Procurement.toString());
                        if (originItems != null && originItems.size() > 0) {
                            for (StorageInItem originItem : originItems) {


                                // 入库历史记录
                                StorageInItemRecord procurementItem = new StorageInItemRecord();
                                procurementItem.setTransactionName(in.getOriginatorName());
                                procurementItem.setSkuCode(sku.getSkuCode());
                                procurementItem.setSkuName(sku.getSkuName());
                                procurementItem.setSkuBarcode(sku.getBarCode());
                                procurementItem.setId(item.getId());
                                procurementItem.setWarehouseName(queryProcurementDao.warehouseName(in.getWarehouseId()));
                                procurementItem.setStorageInCode(in.getTransactionCode());
                                procurementItem.setStorageInNote(in.getNote());
                                procurementItem.setBuyer(procurement.getBuyer());
                                procurementItem.setProcurementCode(procurement.getProcurementCode());
                                procurementItem.setProcurementDate(procurement.getProcurementTime());
                                // 使用 t_sku_product 表中的field1 来接收 单个单位，多单位使用多单位表
                                procurementItem.setSkuUnit(sku.getField1());
                                procurementItem.setTransactionQuantities(originItem.getTransactionQuantities());
                                procurementItem.setTransactionSkuPrice(originItem.getTransactionSkuPrice());
                                procurementItem.setTransactionTime(originItem.getTransactionTime());
                                procurementItems.add(procurementItem);
                                // 入库历史记录

                                // 入库数 以及 剩余 入库数
                                sectionCount += originItem.getTransactionQuantities();
                                remainderCount = remainderCount - sectionCount;
                                // 入库数 以及 剩余 入库数
                                if (finishedRefundCount==null){
                                    canRefundCount = sectionCount;
                                }else{
                                    canRefundCount=sectionCount-finishedRefundCount;
                                }

                            }

                        }
                    }
                    record.setCanRefundCount(canRefundCount);
                    record.setRemainderCount(remainderCount);
                    record.setSectionInCount(sectionCount);
                    records.add(record);

                } else {
                    // 无入库记录
                    record.setCanRefundCount(canRefundCount);
                    record.setSectionInCount(sectionCount);
                    record.setRemainderCount(remainderCount);
                    records.add(record);
                }
            }

        } else {
            // 无采购的商品

        }
        object.put("inHistories", procurementItems);
        object.put("records", records);
        ProcurementModel model = JSON.parseObject(JSON.toJSONString(object), ProcurementModel.class);

        return model;

    }


    @Transactional
    public Integer deleteProcurement(Long id) {

        int affected = 0;
        Procurement procurement = procurementService.retrieveMaster(id);

        // 先删除 入库的产品
        affected += storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID, procurement.getId()).like(StorageInItem.TYPE, TransactionType.Procurement.toString()));

        List<StorageIn> ins = storageInMapper.selectList(new EntityWrapper<StorageIn>().eq(StorageIn.PROCUREMENT_ID, procurement)
                .eq(StorageIn.TRANSACTION_TYPE, TransactionType.Procurement.toString()));

        if (ins != null && ins.size() > 0) {
            for (StorageIn in : ins) {
                affected += storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID, in.getId()));
                affected += storageInMapper.deleteById(in.getId());
            }

        }
        return procurementService.deleteMaster(id);
    }

    /**
     *  closed procurement
     * */
    public Integer closedProcurment(Long id){

        Procurement procurement = procurementMapper.selectById(id);
        procurement.setProcureStatus(ProcurementStatus.Closed.toString());
        procurement.setId(id);
        return procurementMapper.updateById(procurement);
    }

    /**
     *  wait to audit this procurement
     * */
    @Transactional
    public Integer auditProcurment(Long id){

        Procurement procurement = procurementMapper.selectById(id);
        procurement.setProcureStatus(ProcurementStatus.Wait_To_Audit.toString());
        procurement.setId(id);
        return procurementMapper.updateById(procurement);
    }

    /**
     *  wait to audit passed this procurement
     * */
    @Transactional
    public Integer passedProcurment(Long id){

        Procurement procurement = procurementMapper.selectById(id);
        procurement.setProcureStatus(ProcurementStatus.Audit_Passed.toString());
        procurement.setId(id);
        return procurementMapper.updateById(procurement);
    }
}
