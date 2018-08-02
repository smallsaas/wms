package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.modular.system.service.UserService;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageOutFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDTransferService;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.definition.TransferStatus;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;
import com.jfeat.am.module.warehouse.services.domain.model.TransferModel;
import com.jfeat.am.module.warehouse.services.domain.service.StorageInService;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;
import com.jfeat.am.module.warehouse.services.domain.service.TransferService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDTransferServiceImpl;
import com.jfeat.am.module.warehouse.services.domain.service.WarehouseService;
import com.jfeat.am.module.warehouse.services.persistence.dao.*;
import com.jfeat.am.module.warehouse.services.persistence.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
@Service("crudTransferService")
public class TransferServiceImpl extends CRUDTransferServiceImpl implements TransferService {

    @Resource
    CRUDStorageInService crudStorageInService;
    @Resource
    CRUDStorageOutService crudStorageOutService;
    @Resource
    CRUDTransferService crudTransferService;
    @Resource
    StorageInItemMapper storageInItemMapper;
    @Resource
    StorageInMapper storageInMapper;
    @Resource
    StorageOutItemMapper storageOutItemMapper;
    @Resource
    StorageOutMapper storageOutMapper;

    @Resource
    UserService userService;
    @Resource
    WarehouseService warehouseService;

    @Resource
    StorageInService storageInService;

    @Resource
    StorageOutService storageOutService;

    @Resource
    InventoryMapper inventoryMapper;

