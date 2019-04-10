package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.definition.ItemEnumType;
import com.jfeat.am.module.warehouse.services.definition.StorageInStatus;
import com.jfeat.am.module.warehouse.services.definition.StorageOutStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.service.StorageInService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDStorageInServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInItemMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageIn;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service("crudStorageInService")
public class StorageInServiceImpl extends CRUDStorageInServiceImpl implements StorageInService {

    @Resource
    CRUDStorageInService crudStorageInService;
    @Resource
    StorageInMapper storageInMapper;
    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    StorageInItemMapper inItemMapper;
    @Resource
    StorageOutMapper storageOutMapper;

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public Integer changeStatus(Long userId, Long storageInId, StorageInModel entity) {
        Integer affected = 0;
        // 对多子项的更新，先删除原来的。
        affected += inItemMapper.delete(new EntityWrapper<StorageInItem>()
                .eq(StorageInItem.STORAGE_IN_ID, storageInId)
                .eq(StorageInItem.TYPE, ItemEnumType.STORAGEIN.toString()));
//        entity.setOriginatorId(userId);
//        entity.setTransactionTime(new Date());
//        if (entity.getStorageInTime() == null) {
//            entity.setStorageInTime(new Date());
//        }
        if (entity.getStorageInItems() != null && entity.getStorageInItems().size() > 0) {
            for (StorageInItem inItem : entity.getStorageInItems()) {
                if (inItem.getTransactionQuantities() > 0) {
                    // 重新插入
                    inItem.setStorageInId(storageInId);
                    inItem.setDemandQuantities(inItem.getTransactionQuantities());
                    inItem.setRelationCode(entity.getTransactionCode());
                    inItem.setTransactionTime(entity.getStorageInTime());
                    // 重新 设定 itemType
                    inItem.setType(ItemEnumType.STORAGEIN.toString());
                    // 设置产品的入库时间
                    inItem.setTransactionTime(entity.getTransactionTime());
                    affected += inItemMapper.insert(inItem);

                } else {
                    //while transaction quantities = 0 ,do nothing 当需求数量为0 时，不进行插入操作。
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        return affected;
    }

    /**
     * while create , storage in  status is Draft; 新建，草稿状态
     */
    @Transactional
    public Integer createStorageIn(Long userId, StorageInModel entity) {
        Integer affected = 0;
        entity.setOriginatorId(userId);
        entity.setTransactionTime(new Date());
        if (entity.getStorageInTime() == null) {
            entity.setStorageInTime(new Date());
        }
        entity.setStatus(StorageInStatus.Draft.toString());
        affected += storageInMapper.insert(entity);
        affected += changeStatus(userId, entity.getId(), entity);
        return affected;
    }

    /**
     * update by do not modified status 仅更新，不提交审核
     */
    @Transactional
    public Integer updateStorageIn(Long userId, Long storageInId, StorageInModel entity) {
        Integer affected = 0;

        StorageIn in = storageInMapper.selectById(storageInId);
        if (in == null) {
            throw new BusinessException(5100, "无id为" + storageInId + "的入库单！");
        }
        affected += changeStatus(userId, storageInId, entity);
        entity.setId(storageInId);
        entity.setOriginatorName(in.getOriginatorName());
        entity.setOriginatorId(in.getOriginatorId());
        affected += storageInMapper.updateById(entity);
        return affected;

    }

    /**
     * commit and wait to audit  提交审核
     */
    @Transactional
    public Integer commitStorageIn(Long userId, Long storageInId, StorageInModel entity) {

        Integer affected = 0;
        StorageIn in = storageInMapper.selectById(storageInId);
        if (in == null) {
            throw new BusinessException(5100, "无id为" + storageInId + "的入库单！");
        }
        if (in.getStatus().compareTo(StorageInStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        affected += changeStatus(userId, storageInId, entity);
        entity.setStatus(StorageInStatus.Wait_To_Audit.toString());
        entity.setId(storageInId);
        entity.setOriginatorName(in.getOriginatorName());
        entity.setOriginatorId(in.getOriginatorId());
        affected += storageInMapper.updateById(entity);
        return affected;
    }

    /**
     * audit passed
     */
    @Transactional
    public Integer passedStorageIn(Long storageInId, StorageInModel entity) {
        StorageIn in = storageInMapper.selectById(storageInId);
        if (in == null) {
            throw new BusinessException(5100, "无id为" + storageInId + "的入库单！");
        }
        if (in.getStatus().compareTo(StorageInStatus.Wait_To_Audit.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        // 审核可能改变数据，先 update 子项数据
        for (StorageInItem item : entity.getStorageInItems()) {
            item.setType(ItemEnumType.STORAGEIN.toString());
            inItemMapper.updateById(item);
        }
        entity.setStatus(StorageInStatus.Audit_Passed.toString());
        entity.setId(storageInId);
        entity.setOriginatorName(in.getOriginatorName());
        entity.setOriginatorId(in.getOriginatorId());
        return storageInMapper.updateById(entity);
    }

    /**
     * audit rejected
     */
    @Transactional
    public Integer auditRejectedStorageIn(Long storageInId) {

        StorageIn in = storageInMapper.selectById(storageInId);
        if (in == null) {
            throw new BusinessException(5100, "无id为" + storageInId + "的入库单！");
        }
        if (in.getStatus().compareTo(StorageInStatus.Wait_To_Audit.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        in.setId(storageInId);
        in.setStatus(StorageInStatus.Closed.toString());
        return storageInMapper.updateById(in);
    }


    /**
     * 假设 该提交的 SKU 不存在 库存 ，则插入 ，如果存在 ，则更新  executionRefund storage in operation
     */
    @Transactional
    public Integer executionStorageIn(String username, Long storageInId) {
        Integer affected = 0;

        StorageIn in = storageInMapper.selectById(storageInId);
        if (in == null) {
            throw new BusinessException(5100, "无id为" + storageInId + "的入库单！");
        }

        if (in.getStatus().compareTo(StorageInStatus.Audit_Passed.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        List<StorageInItem> items = inItemMapper.selectList(new EntityWrapper<StorageInItem>()
                .eq(StorageInItem.STORAGE_IN_ID, storageInId)
                .eq(StorageInItem.TYPE, ItemEnumType.STORAGEIN.toString()));

        in.setTransactionBy(username);
        in.setTransactionTime(new Date());
        in.setStorageInTime(new Date());
        if (items != null && items.size() > 0) {
            for (StorageInItem inItem : items) {
                if (inItem.getTransactionQuantities() > 0) {
                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(inItem.getSkuId());
                    isExistInventory.setWarehouseId(in.getWarehouseId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    if (originInventory != null) {
                        //插入操作后的库存数量 原来数量+准备入库数量
                        Integer afterSkuCount = originInventory.getValidSku() + inItem.getTransactionQuantities();
                        inItem.setAfterTransactionQuantities(afterSkuCount);
                        originInventory.setValidSku(afterSkuCount);
                        affected += inventoryMapper.updateById(originInventory);
                    } else {
                        //插入操作后的库存数量 == 准备入库的数量 原来的不存在
                        inItem.setAfterTransactionQuantities(inItem.getTransactionQuantities());
//                        isExistInventory.setWarehouseId(in.getWarehouseId());
                        isExistInventory.setValidSku(inItem.getTransactionQuantities());
                        affected += inventoryMapper.insert(isExistInventory);
                    }
                    inItemMapper.updateById(inItem);
                } else {
                    //while transaction quantities = 0 ,do nothing
                }
            }
        } else {
            throw new BusinessException(5200, "执行入库的商品为空");
        }
        in.setStatus(StorageInStatus.Done.toString());
        in.setId(storageInId);
        affected = storageInMapper.updateById(in);
        return affected;
    }


    /**
     * for 商城退货的时候直接 插入，不许需要审核
     */
    @Transactional
    public Integer salesStorageIn(Long userId, StorageInModel entity) {
        Integer affected = 0;
        StorageOut out = new StorageOut();
        out.setOutOrderNum(entity.getOutOrderNum());
        out.setTransactionType(TransactionType.SalesOut.toString());
        StorageOut originOut = storageOutMapper.selectOne(out);
        if (originOut == null) {
            throw new BusinessException(5320, "未找到订单号为" + entity.getOutOrderNum() + "的出库单，请核对后再次提交");
        }
        // 如果商城出库单是已经完成的状态的话，需要创建 入库单。
        if (originOut.getStatus().compareTo(StorageOutStatus.Done.toString()) == 0) {
            entity.setOriginatorId(userId);
            entity.setTransactionTime(new Date());
            entity.setOriginatorName("商城入库");
            if (entity.getStorageInTime() == null) {
                entity.setStorageInTime(new Date());
            }
            if (entity.getStorageInItems() != null && entity.getStorageInItems().size() > 0) {
                for (StorageInItem inItem : entity.getStorageInItems()) {
                    if (inItem.getTransactionQuantities() > 0) {
                        inItem.setRelationCode(entity.getTransactionCode());
                        inItem.setType(ItemEnumType.STORAGEIN.toString());
                        inItem.setDemandQuantities(inItem.getTransactionQuantities());
                        inItem.setTransactionTime(entity.getStorageInTime());
                        // 设置产品的入库时间
                        inItem.setTransactionTime(entity.getTransactionTime());
                        Inventory isExistInventory = new Inventory();
                        isExistInventory.setSkuId(inItem.getSkuId());
                        if (entity.getWarehouseId() == null || entity.getWarehouseId() < 0) {
                            isExistInventory.setWarehouseId(1L);
                        } else {
                            isExistInventory.setWarehouseId(entity.getWarehouseId());
                        }
                        Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                        if (originInventory != null) {
                            //插入操作后的库存数量 原来数量+准备入库数量
                            Integer afterSkuCount = originInventory.getValidSku() + inItem.getTransactionQuantities();
                            originInventory.setValidSku(afterSkuCount);
                            inItem.setAfterTransactionQuantities(afterSkuCount);
                            affected += inventoryMapper.updateById(originInventory);
                        } else {
                            //插入操作后的库存数量 == 准备入库的数量 原来的不存在
                            inItem.setAfterTransactionQuantities(inItem.getTransactionQuantities());
                            isExistInventory.setWarehouseId(entity.getWarehouseId());
                            isExistInventory.setValidSku(inItem.getTransactionQuantities());
                            affected += inventoryMapper.insert(isExistInventory);
                        }
                        inItemMapper.insert(inItem);
                    } else {
                        //while transaction quantities = 0 ,do nothing
                    }
                }
            } else {
                throw new BusinessException(4050, "商品不能为空，请先选择商品！");
            }
            entity.setStatus(StorageInStatus.Done.toString());
            affected += storageInMapper.insert(entity);
            // 商城未发货，则不创建新的入库单
        } else if (originOut.getStatus().compareTo(StorageOutStatus.Audit_Passed.toString()) == 0) {
            for (StorageInItem inItem : entity.getStorageInItems()) {
                Inventory origin = new Inventory();
                origin.setSkuId(inItem.getSkuId());
                if (entity.getWarehouseId() == null || entity.getWarehouseId() < 0) {
                    origin.setWarehouseId(1L);
                } else {
                    origin.setWarehouseId(entity.getWarehouseId());
                }
                Inventory inventory = inventoryMapper.selectOne(origin);
                if (inventory == null) {
                    logger.info("没有更新占用库存" + "无该商品库存记录!请核准然后重新提交!" + JSON.toJSONString(entity));
                    throw new BusinessException(5300, "无该商品库存记录!请核准然后重新提交!");
                }
                logger.info("没有更新占用库存" + "----->更新之前，打印库存信息" + JSON.toJSONString(inventory));
                if (inventory.getOrderCount() < inItem.getTransactionQuantities()) {
                    logger.info("没有更新占用库存" + "出货数据有误，请核准并重新提交" + JSON.toJSONString(entity));
                    throw new BusinessException(5300, "出货数据有误，请核准并重新提交");
                }
                Integer afterOrderCount = inventory.getOrderCount() - inItem.getTransactionQuantities();
                // 数量回滚 ----- 支付超时订单关闭时，实际商品不会出货
                inventory.setValidSku(inventory.getValidSku() + inItem.getTransactionQuantities());
                inventory.setOrderCount(afterOrderCount);
                affected += inventoryMapper.updateById(inventory);
                logger.info("没有更新占用库存" + "----->这个时候更新成功了，打印库存信息" + JSON.toJSONString(inventory));
            }
            originOut.setStatus(StorageOutStatus.Closed.toString());
            storageOutMapper.updateById(originOut);
        } else {
            throw new BusinessException(5300, "状态错误，无法执行更新占用库存");
        }
        return affected;
    }

    /**
     * 删除 入库单 以及对应的子项
     */
    @Transactional
    public Integer deleteStorageIn(Long id) {

        Integer affected = 0;
        affected += inItemMapper.delete(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID, id).eq(StorageInItem.TYPE, ItemEnumType.STORAGEIN));
        affected += storageInMapper.deleteById(id);
        return affected;
    }
}
