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
@TableName("wms_warehouse_slot")
public class WarehouseSlot extends Model<WarehouseSlot> {

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
     * 仓库编号
     */
	@TableField("slot_code")
	private String slotCode;
    /**
     * 仓库名称
     */
	@TableField("slot_name")
	private String slotName;
    /**
     * 仓库ID
     */
	@TableField("warehouse_id")
	private Long warehouseId;
    /**
     * 储位说明
     */
	@TableField("slot_note")
	private String slotNote;


	public Long getId() {
		return id;
	}

	public WarehouseSlot setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public WarehouseSlot setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public WarehouseSlot setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getSlotCode() {
		return slotCode;
	}

	public WarehouseSlot setSlotCode(String slotCode) {
		this.slotCode = slotCode;
		return this;
	}

	public String getSlotName() {
		return slotName;
	}

	public WarehouseSlot setSlotName(String slotName) {
		this.slotName = slotName;
		return this;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public WarehouseSlot setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
		return this;
	}

	public String getSlotNote() {
		return slotNote;
	}

	public WarehouseSlot setSlotNote(String slotNote) {
		this.slotNote = slotNote;
		return this;
	}

	public static final String ID = "id";

	public static final String SLOT_CODE = "slot_code";

	public static final String SLOT_NAME = "slot_name";

	public static final String WAREHOUSE_ID = "warehouse_id";

	public static final String SLOT_NOTE = "slot_note";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WarehouseSlot{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", slotCode='" + slotCode + '\'' +
				", slotName='" + slotName + '\'' +
				", warehouseId=" + warehouseId +
				", slotNote='" + slotNote + '\'' +
				'}';
	}
}
