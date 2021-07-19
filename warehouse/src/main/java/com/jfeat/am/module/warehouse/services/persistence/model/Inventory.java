package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-08-29
 */
@TableName("wms_inventory")
public class Inventory extends Model<Inventory> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 用于隔离的组织id, 由crud-plus维护
	 */
	@TableField("org_id")
	private  Long orgId;
	/**
	 * 用于隔离的组织标识, 参考 docker而定
	 */
	@TableField("org_tag")
	private String orgTag;
    /**
     * 仓库ID
     */
	@TableField("warehouse_id")
	private Long warehouseId;
    /**
     * 储位ID
     */
	@TableField("slot_id")
	private Long slotId;
    /**
     * 库存上限
     */
	@TableField("max_inventory")
	private Integer maxInventory;
    /**
     * 库存下限
     */
	@TableField("min_inventory")
	private Integer minInventory;
    /**
     * 库存量ID
     */
	@TableField("sku_id")
	private Long skuId;
    /**
     * 可用库存量
     */
	@TableField("valid_sku")
	private Integer validSku;
    /**
     * 预购量
     */
	@TableField("advance_quantities")
	private Integer advanceQuantities;
    /**
     * 在途量
     */
	@TableField("transmit_quantities")
	private Integer transmitQuantities;

	/**
	 * 在途量
	 */
	@TableField("order_count")
	private Integer orderCount;


	public Long getId() {
		return id;
	}

	public Inventory setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public Inventory setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public Inventory setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public Inventory setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public Long getSlotId() {
		return slotId;
	}

	public Inventory setSlotId(Long slotId) {
		this.slotId = slotId;
		return this;
	}

	public Integer getMaxInventory() {
		return maxInventory;
	}

	public Inventory setMaxInventory(Integer maxInventory) {
		this.maxInventory = maxInventory;
		return this;
	}

	public Integer getMinInventory() {
		return minInventory;
	}

	public Inventory setMinInventory(Integer minInventory) {
		this.minInventory = minInventory;
		return this;
	}

	public Long getSkuId() {
		return skuId;
	}

	public Inventory setSkuId(Long skuId) {
		this.skuId = skuId;
		return this;
	}

	public Integer getValidSku() {
		return validSku;
	}

	public Inventory setValidSku(Integer validSku) {
		this.validSku = validSku;
		return this;
	}

	public Integer getAdvanceQuantities() {
		return advanceQuantities;
	}

	public Inventory setAdvanceQuantities(Integer advanceQuantities) {
		this.advanceQuantities = advanceQuantities;
		return this;
	}

	public Integer getTransmitQuantities() {
		return transmitQuantities;
	}

	public Inventory setTransmitQuantities(Integer transmitQuantities) {
		this.transmitQuantities = transmitQuantities;
		return this;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public static final String ID = "id";

	public static final String WAREHOUSE_ID = "warehouse_id";

	public static final String SLOT_ID = "slot_id";

	public static final String MAX_INVENTORY = "max_inventory";

	public static final String MIN_INVENTORY = "min_inventory";

	public static final String SKU_ID = "sku_id";

	public static final String VALID_SKU = "valid_sku";

	public static final String ADVANCE_QUANTITIES = "advance_quantities";

	public static final String TRANSMIT_QUANTITIES = "transmit_quantities";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Inventory{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", warehouseId=" + warehouseId +
				", slotId=" + slotId +
				", maxInventory=" + maxInventory +
				", minInventory=" + minInventory +
				", skuId=" + skuId +
				", validSku=" + validSku +
				", advanceQuantities=" + advanceQuantities +
				", transmitQuantities=" + transmitQuantities +
				", orderCount=" + orderCount +
				'}';
	}
}
