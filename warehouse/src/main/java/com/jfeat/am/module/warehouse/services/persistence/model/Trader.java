package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * @since 2018-11-14
 */
@TableName("wms_trader")
public class Trader extends Model<Trader> {

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
     * 分销商名称
     */
	@TableField("trader_name")
	private String traderName;
    /**
     * 分销商编号
     */
	@TableField("trader_code")
	private String traderCode;
    /**
     * 分销商区域
     */
	@TableField("trader_PCD")
	private String traderPCD;
    /**
     * 分销商详细地址
     */
	@TableField("trader_address")
	private String traderAddress;
    /**
     * 分销商邮编
     */
	@TableField("trader_postcode")
	private String traderPostcode;
    /**
     * 分销商联系人姓名
     */
	@TableField("trader_contact_name")
	private String traderContactName;
    /**
     * 分销商联系人电话(座机)
     */
	@TableField("trader_contact_phone")
	private String traderContactPhone;
    /**
     * 分销商联系人传真
     */
	@TableField("trader_contact_fax")
	private String traderContactFax;
    /**
     * 分销商联系人邮箱
     */
	@TableField("trader_contact_email")
	private String traderContactEmail;
    /**
     * 分销商联系人职位
     */
	@TableField("trader_contact_position")
	private String traderContactPosition;
    /**
     * 分销商联系人手机
     */
	@TableField("trader_contact_cell_phone")
	private String traderContactCellPhone;
    /**
     * 分销商开户名称
     */
	@TableField("trader_account_name")
	private String traderAccountName;
    /**
     * 分销商开户银行
     */
	@TableField("trader_account_bank")
	private String traderAccountBank;
    /**
     * 分销商银行账号
     */
	@TableField("trader_account_bank_no")
	private Long traderAccountBankNo;
    /**
     * 分销商发票抬头
     */
	@TableField("trader_invoice_title")
	private String traderInvoiceTitle;
    /**
     * 分销商状态
     */
	@TableField("trader_status")
	private String traderStatus;
    /**
     * 分销商备注
     */
	@TableField("trader_note")
	private String traderNote;
    /**
     * 分销商注册时间
     */
	@TableField("trader_register_time")
	private Date traderRegisterTime;
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

	public Trader setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public Trader setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public Trader setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getTraderName() {
		return traderName;
	}

	public Trader setTraderName(String traderName) {
		this.traderName = traderName;
		return this;
	}

	public String getTraderCode() {
		return traderCode;
	}

	public Trader setTraderCode(String traderCode) {
		this.traderCode = traderCode;
		return this;
	}

	public String getTraderPCD() {
		return traderPCD;
	}

	public Trader setTraderPCD(String traderPCD) {
		this.traderPCD = traderPCD;
		return this;
	}

	public String getTraderAddress() {
		return traderAddress;
	}

	public Trader setTraderAddress(String traderAddress) {
		this.traderAddress = traderAddress;
		return this;
	}

	public String getTraderPostcode() {
		return traderPostcode;
	}

	public Trader setTraderPostcode(String traderPostcode) {
		this.traderPostcode = traderPostcode;
		return this;
	}

	public String getTraderContactName() {
		return traderContactName;
	}

	public Trader setTraderContactName(String traderContactName) {
		this.traderContactName = traderContactName;
		return this;
	}

	public String getTraderContactPhone() {
		return traderContactPhone;
	}

	public Trader setTraderContactPhone(String traderContactPhone) {
		this.traderContactPhone = traderContactPhone;
		return this;
	}

	public String getTraderContactFax() {
		return traderContactFax;
	}

	public Trader setTraderContactFax(String traderContactFax) {
		this.traderContactFax = traderContactFax;
		return this;
	}

	public String getTraderContactEmail() {
		return traderContactEmail;
	}

	public Trader setTraderContactEmail(String traderContactEmail) {
		this.traderContactEmail = traderContactEmail;
		return this;
	}

	public String getTraderContactPosition() {
		return traderContactPosition;
	}

	public Trader setTraderContactPosition(String traderContactPosition) {
		this.traderContactPosition = traderContactPosition;
		return this;
	}

	public String getTraderContactCellPhone() {
		return traderContactCellPhone;
	}

	public Trader setTraderContactCellPhone(String traderContactCellPhone) {
		this.traderContactCellPhone = traderContactCellPhone;
		return this;
	}

