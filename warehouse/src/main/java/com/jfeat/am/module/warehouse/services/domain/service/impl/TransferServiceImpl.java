package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageOutFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDTransferService;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.definition.TransferStatus;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryInventoryDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryTransferDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryWarehouseDao;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;
import com.jfeat.am.module.warehouse.services.domain.model.TransferModel;
import com.jfeat.am.module.warehouse.services.domain.service.TransferService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDTransferServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.*;
import com.jfeat.am.module.warehouse.services.persistence.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("crudTransferService")
public class TransferServiceImpl extends CRUDTransferServiceImpl implements TransferService {

    @Resource
    CRUDStorageInService crudStorageInService;
    @Resource
    CRUDStorageOutService crudStorageOutService;
    @Resource
    CRUDTransferService crudTransferService;
    @Resource
    TransferMapper transferMapper;
    @Resource
    StorageInItemMapper storageInItemMapper;
    @Resource
    StorageInMapper storageInMapper;
    @Resource
    StorageOutItemMapper storageOutItemMapper;
    @Resource
    StorageOutMapper storageOutMapper;
    @Resource
    InventoryMapper inventoryMapper;

    @Resource
    QueryTransferDao queryTransferDao;
    @Resource
    QueryWarehouseDao queryWarehouseDao;
    @Resource
    SkuProductMapper skuProductMapper;
    @Resource
    QueryInventoryDao queryInventoryDao;

    protected static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    @Transactional
    public Integer draftOutItem(List<StorageOutItem> items, Long fromWarehouseId, Long transferId) {
        int affected = 0;

        if (items != null && items.size() > 0) {

            for (StorageOutItem outItem : items) {
                SkuProduct skuProduct = skuProductMapper.selectById(outItem.getSkuId());

                if (outItem.getTransactionQuantities() > 0) {

                    // search sku inventories count
                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(outItem.getSkuId());
                    isExistInventory.setWarehouseId(fromWarehouseId);
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);

                    if (originInventory != null) {
                        if (outItem.getTransactionQuantities() > originInventory.getValidSku()) {
                            throw new BusinessException(4050, "\"" + skuProduct.getSkuName() + ":" + skuProduct.getBarCode() + "\""
                                    + "库存不足," + "现有库存" + originInventory.getValidSku()
                                    + "小于出库量" + outItem.getTransactionQuantities());
                        } else {
                            outItem.setStorageOutId(transferId);
                            outItem.setType(TransactionType.TransferOut.toString());
                            outItem.setRelationCode(transferId.toString());
                            affected += storageOutItemMapper.insert(outItem);
                        }
                    } else {
                        throw new BusinessException(4055, "出库仓库无商品:" + "\"" + skuProduct.getSkuName() + ":" + skuProduct.getBarCode() + "\"" + "库存，请重新核对");
                    }

                } else {
                    throw new BusinessException(5000, "提交失败，" + "\"" + skuProduct.getSkuName() + "\"" + "商品调拨数量不能为0");
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }

        return affected;
    }


    /**
     * DraftTransfer
     */
    @Transactional
    public Integer draftTransfer(TransferModel model, Long userId, String userName) {

        int affected = 0;
        if (model.getTransferTime() == null) {
            model.setTransferTime(new Date());
        }

        if (model.getFromWarehouseId().compareTo(model.getToWarehouseId()) == 0) {

            throw new BusinessException(4100, "ERROR DATA" + "\"数据错误，调入|调出仓库不能相同\"");
        }
        model.setOriginatorId(userId);
        model.setOriginatorName(userName);
        model.setStatus(TransferStatus.Draft.toString());
        affected += transferMapper.insert(model);

        affected += draftOutItem(model.getOutItems(), model.getFromWarehouseId(), model.getId());

        return affected;
    }

    @Transactional
    public Integer updateTransfer(Long transderId, TransferModel model) {
        int affected = 0;
        Transfer transfer = transferMapper.selectById(transderId);
        if (transfer.getStatus().compareTo(TransferStatus.Draft.toString()) != 0) {
            throw new BusinessException(5100, "不能对非草稿状态下的调拨单进行修改");
        }
        affected += storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>().eq(StorageOutItem.STORAGE_OUT_ID, transderId)
                .eq(StorageOutItem.TYPE, TransactionType.TransferOut.toString()));
        affected += draftOutItem(model.getOutItems(), model.getFromWarehouseId(), transderId);
        return affected;
    }


