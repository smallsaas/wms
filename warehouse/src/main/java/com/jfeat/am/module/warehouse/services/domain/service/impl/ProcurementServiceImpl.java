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
import com.jfeat.am.module.warehouse.services.definition.StorageInStatus;
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
@Service("procurementService")
public class ProcurementServiceImpl extends CRUDProcurementServiceImpl implements ProcurementService {

    Logger logger = LoggerFactory.getLogger(ProcurementServiceImpl.class);

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
     * 重构 procurement 问题   待审核/审核通过 item type = wait_to_audit 完成 item type = others 关闭 item type = closed
     */
    @Transactional
    public Integer addProcurement(String username, Long userId, ProcurementModel model) {

        int affected = 0;
        if (model.getItems() == null || model.getItems().size() == 0) {
            throw new BusinessException(5000, "请先选择需要采购的商品！");
        }
        BigDecimal totalSpend = BigDecimal.valueOf(0);
        model.setOriginatorId(userId);
        model.setTransactionBy(username);
        model.setTransactionTime(new Date());
        model.setProcureStatus(ProcurementStatus.Draft.toString());

        affected += procurementMapper.insert(model);
        for (StorageInItem item : model.getItems()) {
            if (item.getDemandQuantities() == null) {
                throw new BusinessException(4501, "采购数量不能为0，请重新输入！");
            }
            // 需求数量 同 实际数量
            item.setTransactionQuantities(item.getDemandQuantities());
            item.setRelationCode(model.getProcurementCode());
            item.setStorageInId(model.getId());
            item.setType(TransactionType.Procurement.toString());
            affected += storageInItemMapper.insert(item);

            BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
            sum = sum.multiply(item.getTransactionSkuPrice());
            totalSpend = totalSpend.add(sum);
        }
        model.setProcurementTotal(totalSpend);
        affected += procurementMapper.updateById(model);
        return affected;
    }


