package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
 * @since 2018-06-22
 */
@TableName("wms_suppliers")
public class Suppliers extends Model<Suppliers> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 供名称应商
     */
	@TableField("supplier_name")
	private String supplierName;
    /**
     * 供应商编号
     */
	@TableField("supplier_code")
	private String supplierCode;
    /**
     * 供应商区域
     */
	@TableField("supplier_PCD")
	private String supplierPCD;
    /**
     * 供应商详细地址
     */
	@TableField("supplier_address")
	private String supplierAddress;
    /**
     * 供应商邮编
     */
	@TableField("supplier_postcode")
	private String supplierPostcode;
    /**
     * 供应商联系人姓名
     */
	@TableField("supplier_contact_name")
	private String supplierContactName;
    /**
     * 供应商联系人电话(座机)
     */
	@TableField("supplier_contact_phone")
	private String supplierContactPhone;
    /**
     * 供应商联系人传真
     */
	@TableField("supplier_contact_fax")
	private String supplierContactFax;
    /**
     * 供应商联系人邮箱
     */
	@TableField("supplier_contact_email")
	private String supplierContactEmail;
    /**
     * 供应商联系人职位
     */
	@TableField("supplier_contact_position")
	private String supplierContactPosition;
    /**
     * 供应商联系人手机
     */
	@TableField("supplier_contact_cell_phone")
	private String supplierContactCellPhone;
    /**
     * 供应商开户名称
     */
	@TableField("supplier_account_name")
	private String supplierAccountName;
    /**
     * 供应商开户银行
     */
	@TableField("supplier_account_bank")
	private String supplierAccountBank;
    /**
     * 供应商银行账号
     */
	@TableField("supplier_account_bank_no")
	private Long supplierAccountBankNo;
    /**
     * 供应商发票抬头
     */
	@TableField("supplier_invoice_title")
	private String supplierInvoiceTitle;
    /**
     * 供应商状态
     */
	@TableField("supplier_status")
	private String supplierStatus;
    /**
     * 供应商备注
     */
	@TableField("supplier_note")
	private String supplierNote;
    /**
     * 供应商注册时间
     */
	@TableField("supplier_register_time")
	private Date supplierRegisterTime;
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

	public Suppliers setId(Long id) {
		this.id = id;
		return this;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public Suppliers setSupplierName(String supplierName) {
		this.supplierName = supplierName;
		return this;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public Suppliers setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
		return this;
	}

	public String getSupplierPCD() {
		return supplierPCD;
	}

	public Suppliers setSupplierPCD(String supplierPCD) {
		this.supplierPCD = supplierPCD;
		return this;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public Suppliers setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
		return this;
	}

	public String getSupplierPostcode() {
		return supplierPostcode;
	}

	public Suppliers setSupplierPostcode(String supplierPostcode) {
		this.supplierPostcode = supplierPostcode;
		return this;
	}

	public String getSupplierContactName() {
		return supplierContactName;
	}

	public Suppliers setSupplierContactName(String supplierContactName) {
		this.supplierContactName = supplierContactName;
		return this;
	}

	public String getSupplierContactPhone() {
		return supplierContactPhone;
	}

	public Suppliers setSupplierContactPhone(String supplierContactPhone) {
		this.supplierContactPhone = supplierContactPhone;
		return this;
	}

	public String getSupplierContactFax() {
		return supplierContactFax;
	}

	public Suppliers setSupplierContactFax(String supplierContactFax) {
		this.supplierContactFax = supplierContactFax;
		return this;
	}

	public String getSupplierContactEmail() {
		return supplierContactEmail;
	}

	public Suppliers setSupplierContactEmail(String supplierContactEmail) {
		this.supplierContactEmail = supplierContactEmail;
		return this;
	}

	public String getSupplierContactPosition() {
		return supplierContactPosition;
	}

	public Suppliers setSupplierContactPosition(String supplierContactPosition) {
		this.supplierContactPosition = supplierContactPosition;
		return this;
	}

	public String getSupplierContactCellPhone() {
		return supplierContactCellPhone;
	}

	public Suppliers setSupplierContactCellPhone(String supplierContactCellPhone) {
		this.supplierContactCellPhone = supplierContactCellPhone;
		return this;
	}

	public String getSupplierAccountName() {
		return supplierAccountName;
	}

	public Suppliers setSupplierAccountName(String supplierAccountName) {
		this.supplierAccountName = supplierAccountName;
		return this;
	}

	public String getSupplierAccountBank() {
		return supplierAccountBank;
	}

	public Suppliers setSupplierAccountBank(String supplierAccountBank) {
		this.supplierAccountBank = supplierAccountBank;
		return this;
	}

	public Long getSupplierAccountBankNo() {
		return supplierAccountBankNo;
	}

	public Suppliers setSupplierAccountBankNo(Long supplierAccountBankNo) {
		this.supplierAccountBankNo = supplierAccountBankNo;
		return this;
	}

	public String getSupplierInvoiceTitle() {
		return supplierInvoiceTitle;
	}

	public Suppliers setSupplierInvoiceTitle(String supplierInvoiceTitle) {
		this.supplierInvoiceTitle = supplierInvoiceTitle;
		return this;
	}

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public Suppliers setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
		return this;
	}

	public String getSupplierNote() {
		return supplierNote;
	}

	public Suppliers setSupplierNote(String supplierNote) {
		this.supplierNote = supplierNote;
		return this;
	}

	public Date getSupplierRegisterTime() {
		return supplierRegisterTime;
	}

	public Suppliers setSupplierRegisterTime(Date supplierRegisterTime) {
		this.supplierRegisterTime = supplierRegisterTime;
		return this;
	}

	public String getField1() {
		return field1;
	}

	public Suppliers setField1(String field1) {
		this.field1 = field1;
		return this;
	}

	public String getField2() {
		return field2;
	}

	public Suppliers setField2(String field2) {
		this.field2 = field2;
		return this;
	}

	public static final String ID = "id";

	public static final String SUPPLIER_NAME = "supplier_name";

	public static final String SUPPLIER_CODE = "supplier_code";

	public static final String SUPPLIER_PCD = "supplier_PCD";

	public static final String SUPPLIER_ADDRESS = "supplier_address";

	public static final String SUPPLIER_POSTCODE = "supplier_postcode";

	public static final String SUPPLIER_CONTACT_NAME = "supplier_contact_name";

	public static final String SUPPLIER_CONTACT_PHONE = "supplier_contact_phone";

	public static final String SUPPLIER_CONTACT_FAX = "supplier_contact_fax";

	public static final String SUPPLIER_CONTACT_EMAIL = "supplier_contact_email";

	public static final String SUPPLIER_CONTACT_POSITION = "supplier_contact_position";

	public static final String SUPPLIER_CONTACT_CELL_PHONE = "supplier_contact_cell_phone";

	public static final String SUPPLIER_ACCOUNT_NAME = "supplier_account_name";

	public static final String SUPPLIER_ACCOUNT_BANK = "supplier_account_bank";

	public static final String SUPPLIER_ACCOUNT_BANK_NO = "supplier_account_bank_no";

	public static final String SUPPLIER_INVOICE_TITLE = "supplier_invoice_title";

	public static final String SUPPLIER_STATUS = "supplier_status";

	public static final String SUPPLIER_NOTE = "supplier_note";

	public static final String SUPPLIER_REGISTER_TIME = "supplier_register_time";

	public static final String FIELD1 = "field1";

	public static final String FIELD2 = "field2";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Suppliers{" +
			"id=" + id +
			", supplierName=" + supplierName +
			", supplierCode=" + supplierCode +
			", supplierPCD=" + supplierPCD +
			", supplierAddress=" + supplierAddress +
			", supplierPostcode=" + supplierPostcode +
			", supplierContactName=" + supplierContactName +
			", supplierContactPhone=" + supplierContactPhone +
			", supplierContactFax=" + supplierContactFax +
			", supplierContactEmail=" + supplierContactEmail +
			", supplierContactPosition=" + supplierContactPosition +
			", supplierContactCellPhone=" + supplierContactCellPhone +
			", supplierAccountName=" + supplierAccountName +
			", supplierAccountBank=" + supplierAccountBank +
			", supplierAccountBankNo=" + supplierAccountBankNo +
			", supplierInvoiceTitle=" + supplierInvoiceTitle +
			", supplierStatus=" + supplierStatus +
			", supplierNote=" + supplierNote +
			", supplierRegisterTime=" + supplierRegisterTime +
			", field1=" + field1 +
			", field2=" + field2 +
			"}";
	}
}
