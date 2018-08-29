package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
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

	public static final String ID = "id";

	public static final String WAREHOUSE_ID = "warehouse_id";

	public static final String SLOT_ID = "slot_id";

	public static final String MAX_INVENTORY = "max_inventory";

	public static final String MIN_INVENTORY = "min_inventory";

	public static final String SKU_ID = "sku_id";

	public static final String VALID_SKU = "valid_sku";

	public static final String ADVANCE_QUANTITIES = "advance_quantities";

	public static final String TRANSMIT_QUANTITIES = "transmit_quantities";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Inventory{" +
			"id=" + id +
			", warehouseId=" + warehouseId +
			", slotId=" + slotId +
			", maxInventory=" + maxInventory +
			", minInventory=" + minInventory +
			", skuId=" + skuId +
			", validSku=" + validSku +
			", advanceQuantities=" + advanceQuantities +
			", transmitQuantities=" + transmitQuantities +
			"}";
	}
}