    /**
     * 提交并审核
     */
    @Transactional
    public Integer updateProcurement(String username, Long userId, Long procurementId, ProcurementModel model) {
        int affected = 0;

        Procurement procurement = procurementMapper.selectById(procurementId);
        // 等待入库的情况下才能执行更新的操作
        if (procurement.getProcureStatus().compareTo(ProcurementStatus.Draft.toString()) == 0) {
            model.setId(procurementId);
            model.setTransactionTime(new Date());

            model.setOriginatorId(userId);
            model.setTransactionBy(username);

            model.setProcureStatus(ProcurementStatus.Draft.toString());
            if (model.getItems() == null || model.getItems().size() == 0) {
                throw new BusinessException(5002, "请至少选择一种需要采购的商品");
            } else {
                storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID, procurementId).eq(StorageInItem.TYPE, TransactionType.Procurement.toString()));
                BigDecimal totalSpend = BigDecimal.valueOf(0);
                for (StorageInItem item : model.getItems()) {

                    if (item.getDemandQuantities() == null || item.getDemandQuantities() == 0) {
                        throw new BusinessException(4501, "采购数量不能为0，请重新输入！");
                    }
                    // 需求数量 同 实际数量
                    item.setTransactionQuantities(item.getDemandQuantities());
                    item.setRelationCode(procurement.getProcurementCode());
                    item.setStorageInId(procurementId);
                    item.setType(TransactionType.Procurement.toString());
                    affected += storageInItemMapper.insert(item);

                    BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
                    sum = sum.multiply(item.getTransactionSkuPrice());
                    totalSpend = totalSpend.add(sum);
                }
                model.setProcurementTotal(totalSpend);
                affected += procurementMapper.updateById(model);
            }
            return affected;
        }
        throw new BusinessException(BusinessCode.ErrorStatus);
    }

    /**
     * 提交并审核
     */
    @Transactional
    public Integer updateAndAuditProcurement(Long userId, Long procurementId, ProcurementModel model) {
        int affected = 0;

        Procurement procurement = procurementMapper.selectById(procurementId);
        // 等待入库的情况下才能执行更新的操作
        if (procurement.getProcureStatus().compareTo(ProcurementStatus.Draft.toString()) == 0) {
            model.setId(procurementId);
            model.setTransactionTime(new Date());
            model.setProcureStatus(ProcurementStatus.Wait_To_Audit.toString());

            model.setOriginatorId(userId);
            if (model.getItems() == null || model.getItems().size() == 0) {
                throw new BusinessException(5002, "请至少选择一种需要采购的商品");
            } else {
                storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID, procurementId).eq(StorageInItem.TYPE, TransactionType.Procurement.toString()));
                BigDecimal totalSpend = BigDecimal.valueOf(0);
                for (StorageInItem item : model.getItems()) {

                    // 需求数量 同 实际数量
                    item.setTransactionQuantities(item.getDemandQuantities());
                    item.setRelationCode(procurement.getProcurementCode());
                    item.setStorageInId(procurementId);
                    item.setType(TransactionType.Procurement.toString());
                    affected += storageInItemMapper.insert(item);

                    BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
                    sum = sum.multiply(item.getTransactionSkuPrice());
                    totalSpend = totalSpend.add(sum);
                }
                model.setProcurementTotal(totalSpend);
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
    public Integer executionStorageIn(String username, Long userId, Long procurementId, ProcurementModel model) {

        int affected = 0;
        int inSuccess = 0;
        // 总需要入库的商品的数量
        int totalCount = queryProcurementDao.totalCount(procurementId);

        Procurement procurement = procurementMapper.selectById(procurementId);

        if (procurement.getProcureStatus().compareTo(ProcurementStatus.Audit_Passed.toString()) != 0) {
            if (procurement.getProcureStatus().compareTo(ProcurementStatus.SectionStorageIn.toString()) != 0) {
                throw new BusinessException(5200, "非\"部分入库|审核通过\"状态下无法执行入库操作");
            }
        } else {

        }

        model.setId(procurementId);
        if (model.getItems() != null && model.getItems().size() > 0) {
            // 判断所有的商品是否都已经入库
            StorageInModel in = new StorageInModel();
            in.setOriginatorId(userId);
            in.setTransactionBy(username);
            in.setStorageInTime(new Date());
            in.setTransactionTime(new Date());
            // 待审核状态
            in.setStatus(StorageInStatus.Wait_To_Audit.toString());

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
                if (item.getDemandQuantities() > 0) {
                    item.setTransactionQuantities(item.getDemandQuantities());
                    SkuProduct skuProduct = skuProductMapper.selectById(item.getSkuId());
                    item.setType(StorageInStatus.Wait_To_Audit.toString());
                    item.setRelationCode(procurement.getProcurementCode());

                    // 某个 sku 的采购的数量
                    Integer skuProcurementCount = queryProcurementDao.skuProcurementCount(procurementId, item.getSkuId());
                    if (skuProcurementCount == null) {
                        skuProcurementCount = 0;
                    }
                    //某次采购 某个 sku 入库历史数量
                    Integer storageInCount = queryProcurementDao.storageInCount(procurementId, item.getSkuId());
                    if (storageInCount == null) {
                        storageInCount = 0;
                    }
                    // 带审核数
                    Integer storageInAuditCount = queryProcurementDao.storageInAuditCount(procurementId, item.getSkuId());
                    if (storageInAuditCount == null) {
                        storageInAuditCount = 0;
                    }
                    if (storageInCount == null) {
                        storageInCount = 0;
                    }
                    if (item.getTransactionQuantities() > (skuProcurementCount - storageInCount - storageInAuditCount)) {
                        throw new BusinessException(4500, "\"" + skuProduct.getSkuName() + "\"" + "入库数不能大于采购数，请先核对入库数！" + "其中已入库数为" + storageInCount + "，待审核入库数为" + storageInAuditCount);
                    }
                    item.setTransactionTime(in.getStorageInTime());
                    storageInItems.add(item);
                } else {
                    //while transaction quantities = 0 ,do nothing
                }
            }
            in.setStorageInItems(storageInItems);
            affected += crudStorageInService.createMaster(in, storageInFilter, null, null);
        }
        affected += procurementMapper.updateById(model);
        return affected;
    }

    /*
     * 审核采购的执行入库
     * */
    @Transactional
    public Integer auditPass(Long id, StorageInModel model) {
        StorageIn in = storageInMapper.selectById(id);
        if (in == null) {
            throw new BusinessException(5300, "入库单异常，请核对！");
        }
        if ((in.getProcurementId() == null || in.getProcurementId() < 0) && (in.getTransactionType().compareTo(TransactionType.Procurement.toString()) == 0)) {
            throw new BusinessException(5300, "非采购入库的流水单号!");
        }
        Procurement procurement = procurementMapper.selectById(in.getProcurementId());
        if (procurement == null) {
            throw new BusinessException(5300, "采购单信息丢失，请核对！");
        }
        if (in.getStatus().compareTo(StorageInStatus.Wait_To_Audit.toString()) != 0) {
            throw new BusinessException(5300, "状态错误，非\"待审核\"状态无法进行审核");
        }
        // 总需要入库的商品的数量
        int totalCount = queryProcurementDao.totalCount(in.getProcurementId());
        // 先delete 掉 记录，避免索引的时候出错
        storageInItemMapper.delete(new EntityWrapper<StorageInItem>()
                .eq(StorageInItem.STORAGE_IN_ID, id)
                .eq(StorageInItem.TYPE, StorageInStatus.Wait_To_Audit.toString()));

        for (StorageInItem item : model.getStorageInItems()) {
            if (item.getTransactionQuantities() > 0) {
                // 审核通过并修改之后，会是实际的数量
                item.setDemandQuantities(item.getTransactionQuantities());
                SkuProduct skuProduct = skuProductMapper.selectById(item.getSkuId());
                item.setType(StorageInStatus.Wait_To_Audit.toString());
                item.setRelationCode(procurement.getProcurementCode());
                // 某个 sku 的采购的数量
                Integer skuProcurementCount = queryProcurementDao.skuProcurementCount(in.getProcurementId(), item.getSkuId());
                if (skuProcurementCount == null) {
                    skuProcurementCount = 0;
                }
                //某次采购 某个 sku 入库历史数量
                Integer storageInCount = queryProcurementDao.storageInCount(in.getProcurementId(), item.getSkuId());
                Integer storageInAuditCount = queryProcurementDao.storageInAuditCount(in.getProcurementId(), item.getSkuId());
                if (storageInCount == null) {
                    storageInCount = 0;
                }
                if (storageInAuditCount == null) {
                    storageInAuditCount = 0;
                }
                if (item.getTransactionQuantities() > (skuProcurementCount - storageInCount - storageInAuditCount)) {
                    throw new BusinessException(4500, "\"" + skuProduct.getSkuName() + "\"" + "入库数不能大于采购数，请先核对入库数！" + "其中已入库数为" + storageInCount + "，待审核入库数为" + storageInAuditCount);
                }
                item.setTransactionTime(in.getStorageInTime());
                storageInItemMapper.insert(item);
            } else {
                //while transaction quantities = 0 ,do nothing
            }
        }
        in.setStatus(StorageInStatus.Audit_Passed.toString());
        in.setId(id);
        return storageInMapper.updateById(in);
    }

    @Transactional
    public Integer executionProcurementStorageIn(Long id) {

        int count = 0;
        int total = 0;
        StorageIn in = storageInMapper.selectById(id);
        if (in == null) {
            throw new BusinessException(5300, "入库单异常，请核对！");
        }
        if ((in.getProcurementId() == null || in.getProcurementId() < 0) && (in.getTransactionType().compareTo(TransactionType.Procurement.toString()) == 0)) {
            throw new BusinessException(5300, "非采购入库的流水单号!");
        }
        Procurement procurement = procurementMapper.selectById(in.getProcurementId());
        if (procurement == null) {
            throw new BusinessException(5300, "采购单信息丢失，请核对！");
        }
        if (in.getStatus().compareTo(StorageInStatus.Audit_Passed.toString()) != 0) {
            throw new BusinessException(5300, "状态错误，非\"审核通过\"状态无法进行审核");
        }

        List<StorageInItem> items = storageInItemMapper.selectList(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID, id)
                .eq(StorageInItem.TYPE, StorageInStatus.Wait_To_Audit.toString()));

        for (StorageInItem item : items) {
            // 某个 sku 的采购的数量
            Integer skuProcurementCount = queryProcurementDao.skuProcurementCount(procurement.getId(), item.getSkuId());
            if (skuProcurementCount == null) {
                skuProcurementCount = 0;
            }
            total += skuProcurementCount;

            //某次采购 某个 sku 入库历史数量
            Integer storageInCount = queryProcurementDao.storageInCount(procurement.getId(), item.getSkuId());
            if (storageInCount == null) {
                storageInCount = 0;
            }

            count += storageInCount;


            //执行库存修改
            Inventory isExistInventory = new Inventory();
            isExistInventory.setSkuId(item.getSkuId());
            isExistInventory.setWarehouseId(in.getWarehouseId());
            Inventory originInventory = inventoryMapper.selectOne(isExistInventory);


            if (originInventory != null) {
                Integer validSku = originInventory.getValidSku() + item.getTransactionQuantities();
                // 操作后的 数量
                item.setAfterTransactionQuantities(validSku);
                originInventory.setValidSku(validSku);
                inventoryMapper.updateById(originInventory);

            } else {
                // 操作后的 数量
                item.setAfterTransactionQuantities(item.getTransactionQuantities());
                isExistInventory.setTransmitQuantities(0);
                isExistInventory.setAdvanceQuantities(0);
                isExistInventory.setMinInventory(0);
                isExistInventory.setValidSku(item.getTransactionQuantities());
                isExistInventory.setMaxInventory(item.getTransactionQuantities());
                inventoryMapper.insert(isExistInventory);
            }
            // 原来的 + 刚刚入库的
            count += item.getTransactionQuantities();
            item.setType("Others");
            storageInItemMapper.updateById(item);

        }
        in.setStatus(StorageInStatus.Done.toString());
        in.setId(id);
        //假设入库数量等于需入库的数量，则设定入库完成，不等于则是部分入库  // 与总数对比确定最终的 采购单的状态
        if (count == total) {
            procurement.setProcureStatus(ProcurementStatus.TotalStorageIn.toString());
            procurementMapper.updateById(procurement);
        } else {
            procurement.setProcureStatus(ProcurementStatus.SectionStorageIn.toString());
            procurementMapper.updateById(procurement);
        }
        return storageInMapper.updateById(in);
    }

    /*
     * 审核拒绝采购的执行入库---直接将入库单设置为 关闭状态
     * */
    @Transactional
    public Integer auditReject(Long id) {
        StorageIn in = storageInMapper.selectById(id);
        if ((in.getProcurementId() == null || in.getProcurementId() < 0) && (in.getTransactionType().compareTo(TransactionType.Procurement.toString()) == 0)) {
            throw new BusinessException(5300, "非采购入库的流水单号!");
        }
        // 采购入库待审核的商品
        List<StorageInItem> items = storageInItemMapper.selectList(new EntityWrapper<StorageInItem>()
                .eq(StorageInItem.STORAGE_IN_ID, id)
                .eq(StorageInItem.TYPE, StorageInStatus.Wait_To_Audit.toString()));
        // 如果不通过，将 item 的type 设置为 CLosed
        for (StorageInItem item : items) {
            item.setType(StorageInStatus.Closed.toString());
            storageInItemMapper.updateById(item);
        }
        in.setStatus(StorageInStatus.Closed.toString());
        in.setId(id);
        return storageInMapper.updateById(in);
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

        // 入库记录
        List<StorageIn> ins = storageInMapper.selectList(new EntityWrapper<StorageIn>().eq(StorageIn.PROCUREMENT_ID, procurementId)
                .eq(StorageIn.TRANSACTION_TYPE, TransactionType.Procurement.toString()));

        // 入库历史记录
        List<StorageInItemRecord> procurementItems = new ArrayList<>();

        //采购的商品
        List<ProcurementItemRecord> records = new ArrayList<>();


        logger.debug("items != null && items.size()>0");
        if (items != null && items.size() > 0) {
            // 采购的 商品
            for (StorageInItem item : items) {

                int remainderCount = item.getTransactionQuantities();
                // 已经 入库数
                Integer sectionCount = queryProcurementDao.storageInCount(procurementId, item.getSkuId());
                if (sectionCount == null) {
                    sectionCount = 0;
                }
                // 已经退货总数
                Integer finishedRefundCount = queryRefundDao.finishedRefundCount(item.getSkuId(), procurementId);//tui huo shu
                if (finishedRefundCount == null) {
                    finishedRefundCount = 0;
                }
                int canRefundCount= sectionCount - finishedRefundCount;// ke tui huo shu
                // 待审核数
                Integer storageInAuditCount = queryProcurementDao.storageInAuditCount(procurementId, item.getSkuId());
                if (storageInAuditCount == null) {
                    storageInAuditCount = 0;
                }
                ProcurementItemRecord record = new ProcurementItemRecord();

                SkuProduct sku = skuProductMapper.selectById(item.getSkuId());

                record.setTotalCount(item.getTransactionQuantities()); // cai gou zong shu
                // 剩余 入库数
                remainderCount = record.getTotalCount() - sectionCount;

                record.setDemandQuantities(item.getDemandQuantities());
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

                logger.debug("ins != null && ins.size() > 0");
                if (ins != null && ins.size() > 0) {
                    // 有入库记录
                    // 查找 入库 记录下已经入库的商品及数量
                    for (StorageIn in : ins) {
                        if ((in.getProcurementId() != null)) {
                            // 查找是否存在 这个 商品已经入库
                            List<StorageInItem> originItems = queryProcurementDao.originItems(in.getId(), item.getSkuId(), TransactionType.Procurement.toString());
                            logger.debug("originItems != null && originItems.size() > 0");
                            if (originItems != null && originItems.size() > 0) {
                                for (StorageInItem originItem : originItems) {
                                    logger.debug("入库历史记录:  " + JSON.toJSONString(originItem));
                                    // 入库历史记录
                                    StorageInItemRecord procurementItem = new StorageInItemRecord();
                                    procurementItem.setStorageInStatus(in.getStatus());
                                    procurementItem.setTransactionName(in.getOriginatorName());
                                    procurementItem.setSkuCode(sku.getSkuCode());
                                    procurementItem.setSkuName(sku.getSkuName());
                                    procurementItem.setSkuBarcode(sku.getBarCode());
                                    procurementItem.setId(item.getId());
                                    procurementItem.setWarehouseName(queryProcurementDao.warehouseName(in.getWarehouseId()) == null ? null : queryProcurementDao.warehouseName(in.getWarehouseId()));
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
                                }
                            }
                        } else {

                        }
                    }
                    record.setCanRefundCount(canRefundCount);
                    record.setRemainderCount(remainderCount);
                    record.setSectionInCount(sectionCount);
                    record.setAuditCount(storageInAuditCount);
                    records.add(record);

                    logger.debug("有入库记录: records.add(record);");

                } else {
                    // 无入库记录
                    record.setCanRefundCount(canRefundCount);
                    record.setSectionInCount(sectionCount);
                    record.setRemainderCount(remainderCount);
                    record.setAuditCount(storageInAuditCount);
                    records.add(record);
                    logger.debug("无入库记录: records.add(record);");
                }
            }

        } else {
        }

        object.put("inHistories", procurementItems);
        object.put("items", records);
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
     * closed procurement
     */
    @Transactional
    public Integer closedProcurement(Long id, ProcurementModel model) {

        int affected = 0;

        Procurement procurement = procurementMapper.selectById(id);
        if (procurement == null) {
            throw new BusinessException(BusinessCode.FileNotFound);
        }
        if (procurement.getProcureStatus().compareTo(ProcurementStatus.Wait_To_Audit.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        model.setProcureStatus(ProcurementStatus.Closed.toString());
        model.setId(id);
        affected += procurementMapper.updateById(model);

        return affected;
    }

    /**
     * wait to audit this procurement
     */
    @Transactional
    public Integer auditProcurement(Long id) {

        Procurement procurement = procurementMapper.selectById(id);
        if (procurement == null) {
            throw new BusinessException(BusinessCode.FileNotFound);
        }
        if (procurement.getProcureStatus().compareTo(ProcurementStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        procurement.setProcureStatus(ProcurementStatus.Wait_To_Audit.toString());
        procurement.setId(id);
        return procurementMapper.updateById(procurement);
    }

    /**
     * wait to audit passed this procurement
     */
    @Transactional
    public Integer passedProcurement(Long id, ProcurementModel model) {

        int affected = 0;

        Procurement procurement = procurementMapper.selectById(id);
        if (procurement == null) {
            throw new BusinessException(BusinessCode.FileNotFound);
        }
        if (procurement.getProcureStatus().compareTo(ProcurementStatus.Wait_To_Audit.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        model.setProcureStatus(ProcurementStatus.Audit_Passed.toString());
        model.setId(id);

        BigDecimal totalSpend = BigDecimal.valueOf(0);
        for (StorageInItem item : model.getItems()) {
            // 更新实际数量
            item.setType(TransactionType.Procurement.toString());
            affected += storageInItemMapper.updateById(item);
            BigDecimal sum = new BigDecimal(item.getTransactionQuantities());
            sum = sum.multiply(item.getTransactionSkuPrice());
            totalSpend = totalSpend.add(sum);
        }
        model.setProcurementTotal(totalSpend);
        affected += procurementMapper.updateById(model);
        return affected;
    }
}