	public String getTraderAccountName() {
		return traderAccountName;
	}

	public Trader setTraderAccountName(String traderAccountName) {
		this.traderAccountName = traderAccountName;
		return this;
	}

	public String getTraderAccountBank() {
		return traderAccountBank;
	}

	public Trader setTraderAccountBank(String traderAccountBank) {
		this.traderAccountBank = traderAccountBank;
		return this;
	}

	public Long getTraderAccountBankNo() {
		return traderAccountBankNo;
	}

	public Trader setTraderAccountBankNo(Long traderAccountBankNo) {
		this.traderAccountBankNo = traderAccountBankNo;
		return this;
	}

	public String getTraderInvoiceTitle() {
		return traderInvoiceTitle;
	}

	public Trader setTraderInvoiceTitle(String traderInvoiceTitle) {
		this.traderInvoiceTitle = traderInvoiceTitle;
		return this;
	}

	public String getTraderStatus() {
		return traderStatus;
	}

	public Trader setTraderStatus(String traderStatus) {
		this.traderStatus = traderStatus;
		return this;
	}

	public String getTraderNote() {
		return traderNote;
	}

	public Trader setTraderNote(String traderNote) {
		this.traderNote = traderNote;
		return this;
	}

	public Date getTraderRegisterTime() {
		return traderRegisterTime;
	}

	public Trader setTraderRegisterTime(Date traderRegisterTime) {
		this.traderRegisterTime = traderRegisterTime;
		return this;
	}

	public String getField1() {
		return field1;
	}

	public Trader setField1(String field1) {
		this.field1 = field1;
		return this;
	}

	public String getField2() {
		return field2;
	}

	public Trader setField2(String field2) {
		this.field2 = field2;
		return this;
	}

	public static final String ID = "id";

	public static final String TRADER_NAME = "trader_name";

	public static final String TRADER_CODE = "trader_code";

	public static final String TRADER_PCD = "trader_PCD";

	public static final String TRADER_ADDRESS = "trader_address";

	public static final String TRADER_POSTCODE = "trader_postcode";

	public static final String TRADER_CONTACT_NAME = "trader_contact_name";

	public static final String TRADER_CONTACT_PHONE = "trader_contact_phone";

	public static final String TRADER_CONTACT_FAX = "trader_contact_fax";

	public static final String TRADER_CONTACT_EMAIL = "trader_contact_email";

	public static final String TRADER_CONTACT_POSITION = "trader_contact_position";

	public static final String TRADER_CONTACT_CELL_PHONE = "trader_contact_cell_phone";

	public static final String TRADER_ACCOUNT_NAME = "trader_account_name";

	public static final String TRADER_ACCOUNT_BANK = "trader_account_bank";

	public static final String TRADER_ACCOUNT_BANK_NO = "trader_account_bank_no";

	public static final String TRADER_INVOICE_TITLE = "trader_invoice_title";

	public static final String TRADER_STATUS = "trader_status";

	public static final String TRADER_NOTE = "trader_note";

	public static final String TRADER_REGISTER_TIME = "trader_register_time";

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
		return "Trader{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", traderName='" + traderName + '\'' +
				", traderCode='" + traderCode + '\'' +
				", traderPCD='" + traderPCD + '\'' +
				", traderAddress='" + traderAddress + '\'' +
				", traderPostcode='" + traderPostcode + '\'' +
				", traderContactName='" + traderContactName + '\'' +
				", traderContactPhone='" + traderContactPhone + '\'' +
				", traderContactFax='" + traderContactFax + '\'' +
				", traderContactEmail='" + traderContactEmail + '\'' +
				", traderContactPosition='" + traderContactPosition + '\'' +
				", traderContactCellPhone='" + traderContactCellPhone + '\'' +
				", traderAccountName='" + traderAccountName + '\'' +
				", traderAccountBank='" + traderAccountBank + '\'' +
				", traderAccountBankNo=" + traderAccountBankNo +
				", traderInvoiceTitle='" + traderInvoiceTitle + '\'' +
				", traderStatus='" + traderStatus + '\'' +
				", traderNote='" + traderNote + '\'' +
				", traderRegisterTime=" + traderRegisterTime +
				", field1='" + field1 + '\'' +
				", field2='" + field2 + '\'' +
				'}';
	}
}
