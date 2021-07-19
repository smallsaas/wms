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
@TableName("wms_check_sku")
public class CheckSku extends Model<CheckSku> {

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
     * 盘点编号
     */
	@TableField("check_id")
	private Long checkId;
    /**
     * 盘点SKUID
     */
	@TableField("sku_id")
	private Long skuId;
    /**
     * 仓库ID
     */
	@TableField("warehouse_id")
	private Long warehouseId;
    /**
     * 实际存量
     */
	@TableField("fact_quantities")
	private Integer factQuantities;
    /**
     * 校对qian的数量，如果缺失值为0的话，该值同应有量相同
     */
	@TableField("before_proof_quantities")
	private Integer beforeProofQuantities;
    /**
     * 应有存量
     */
	@TableField("deserved_quantities")
	private Integer deservedQuantities;
    /**
     * 盈亏(缺失值)
     */
	@TableField("profit_lost")
	private Integer profitLost;
    /**
     * 保留字段
     */
	private String field1;
    /**
     * 保留字段
     */
	private String field2;


	public Long getId() {
		return id;
	}

	public CheckSku setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public CheckSku setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public CheckSku setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public Long getCheckId() {
		return checkId;
	}

	public CheckSku setCheckId(Long checkId) {
		this.checkId = checkId;
		return this;
	}

	public Long getSkuId() {
		return skuId;
	}

	public CheckSku setSkuId(Long skuId) {
		this.skuId = skuId;
		return this;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public CheckSku setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
		return this;
	}

	public Integer getFactQuantities() {
		return factQuantities;
	}

	public CheckSku setFactQuantities(Integer factQuantities) {
		this.factQuantities = factQuantities;
		return this;
	}

	public Integer getBeforeProofQuantities() {
		return beforeProofQuantities;
	}

	public CheckSku setBeforeProofQuantities(Integer beforeProofQuantities) {
		this.beforeProofQuantities = beforeProofQuantities;
		return this;
	}

	public Integer getDeservedQuantities() {
		return deservedQuantities;
	}

	public CheckSku setDeservedQuantities(Integer deservedQuantities) {
		this.deservedQuantities = deservedQuantities;
		return this;
	}

	public Integer getProfitLost() {
		return profitLost;
	}

	public CheckSku setProfitLost(Integer profitLost) {
		this.profitLost = profitLost;
		return this;
	}

	public String getField1() {
		return field1;
	}

	public CheckSku setField1(String field1) {
		this.field1 = field1;
		return this;
	}

	public String getField2() {
		return field2;
	}

	public CheckSku setField2(String field2) {
		this.field2 = field2;
		return this;
	}

	public static final String ID = "id";

	public static final String CHECK_ID = "check_id";

	public static final String SKU_ID = "sku_id";

	public static final String WAREHOUSE_ID = "warehouse_id";

	public static final String FACT_QUANTITIES = "fact_quantities";

	public static final String BEFORE_PROOF_QUANTITIES = "before_proof_quantities";

	public static final String DESERVED_QUANTITIES = "deserved_quantities";

	public static final String PROFIT_LOST = "profit_lost";

	public static final String FIELD1 = "field1";

	public static final String FIELD2 = "field2";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CheckSku{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", checkId=" + checkId +
				", skuId=" + skuId +
				", warehouseId=" + warehouseId +
				", factQuantities=" + factQuantities +
				", beforeProofQuantities=" + beforeProofQuantities +
				", deservedQuantities=" + deservedQuantities +
				", profitLost=" + profitLost +
				", field1='" + field1 + '\'' +
				", field2='" + field2 + '\'' +
				'}';
	}
}
