package com.jfeat.am.module.statement.services.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-11-02
 */
@TableName("equipment_template")
public class EquipmentTemplate extends Model<EquipmentTemplate> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 模板编码，此编码是生成设备编码的其中一部分
     */
	private String number;
    /**
     * 设备编码
     */
	private String code;
    /**
     * 设备名称
     */
	private String name;
    /**
     * 规格型号
     */
	private String spec;
    /**
     * 材质
     */
	private String material;
    /**
     *  归属部门 外键
     */
	@TableField("department_id")
	private Long departmentId;
    /**
     * 部门名称
     */
	@TableField("department_name")
	private String departmentName;
    /**
     * 计量单位
     */
	private String unit;
    /**
     * 备注	
     */
	private String note;
    /**
     * 品牌名称
     */
	private String brand;
    /**
     * 供应商名称
     */
	private String supplier;
    /**
     * 仓库ID
     */
	@TableField("warehouse_id")
	private Long warehouseId;
    /**
     * 库区ID
     */
	@TableField("warehouse_area_id")
	private Long warehouseAreaId;
    /**
     * 储位ID
     */
	@TableField("warehouse_slot_id")
	private Long warehouseSlotId;
    /**
     * 主机编码
     */
	@TableField("machine_code")
	private String machineCode;
    /**
     * 设备系统
     */
	private String system;
    /**
     * 当前状态
     */
	private String status;
    /**
     *  制造厂
     */
	private String factory;
    /**
     * 安装位置
     */
	@TableField("installation_site")
	private String installationSite;
    /**
     * 启用时间
     */
	@TableField("start_time")
	private Date startTime;
    /**
     * ABC分类
     */
	private Integer type;
    /**
     * 出厂时间
     */
	@TableField("produce_time")
	private Date produceTime;
    /**
     * 设备主次
     */
	private Integer main;
    /**
     * 类别
     */
	@TableField("category_id")
	private Long categoryId;
    /**
     * 类别名称，显示类别名称的时候不需要去category表查询，无论category相应的类别怎么变化，是删除还是改名，这里显示的都是添加设备模板时的类别名
     */
	@TableField("category_name")
	private String categoryName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getWarehouseAreaId() {
		return warehouseAreaId;
	}

	public void setWarehouseAreaId(Long warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}

	public Long getWarehouseSlotId() {
		return warehouseSlotId;
	}

	public void setWarehouseSlotId(Long warehouseSlotId) {
		this.warehouseSlotId = warehouseSlotId;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getInstallationSite() {
		return installationSite;
	}

	public void setInstallationSite(String installationSite) {
		this.installationSite = installationSite;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getProduceTime() {
		return produceTime;
	}

	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}

	public Integer getMain() {
		return main;
	}

	public void setMain(Integer main) {
		this.main = main;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public static final String ID = "id";

	public static final String NUMBER = "number";

	public static final String CODE = "code";

	public static final String NAME = "name";

	public static final String SPEC = "spec";

	public static final String MATERIAL = "material";

	public static final String DEPARTMENT_ID = "department_id";

	public static final String DEPARTMENT_NAME = "department_name";

	public static final String UNIT = "unit";

	public static final String NOTE = "note";

	public static final String BRAND = "brand";

	public static final String SUPPLIER = "supplier";

	public static final String WAREHOUSE_ID = "warehouse_id";

	public static final String WAREHOUSE_AREA_ID = "warehouse_area_id";

	public static final String WAREHOUSE_SLOT_ID = "warehouse_slot_id";

	public static final String MACHINE_CODE = "machine_code";

	public static final String SYSTEM = "system";

	public static final String STATUS = "status";

	public static final String FACTORY = "factory";

	public static final String INSTALLATION_SITE = "installation_site";

	public static final String START_TIME = "start_time";

	public static final String TYPE = "type";

	public static final String PRODUCE_TIME = "produce_time";

	public static final String MAIN = "main";

	public static final String CATEGORY_ID = "category_id";

	public static final String CATEGORY_NAME = "category_name";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "EquipmentTemplate{" +
			"id=" + id +
			", number=" + number +
			", code=" + code +
			", name=" + name +
			", spec=" + spec +
			", material=" + material +
			", departmentId=" + departmentId +
			", departmentName=" + departmentName +
			", unit=" + unit +
			", note=" + note +
			", brand=" + brand +
			", supplier=" + supplier +
			", warehouseId=" + warehouseId +
			", warehouseAreaId=" + warehouseAreaId +
			", warehouseSlotId=" + warehouseSlotId +
			", machineCode=" + machineCode +
			", system=" + system +
			", status=" + status +
			", factory=" + factory +
			", installationSite=" + installationSite +
			", startTime=" + startTime +
			", type=" + type +
			", produceTime=" + produceTime +
			", main=" + main +
			", categoryId=" + categoryId +
			", categoryName=" + categoryName +
			"}";
	}
}