    @Transactional
    public Integer createTransfer(Long transferId, Long userId) {
        /**
         * 1.调拨包括了两个仓库的出入库 主动为出 被动为入
         * 2.插入 storageIn 以及 storageInItems
         * 3.插入 storageOut 以及 storageOutItems
         * 4.插入 Transfer
         * */

        int affected = 0;

        Transfer transfer = transferMapper.selectById(transferId);

        List<StorageOutItem> items = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>().eq("storage_out_id", transferId)
                .eq(StorageOutItem.TYPE, TransactionType.TransferOut.toString()));

        if (items == null && items.size() <= 0) {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }

        transfer.setTransferTime(new Date());

        StorageOutModel storageOut = new StorageOutModel();
        // field1 去接收最上层的ID  作跳转使用
        storageOut.setField1(transferId.toString());

        storageOut.setStorageOutTime(transfer.getTransactionTime());


        storageOut.setTransactionType(TransactionType.TransferOut.toString());

        storageOut.setWarehouseId(transfer.getFromWarehouseId());
        storageOut.setOriginatorId(userId);
        storageOut.setOriginatorName(transfer.getOriginatorName());
        storageOut.setTransactionBy(transfer.getTransactionBy());
        // 使用调拨记录表中的field1字段去接收出库的code
        storageOut.setTransactionCode(transfer.getField1());
        storageOut.setTransactionTime(new Date());

        StorageOutFilter storageOutFilter = new StorageOutFilter();

        for (StorageOutItem outItem : items) {

            outItem.setRelationCode(transfer.getTransactionCode());// 插入最上级的 编号
            outItem.setTransactionTime(storageOut.getStorageOutTime());

            Inventory isExistInventory = new Inventory();
            isExistInventory.setSkuId(outItem.getSkuId());
            isExistInventory.setWarehouseId(transfer.getFromWarehouseId());

            Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
            Integer afterCount = originInventory.getValidSku() - outItem.getTransactionQuantities();
            originInventory.setValidSku(afterCount);
            outItem.setAfterTransactionQuantities(afterCount);
            // 操作后的数量
            affected += inventoryMapper.updateById(originInventory);
            // 接收方为在途数
            Inventory inventory = new Inventory();
            inventory.setSkuId(outItem.getSkuId());
            inventory.setWarehouseId(transfer.getToWarehouseId());
            Inventory toInventory = inventoryMapper.selectOne(inventory);
            if (toInventory == null) {
                inventory.setValidSku(0);
                inventory.setMinInventory(0);
                inventory.setAdvanceQuantities(0);
                inventory.setMaxInventory(outItem.getTransactionQuantities());
                inventory.setTransmitQuantities(outItem.getTransactionQuantities());
                affected += inventoryMapper.insert(inventory);  //假设 接收方没有 ，则新建
            } else {
                int originCount = toInventory.getTransmitQuantities();
                originCount += outItem.getTransactionQuantities();
                toInventory.setTransmitQuantities(originCount);
                affected += inventoryMapper.updateAllColumnById(toInventory); //有 则是在途数

            }
        }

        storageOut.setStorageOutItems(items);
        storageOut.setStatus("Done");
        affected = crudStorageOutService.createMaster(storageOut, storageOutFilter, null, null);

        transfer.setStorageOutId((Long) storageOutFilter.result().get("id") == null ? null : (Long) storageOutFilter.result().get("id"));

