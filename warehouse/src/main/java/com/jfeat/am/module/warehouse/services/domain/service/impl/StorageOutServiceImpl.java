package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.definition.ItemEnumType;
import com.jfeat.am.module.warehouse.services.definition.SalesStatus;
import com.jfeat.am.module.warehouse.services.definition.StorageOutStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryInventoryDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QuerySalesDao;
import com.jfeat.am.module.warehouse.services.domain.model.BulkUpdateOrderCount;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;
import com.jfeat.am.module.warehouse.services.domain.model.UpdateOrderCount;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDStorageOutServiceImpl;
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
@Service("crudStorageOutService")
public class StorageOutServiceImpl extends CRUDStorageOutServiceImpl implements StorageOutService {


    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    CRUDStorageOutService crudStorageOutService;
    @Resource
    StorageOutMapper storageOutMapper;
    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    QueryInventoryDao queryInventoryDao;
    @Resource
    StorageOutItemMapper outItemMapper;
    @Resource
    SkuProductMapper skuProductMapper;
    @Resource
    SalesMapper salesMapper;
    @Resource
    QuerySalesDao querySalesDao;


    @Transactional
    public Integer changeStatus(Long userId, Long storageOutId, StorageOutModel entity) {

        Integer affected = 0;
        // 先执行删除，避免重复添加数据
        affected += outItemMapper.delete(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, storageOutId)
                .eq(StorageOutItem.TYPE, ItemEnumType.STORAGEOUT.toString()));

        entity.setOriginatorId(userId);
        entity.setTransactionTime(new Date());
        if (entity.getStorageOutTime() == null) {
            entity.setStorageOutTime(new Date());
        }