    @Transactional
    public Integer createTransfer(TransferModel model, Long userId) {
        /**
         * 1.调拨包括了两个仓库的出入库 主动为出 被动为入
         * 2.插入 storageIn 以及 storageInItems
         * 3.插入 storageOut 以及 storageOutItems
         * 4.插入 Transfer
         * */
        int affected = 0;

        StorageOutModel storageOut = new StorageOutModel();
        storageOut.setStorageOutItems(model.getOutItems());
        storageOut.setTransactionType(TransactionType.Transfer.toString());
        storageOut.setWarehouseId(model.getFromWarehouseId());
        storageOut.setOriginatorId(userId);
        storageOut.setTransactionBy(userId);
        storageOut.setTransactionCode(model.getTransactionCode());
        storageOut.setTransactionTime(new Date());
        StorageOutFilter storageOutFilter = new StorageOutFilter();
        List<StorageOutItem> items = new ArrayList<>();
        if (model.getOutItems() != null && model.getOutItems().size() > 0) {
            for (StorageOutItem outItem : model.getOutItems()) {
                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(outItem.getSkuId());
                isExistInventory.setWarehouseId(model.getFromWarehouseId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                if (originInventory != null) {
                    if (outItem.getTransactionQuantities() > originInventory.getValidSku()) {
                        throw new BusinessException(4050, "库存不足,"+ "现有库存"+ originInventory.getValidSku() +"小于出库量"+outItem.getTransactionQuantities());
                    } else {
                        originInventory.setValidSku(originInventory.getValidSku() - outItem.getTransactionQuantities());
                        affected += inventoryMapper.updateById(originInventory);
                    }
                } else {
                    throw new BusinessException(BusinessCode.NotImplement);
                }
                items.add(outItem);
            }
        }
        storageOut.setStorageOutItems(items);
        affected = crudStorageOutService.createMaster(storageOut, storageOutFilter, null, null);

        model.setStorageOutId((Long) storageOutFilter.result().get("id") == null ? null : (Long) storageOutFilter.result().get("id"));

        model.setOriginatorId(userId);
        model.setOperator(userId);
        model.setTransactionTime(new Date());
        affected += crudTransferService.createMaster(model);
        return affected;
    }


    /**
     * 接受方完成 调拨 ，插入 调拨 货物 及 更新 库存 ,接受方拒绝 ，调拨作废，回到 原来的 仓库
     */
    @Transactional
    public Integer doneTransfer(Long id,TransferModel model, Long userId) {
        /**
         * 1.调拨包括了两个仓库的出入库 主动为出 被动为入
         *
         * */

        int affected = 0;

        StorageInModel storageIn = new StorageInModel();
        storageIn.setTransactionType(TransactionType.Transfer.toString());
        storageIn.setWarehouseId(model.getToWarehouseId());
        storageIn.setOriginatorId(userId);
        storageIn.setTransactionBy(userId);
        storageIn.setTransactionCode(model.getTransactionCode());
        storageIn.setTransactionTime(new Date());
        StorageInFilter storageInFilter = new StorageInFilter();
        List<StorageInItem> items = new ArrayList<>();

        if (model.getOutItems() != null && model.getOutItems().size() > 0) {
            for (StorageOutItem outItem : model.getOutItems()) {

                StorageInItem inItem = new StorageInItem();

                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(outItem.getSkuId());
                isExistInventory.setWarehouseId(model.getToWarehouseId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);

                if (originInventory != null) {
                    originInventory.setValidSku(originInventory.getValidSku() + outItem.getTransactionQuantities());
                    affected += inventoryMapper.updateById(originInventory);
                } else {
                    isExistInventory.setMaxInventory(outItem.getTransactionQuantities());
                    isExistInventory.setAdvanceQuantities(0);
                    isExistInventory.setMinInventory(0);
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
        storageIn.setStorageInItems(items);

        affected += crudStorageInService.createMaster(storageIn, storageInFilter, null, null);


        model.setStorageInId((Long) storageInFilter.result().get("id") == null ? null : (Long) storageInFilter.result().get("id"));
        affected += crudTransferService.updateMaster(model);
        return affected;
    }

    /**
     * 接受方拒接 调拨 ，插入 调拨 货物 及 更新 库存 ,接受方拒绝 ，调拨作废，回到 原来的 仓库
     */
    @Transactional
    public Integer cancelTransfer(Long id,TransferModel model, Long userId) {
        /**
         * 1.调拨包括了两个仓库的出入库 主动为出 被动为入
         *
         * */

        int affected = 0;

        StorageInModel storageIn = new StorageInModel();
        storageIn.setTransactionType(TransactionType.Transfer.toString());
        storageIn.setWarehouseId(model.getFromWarehouseId());
        storageIn.setOriginatorId(userId);
        storageIn.setTransactionBy(userId);
        storageIn.setTransactionCode(model.getTransactionCode());
        storageIn.setTransactionTime(new Date());
        StorageInFilter storageInFilter = new StorageInFilter();
        List<StorageInItem> items = new ArrayList<>();

        if (model.getOutItems() != null && model.getOutItems().size() > 0) {
            for (StorageOutItem outItem : model.getOutItems()) {

                StorageInItem inItem = new StorageInItem();

                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(outItem.getSkuId());
                isExistInventory.setWarehouseId(model.getFromWarehouseId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);

                originInventory.setValidSku(originInventory.getValidSku() + outItem.getTransactionQuantities());
                affected += inventoryMapper.updateById(originInventory);


                inItem.setSkuId(outItem.getSkuId());
                inItem.setTransactionQuantities(outItem.getTransactionQuantities());
                inItem.setTransactionSkuPrice(outItem.getTransactionSkuPrice());
                inItem.setTransactionTime(new Date());

                items.add(inItem);
            }
        }

        storageIn.setStorageInItems(items);
        affected += crudStorageInService.createMaster(storageIn, storageInFilter, null, null);

        model.setStorageInId((Long) storageInFilter.result().get("id") == null ? null : (Long) storageInFilter.result().get("id"));
        affected += crudTransferService.updateMaster(model);
        return affected;
    }


    public TransferModel transferDetails(Long id) {
        /**
         * 1.包含入库明细
         * 2.包含出库明细
         * 3.tranfer本身的信息
         * */
        Transfer transfer = crudTransferService.retrieveMaster(id);
        JSONObject transferObj = JSON.parseObject(JSONObject.toJSONString(transfer));

        StorageOut out = storageOutMapper.selectById(transfer.getStorageOutId());

        List<StorageOutItem> items = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>().eq(StorageOutItem.STORAGE_OUT_ID,out.getId()));

        transferObj.put("outItems", items);

        transferObj.put("originatorName", userService.getById(transfer.getOriginatorId()).getName());
        transferObj.put("operatorName",
                userService.getById(transfer.getOperator()).getName() == null ? null : userService.getById(transfer.getOperator()).getName());
        transferObj.put("fromWarehouseName",
                warehouseService.retrieveMaster(transfer.getFromWarehouseId()) == null ? null : warehouseService.retrieveMaster(transfer.getFromWarehouseId()).getWarehouseName());
        transferObj.put("toWarehouseName",
                warehouseService.retrieveMaster(transfer.getToWarehouseId()) == null ? null : warehouseService.retrieveMaster(transfer.getFromWarehouseId()).getWarehouseName());

        TransferModel model = JSONObject.parseObject(JSONObject.toJSONString(transferObj), TransferModel.class);
        return model;
    }


    @Transactional
    public Integer deleteTransfer(Long id) {

        Transfer transfer = crudTransferService.retrieveMaster(id);

        storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq("storage_in_id", transfer.getStorageInId()));
        storageInMapper.delete(new EntityWrapper<StorageIn>().eq("id", transfer.getStorageInId()));


        storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>().eq("storage_out_id", transfer.getStorageOutId()));
        storageOutMapper.delete(new EntityWrapper<StorageOut>().eq("id", transfer.getStorageOutId()));


        return crudTransferService.deleteMaster(id);
    }


}