        transfer.setStatus(TransferStatus.Transfer.toString());
        transfer.setOriginatorId(userId);
        transfer.setTransactionTime(new Date());
        transfer.setId(transferId);
        affected += crudTransferService.updateMaster(transfer);
        return affected;
    }


    /**
     * 接受方完成 调拨 ，插入 调拨 货物 及 更新 库存 ,接受方拒绝 ，调拨作废，回到 原来的 仓库
     */
    @Transactional
    public Integer doneTransfer(Long id, Long userId) {
        /**
         * 1.调拨包括了两个仓库的出入库 主动为出 被动为入
         *
         * */

        int affected = 0;
        Transfer transfer = crudTransferService.retrieveMaster(id);
        transfer.setFinishTime(new Date());
        StorageInModel storageIn = new StorageInModel();
        storageIn.setStorageInTime(transfer.getFinishTime());

        StorageOut storageOut = storageOutMapper.selectById(transfer.getStorageOutId());
        List<StorageOutItem> storageOutItems = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, storageOut.getId()));

        List<StorageInItem> items = new ArrayList<>();

        if (storageOutItems != null && storageOutItems.size() > 0) {
            for (StorageOutItem outItem : storageOutItems) {

                StorageInItem inItem = new StorageInItem();
                inItem.setRelationCode(transfer.getTransactionCode());// 插入最上级的 code
                inItem.setTransactionTime(storageIn.getStorageInTime());

                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(outItem.getSkuId());
                isExistInventory.setWarehouseId(transfer.getToWarehouseId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);

                if (originInventory != null) {
                    Integer afterCount = originInventory.getValidSku() + outItem.getTransactionQuantities();
                    // 操作后的数量
                    inItem.setAfterTransactionQuantities(afterCount);

                    originInventory.setValidSku(afterCount);
                    Integer transmitCount = originInventory.getTransmitQuantities() - outItem.getTransactionQuantities();
                    originInventory.setTransmitQuantities(transmitCount);
                    affected += inventoryMapper.updateById(originInventory);
                } else {
                    // 操作后的数量
                    inItem.setAfterTransactionQuantities(outItem.getTransactionQuantities());
                    isExistInventory.setMaxInventory(outItem.getTransactionQuantities());
                    isExistInventory.setAdvanceQuantities(0);
                    isExistInventory.setMinInventory(0);
                    isExistInventory.setTransmitQuantities(0);
                    isExistInventory.setValidSku(outItem.getTransactionQuantities());
                    isExistInventory.setSkuId(outItem.getSkuId());
                    affected += inventoryMapper.insert(isExistInventory);
                }
                inItem.setSkuId(outItem.getSkuId());
                inItem.setTransactionQuantities(outItem.getTransactionQuantities());
                inItem.setTransactionSkuPrice(outItem.getTransactionSkuPrice());
                inItem.setTransactionTime(new Date());

                items.add(inItem);
            }
        }

        // field1 去接收最上层的ID  作跳转使用
        storageIn.setField1(id.toString());

        storageIn.setTransactionType(TransactionType.TransferIn.toString());
        storageIn.setWarehouseId(transfer.getToWarehouseId());
        storageIn.setOriginatorId(userId);
        storageIn.setOriginatorName(transfer.getOriginatorName());
        // needs code ?
        storageIn.setTransactionCode(transfer.getField1().replace("OUT", "IN"));
        storageIn.setTransactionTime(new Date());
        StorageInFilter storageInFilter = new StorageInFilter();
        storageIn.setStorageInItems(items);
        storageIn.setStatus(TransferStatus.Done.toString());

        affected += crudStorageInService.createMaster(storageIn, storageInFilter, null, null);


        transfer.setStorageInId((Long) storageInFilter.result().get("id") == null ? null : (Long) storageInFilter.result().get("id"));

        transfer.setStatus(TransferStatus.Done.toString());
        transfer.setId(id);
        affected += crudTransferService.updateMaster(transfer);
        return affected;
    }

    /**
     * 接受方拒接 调拨 ，插入 调拨 货物 及 更新 库存 ,接受方拒绝 ，调拨作废，回到 原来的 仓库
     */
    @Transactional
    public Integer cancelTransfer(Long id, Long userId) {
        /**
         * 1.调拨包括了两个仓库的出入库 主动为出 被动为入
         *
         * */

        int affected = 0;

        Transfer transfer = crudTransferService.retrieveMaster(id);
        transfer.setFinishTime(new Date());

        StorageInModel storageIn = new StorageInModel();

        // field1 去接收最上层的ID  作跳转使用
        storageIn.setField1(id.toString());

        storageIn.setStorageInTime(transfer.getFinishTime());

        StorageOut storageOut = storageOutMapper.selectById(transfer.getStorageOutId());
        List<StorageOutItem> storageOutItems = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>().eq(StorageOutItem.STORAGE_OUT_ID, storageOut.getId()));


        List<StorageInItem> items = new ArrayList<>();

        if (storageOutItems != null && storageOutItems.size() > 0) {
            for (StorageOutItem outItem : storageOutItems) {
                // come back to from warehouse
                StorageInItem inItem = new StorageInItem();
                inItem.setRelationCode(transfer.getTransactionCode());
                inItem.setTransactionTime(storageIn.getStorageInTime());

                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(outItem.getSkuId());
                isExistInventory.setWarehouseId(transfer.getFromWarehouseId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                if (originInventory == null) {
                    throw new BusinessException(4060, "服务异常，请稍后尝试!");
                }
                Integer afterCount = originInventory.getValidSku() + outItem.getTransactionQuantities();
                originInventory.setValidSku(afterCount);
                // 操作后的数量
                inItem.setAfterTransactionQuantities(afterCount);

                affected += inventoryMapper.updateById(originInventory);
                // 接收方 在途数 - 调拨数
                Inventory inventory = new Inventory();
                inventory.setSkuId(outItem.getSkuId());
                inventory.setWarehouseId(transfer.getToWarehouseId());
                Inventory toInventory = inventoryMapper.selectOne(inventory);
                Integer transmitCount = toInventory.getTransmitQuantities() - outItem.getTransactionQuantities();
                toInventory.setTransmitQuantities(transmitCount);
                affected += inventoryMapper.updateById(toInventory);

                inItem.setSkuId(outItem.getSkuId());
                inItem.setRelationCode(transfer.getTransactionCode());
                inItem.setTransactionQuantities(outItem.getTransactionQuantities());
                inItem.setTransactionSkuPrice(outItem.getTransactionSkuPrice());
                inItem.setTransactionTime(new Date());

                items.add(inItem);
            }
        }

        storageIn.setTransactionType(TransactionType.OthersStorageIn.toString());
        storageIn.setWarehouseId(transfer.getFromWarehouseId());
        storageIn.setOriginatorId(userId);
        storageIn.setOriginatorName(transfer.getOriginatorName());
        // 这个 code 应该怎么去处理呢？
        storageIn.setTransactionCode(transfer.getField1().replace("OUT", "IN"));
        storageIn.setTransactionTime(new Date());
        StorageInFilter storageInFilter = new StorageInFilter();
        storageIn.setStorageInItems(items);
        affected += crudStorageInService.createMaster(storageIn, storageInFilter, null, null);

        transfer.setStorageInId((Long) storageInFilter.result().get("id") == null ? null : (Long) storageInFilter.result().get("id"));
        transfer.setStatus(TransferStatus.Closed.toString());
        transfer.setId(id);
        affected += crudTransferService.updateMaster(transfer);
        return affected;
    }


    public TransferModel transferDetails(Long id) {
        /**
         * 1.包含入库明细
         * 2.包含出库明细
         * 3.tranfer本身的信息
         * */
        Transfer transfer = crudTransferService.retrieveMaster(id);
        if (transfer == null) {
            throw new BusinessException(5200, "数据出错，无该调拨记录");
        }
        JSONObject transferObj = JSON.parseObject(JSONObject.toJSONString(transfer));

//        if (transfer.getStatus().compareTo(TransferStatus.Draft.toString()) == 0) {
            List<StorageOutItemRecord> outItemRecords = queryTransferDao.draftOutItemRecords(id);
            transferObj.put("outItemRecords", outItemRecords);
            transferObj.put("fromWarehouseName", queryWarehouseDao.warehouseName(transfer.getFromWarehouseId()));
            transferObj.put("toWarehouseName", queryWarehouseDao.warehouseName(transfer.getToWarehouseId()));
            TransferModel model = JSONObject.parseObject(JSONObject.toJSONString(transferObj), TransferModel.class);
            return model;
/*        }

        List<StorageOutItemRecord> outItemRecords = queryTransferDao.outItemRecords(transfer.getStorageOutId());
        transferObj.put("outItemRecords", outItemRecords);
        transferObj.put("fromWarehouseName", queryWarehouseDao.warehouseName(transfer.getFromWarehouseId()));
        transferObj.put("toWarehouseName", queryWarehouseDao.warehouseName(transfer.getToWarehouseId()));
        TransferModel model = JSONObject.parseObject(JSONObject.toJSONString(transferObj), TransferModel.class);
        return model;*/
    }


    @Transactional
    public Integer deleteTransfer(Long id) {

        Transfer transfer = crudTransferService.retrieveMaster(id);

        storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq("storage_in_id", transfer.getStorageInId()));
        storageInMapper.delete(new EntityWrapper<StorageIn>().eq("id", transfer.getStorageInId()));


        storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>().eq("storage_out_id", transfer.getStorageOutId()));
        storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>().eq("storage_out_id", id)
                .eq(StorageOutItem.TYPE, TransactionType.TransferOut.toString()));
        storageOutMapper.delete(new EntityWrapper<StorageOut>().eq("id", transfer.getStorageOutId()));


        return crudTransferService.deleteMaster(id);
    }


}
