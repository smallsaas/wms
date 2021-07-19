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
@TableName("wms_warehouse")
public class Warehouse extends Model<Warehouse> {

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
	@TableField("warehouse_code")
	private String warehouseCode;
    /**
     * 仓库名称
     */
	@TableField("warehouse_name")
	private String warehouseName;
    /**
     * 仓库所在地区
     */
	@TableField("warehouse_PCD")
	private String warehousePCD;
    /**
     * 仓库详细地址
     */
	@TableField("warehouse_address")
	private String warehouseAddress;
    /**
     * 仓库负责人
     */
	@TableField("warehouse_charger")
	private Long warehouseCharger;


	public Long getId() {
		return id;
	}

	public Warehouse setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public Warehouse setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public Warehouse setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public Warehouse setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
		return this;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public Warehouse setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
		return this;
	}

	public String getWarehousePCD() {
		return warehousePCD;
	}

	public Warehouse setWarehousePCD(String warehousePCD) {
		this.warehousePCD = warehousePCD;
		return this;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public Warehouse setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
		return this;
	}

	public Long getWarehouseCharger() {
		return warehouseCharger;
	}

	public Warehouse setWarehouseCharger(Long warehouseCharger) {
		this.warehouseCharger = warehouseCharger;
		return this;
	}

	public static final String ID = "id";

	public static final String WAREHOUSE_CODE = "warehouse_code";

	public static final String WAREHOUSE_NAME = "warehouse_name";

	public static final String WAREHOUSE_PCD = "warehouse_PCD";

	public static final String WAREHOUSE_ADDRESS = "warehouse_address";

	public static final String WAREHOUSE_CHARGER = "warehouse_charger";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Warehouse{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", warehouseCode='" + warehouseCode + '\'' +
				", warehouseName='" + warehouseName + '\'' +
				", warehousePCD='" + warehousePCD + '\'' +
				", warehouseAddress='" + warehouseAddress + '\'' +
				", warehouseCharger=" + warehouseCharger +
				'}';
	}
}