        if (entity.getStorageOutItems() != null && entity.getStorageOutItems().size() > 0) {
            for (StorageOutItem outItem : entity.getStorageOutItems()) {
                if (outItem.getDemandQuantities() > 0) {
                    outItem.setStorageOutId(storageOutId);
                    outItem.setTransactionQuantities(outItem.getDemandQuantities());
                    outItem.setRelationCode(entity.getTransactionCode());
                    outItem.setTransactionTime(entity.getStorageOutTime());
                    outItem.setType(ItemEnumType.STORAGEOUT.toString());
                    // 设置产品的入库时间
                    outItem.setTransactionTime(entity.getTransactionTime());

                    SkuProduct skuProduct = skuProductMapper.selectById(outItem.getSkuId());

                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(outItem.getSkuId());
                    isExistInventory.setWarehouseId(entity.getWarehouseId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    if (originInventory != null) {
                        if (outItem.getTransactionQuantities() > originInventory.getValidSku()) {
                            throw new BusinessException(4050, "\""
                                    + skuProduct.getSkuName()==null?null:skuProduct.getSkuName()
                                    + skuProduct.getBarCode()==null?null:skuProduct.getBarCode()
                                    + ":\"" + "库存不足," + "现有库存"
                                    + originInventory.getValidSku()
                                    + "小于出库量" + outItem.getTransactionQuantities());
                        } else {
                            affected += outItemMapper.insert(outItem);
                        }
                    } else {
                        throw new BusinessException(4051, "产品不存在，请核对！");
                    }
                } else {
                    //while transaction quantities = 0 ,do nothing
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        return affected;

    }

    /**
     * while create , storage out  status is Draft; 新建 出库单
     */
    @Transactional
    public Integer draftStorageOut(Long userId, StorageOutModel entity) {

        Integer affected = 0;

        entity.setOriginatorId(userId);
        entity.setTransactionTime(new Date());
        if (entity.getStorageOutTime() == null) {
            entity.setStorageOutTime(new Date());
        }
        entity.setStatus(StorageOutStatus.Draft.toString());
        affected += storageOutMapper.insert(entity);
        affected += changeStatus(userId,entity.getId(),entity);
        return affected;
    }

    /**
     * update by do not modified status
     */
    @Transactional
    public Integer updateStorageIn(Long userId, Long storageOutId, StorageOutModel entity) {

        Integer affected = 0;
        StorageOut out = storageOutMapper.selectById(storageOutId);
        if (out == null) {
            throw new BusinessException(5100,"无id为"+storageOutId+"的出库单！");
        }
        if (out.getStatus().compareTo(StorageOutStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        affected += changeStatus(userId, storageOutId, entity);
        entity.setId(storageOutId);
        affected += storageOutMapper.updateById(entity);
        return affected;

    }

    /**
     * commit and wait to audit
     */
    @Transactional
    public Integer commitStorageOut(Long userId, Long storageOutId, StorageOutModel entity) {
        Integer affected = 0;


        StorageOut out = storageOutMapper.selectById(storageOutId);

        if (out == null) {
            throw new BusinessException(5100,"无id为"+storageOutId+"的出库单！");
        }
        if (out.getStatus().compareTo(StorageOutStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        entity.setStatus(StorageOutStatus.Wait_To_Audit.toString());
        entity.setId(storageOutId);
        affected += changeStatus(userId, storageOutId, entity);
        affected += storageOutMapper.updateById(entity);
        return affected;
    }


    /**
     * audit passed
     */
    @Transactional
    public Integer passedStorageOut(Long storageOutId, StorageOutModel entity) {
        StorageOut out = storageOutMapper.selectById(storageOutId);

        if (out == null) {
            throw new BusinessException(5100,"无id为"+storageOutId+"的出库单！");
        }
        if (out.getStatus().compareTo(StorageOutStatus.Wait_To_Audit.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        // 允许在审核的时候 修改 子项的数据
        for (StorageOutItem item : entity.getStorageOutItems()) {
            item.setType(ItemEnumType.STORAGEOUT.toString());
            outItemMapper.updateById(item);
        }
        entity.setStatus(StorageOutStatus.Audit_Passed.toString());
        entity.setId(storageOutId);
        return storageOutMapper.updateById(entity);
    }

    /**
     * audit rejected
     */
    @Transactional
    public Integer auditRejectedStorageOut(Long storageOutId) {
        StorageOut out = storageOutMapper.selectById(storageOutId);

        if (out == null) {
            throw new BusinessException(5100,"无id为"+storageOutId+"的出库单！");
        }
        if (out.getStatus().compareTo(StorageOutStatus.Wait_To_Audit.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        out.setStatus(StorageOutStatus.Closed.toString());
        out.setId(storageOutId);
        return storageOutMapper.updateById(out);
    }


    /**
     * SKU 肯定 存在  需要 判断 他的 可用量 是否 大于 出库的数量
     */
    @Transactional
    public Integer executionStorageOut(String uasername, Long storageOutId) {
        StorageOut out = storageOutMapper.selectById(storageOutId);
        if (out == null) {
            throw new BusinessException(5100,"无id为"+storageOutId+"的出库单！");
        }
        if (out.getOutOrderNum() != null) {
            throw new BusinessException(5320, "商城出库单只能通过发货方式执行出库操作！");
        }
        if (out.getStatus().compareTo(StorageOutStatus.Audit_Passed.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        Integer affected = 0;
        out.setTransactionBy(uasername);
        out.setTransactionTime(new Date());
        if (out.getStorageOutTime() == null) {
            out.setStorageOutTime(new Date());
        }

        List<StorageOutItem> items = outItemMapper.selectList(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, storageOutId)
                .eq(StorageOutItem.TYPE, ItemEnumType.STORAGEOUT));

        if (items != null && items.size() > 0) {
            for (StorageOutItem outItem : items) {
                if (outItem.getTransactionQuantities() > 0) {
                    /*outItem.setType("Others");
                    outItemMapper.updateById(outItem);*/
                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(outItem.getSkuId());
                    isExistInventory.setWarehouseId(out.getWarehouseId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);

                    Integer afterCount = originInventory.getValidSku() - outItem.getTransactionQuantities();
                    //outItem.setAfterTransactionQuantities(afterCount);
                    originInventory.setValidSku(afterCount);
                    affected += inventoryMapper.updateById(originInventory);
                } else {
                    throw new BusinessException(4050, "出库数量不能为空！");
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        out.setStatus(StorageOutStatus.Done.toString());
        out.setId(storageOutId);
        affected = storageOutMapper.updateById(out);
        return affected;
    }

    /**
     *  分销商出库 执行出库
     * */
    @Transactional
    public Integer executionSalesStorageOut(String uasername, Long storageOutId) {
        StorageOut out = storageOutMapper.selectById(storageOutId);
        if (out == null) {
            throw new BusinessException(5100,"无id为"+storageOutId+"的出库单！");
        }
        if (out.getStatus().compareTo(StorageOutStatus.Audit_Passed.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        Sales sales = salesMapper.selectById(out.getSalesId());
        if (sales == null){
            throw new BusinessException(5500,"分销商出库订单有误，请重新核对");
        }
        Integer affected = 0;
        out.setTransactionBy(uasername);
        out.setTransactionTime(new Date());
        if (out.getStorageOutTime() == null) {
            out.setStorageOutTime(new Date());
        }
        List<StorageOutItem> items = outItemMapper.selectList(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, storageOutId)
                .eq(StorageOutItem.TYPE, ItemEnumType.STORAGEOUT));

        Integer totalCount = querySalesDao.totalCount(sales.getId());
        Integer finishedTotalCount = querySalesDao.finishedTotalCount(sales.getId());
        if (finishedTotalCount == null){
            finishedTotalCount=0;
        }
        if (items != null && items.size() > 0) {
            for (StorageOutItem outItem : items) {
                if (outItem.getTransactionQuantities() > 0) {
                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(outItem.getSkuId());
                    isExistInventory.setWarehouseId(out.getWarehouseId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    Integer afterCount = originInventory.getValidSku() - outItem.getTransactionQuantities();
                    originInventory.setValidSku(afterCount);
                    affected += inventoryMapper.updateById(originInventory);
                    finishedTotalCount += outItem.getTransactionQuantities();
                } else {
                }
            }
        } else {
            throw new BusinessException(4050, "数据出错！");
        }
        if (totalCount==finishedTotalCount){
            sales.setSalesStatus(SalesStatus.TotalStorageOut.toString());
            salesMapper.updateById(sales);
        }else {
            sales.setSalesStatus(SalesStatus.SectionStorageOut.toString());
            salesMapper.updateById(sales);
        }
        out.setStatus(StorageOutStatus.Done.toString());
        out.setId(storageOutId);
        affected = storageOutMapper.updateById(out);
        return affected;
    }

    /**
     * for 商城下单 出库，不需要审核直接出库
     */
    @Transactional
    public Integer salesStorageOut(Long userId, StorageOutModel entity) {
        Integer affected = 0;
        entity.setOriginatorId(userId);
        entity.setOriginatorName("商城出库");
        entity.setTransactionType(TransactionType.SalesOut.toString());
        entity.setTransactionTime(new Date());
        if (entity.getStorageOutTime() == null) {
            entity.setStorageOutTime(new Date());
        }
        if (entity.getWarehouseId() == null) {
            entity.setWarehouseId(1L);
        }
        entity.setStatus(StorageOutStatus.Audit_Passed.toString());
        affected += storageOutMapper.insert(entity);
        if (entity.getStorageOutItems() != null && entity.getStorageOutItems().size() > 0) {
            for (StorageOutItem outItem : entity.getStorageOutItems()) {
                if (outItem.getTransactionQuantities() > 0) {
                    outItem.setStorageOutId(entity.getId());
                    outItem.setDemandQuantities(outItem.getTransactionQuantities());
                    outItem.setType(ItemEnumType.STORAGEOUT.toString());
                    outItem.setRelationCode(entity.getTransactionCode());
                    outItem.setTransactionTime(entity.getStorageOutTime());
                    SkuProduct skuProduct = skuProductMapper.selectById(outItem.getSkuId());
                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(outItem.getSkuId());
                    isExistInventory.setWarehouseId(entity.getWarehouseId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    if (originInventory != null) {
                        if (outItem.getTransactionQuantities() > originInventory.getValidSku()) {
                            throw new BusinessException(4050, "\""
                                    + skuProduct.getSkuName()==null?null:skuProduct.getSkuName()
                                    + skuProduct.getBarCode()==null?null:skuProduct.getBarCode()
                                    + ":\"" + "库存不足," + "现有库存"
                                    + originInventory.getValidSku()
                                    + "小于出库量" + outItem.getTransactionQuantities());
                        } else {
                            // 是否是直接减少 库存呢
                            Integer afterCount = originInventory.getValidSku() - outItem.getTransactionQuantities();
                            //outItem.setAfterTransactionQuantities(afterCount);
                            originInventory.setValidSku(afterCount);
                            //占用内存量累加
                            originInventory.setOrderCount(originInventory.getOrderCount() + outItem.getTransactionQuantities());
                            affected += inventoryMapper.updateById(originInventory);
                        }
                    } else {
                        throw new BusinessException(4051, "\"" + skuProduct.getSkuName() + skuProduct.getBarCode() + ":\"" + "产品不存在，请核对！");
                    }
                    outItemMapper.insert(outItem);
                } else {
                    //while transaction quantities = 0 ,do nothing
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        return affected;
    }

    /**
     * 更新占用库存，商城的出货的时候调用
     */
    @Transactional
    public Integer updateOrderCount(BulkUpdateOrderCount entity) {
        logger.info("调用API:/api/wms/storages/out/mall/update,对应数据:" + JSON.toJSONString(entity));

        // 需要前端 提交 订单的 ID，方便去索引 出库单
        Integer affected = 0;
        if (entity.getItems() == null || entity.getItems().size() <= 0) {
            logger.info("没有更新占用库存" + "未检测到需要执行更新占用库存操作的商品，请核准并重新提交" + JSON.toJSONString(entity));
            throw new BusinessException(5310, "未检测到需要执行更新占用库存操作的商品，请核准并重新提交");
        }
        StorageOut out = new StorageOut();
        out.setOutOrderNum(entity.getOutOrderNum());
        out.setTransactionType(TransactionType.SalesOut.toString());
        logger.info("出库单查找的初始化值" + JSON.toJSONString(out));
        StorageOut originOut = storageOutMapper.selectOne(out);
        logger.info("打出来，证明有值" + JSON.toJSONString(out));
        if (originOut == null) {
            throw new BusinessException(5300, "未检测到id为" + entity.getOutOrderNum() + "的订单的出库记录，请重新核对");
        }
        for (UpdateOrderCount updateOrderCount : entity.getItems()) {
            StorageOutItem item = new StorageOutItem();
            item.setSkuId(updateOrderCount.getSkuId());
            item.setType(ItemEnumType.STORAGEOUT.toString());
            item.setStorageOutId(originOut.getId());
            StorageOutItem originOutItem = outItemMapper.selectOne(item);
            logger.info("打出来，证明商品有值" + JSON.toJSONString(originOutItem));
            if (originOutItem == null) {
                throw new BusinessException(5310, "该订单下无SKUID为" + updateOrderCount.getSkuId() + "的商品购买记录");
            }
            /*originOutItem.setType("Others");
            outItemMapper.updateById(originOutItem);*/

            Inventory origin = new Inventory();
            origin.setSkuId(updateOrderCount.getSkuId());
            if (updateOrderCount.getWarehouseId() == null || updateOrderCount.getWarehouseId() < 0) {
                origin.setWarehouseId(out.getWarehouseId() == null ? 1L : out.getWarehouseId());
            } else {
                origin.setWarehouseId(updateOrderCount.getWarehouseId());
            }
            Inventory inventory = inventoryMapper.selectOne(origin);
            if (inventory == null) {
                throw new BusinessException(5300, "无该商品库存记录!请核准然后重新提交!");
            }
            if (inventory.getOrderCount() < updateOrderCount.getOrderCount()) {
                throw new BusinessException(5300, "出货数据有误，请核准并重新提交");
            }
            Integer afterOrderCount = inventory.getOrderCount() - updateOrderCount.getOrderCount();
            //inventory.setValidSku(inventory.getValidSku()-updateOrderCount.getOrderCount());
            inventory.setOrderCount(afterOrderCount);
            affected += inventoryMapper.updateById(inventory);
        }
        originOut.setStatus(StorageOutStatus.Done.toString());
        storageOutMapper.updateById(originOut);
        return affected;
    }

    /**
     * 删除 入库单 以及对应的子项
     * */
    @Transactional
    public Integer deleteStorageOut(Long id){
        Integer affected = 0;
        affected += outItemMapper.delete(new EntityWrapper<StorageOutItem>().eq(StorageOutItem.STORAGE_OUT_ID,id).eq(StorageOutItem.TYPE,ItemEnumType.STORAGEOUT));
        affected += storageOutMapper.deleteById(id);
        return affected;
    }
}
