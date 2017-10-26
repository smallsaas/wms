package com.jfeat.am.module.profile.services.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-10-24
 */
@TableName("t_enterprise")
public class Enterprise extends Model<Enterprise> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 商家行业
     */
	private String industry;
    /**
     * 商家地址
     */
	private String site;
    /**
     * 商家简介
     */
	private String brief;
    /**
     * 营业执照
     */
	@TableField("license_code")
	private String licenseCode;
    /**
     * 执照照片
     */
	@TableField("license_image")
	private String licenseImage;
    /**
     * 开户银行
     */
	private String bank;
    /**
     * 银行帐号
     */
	@TableField("bank_account")
	private String bankAccount;
    /**
     * 身份证号码
     */
	@TableField("idcard_no")
	private Long idcardNo;
    /**
     * 身份证照片
     */
	@TableField("idcard_image")
	private String idcardImage;
	/**
     * 公司法人人
     */
	@TableField("contact")
	private String contact;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getLicenseImage() {
		return licenseImage;
	}

	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Long getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(Long idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getIdcardImage() {
		return idcardImage;
	}

	public void setIdcardImage(String idcardImage) {
		this.idcardImage = idcardImage;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Enterprise{" +
			"id=" + id +
			", industry=" + industry +
			", site=" + site +
			", brief=" + brief +
			", licenseCode=" + licenseCode +
			", licenseImage=" + licenseImage +
			", bank=" + bank +
			", bankAccount=" + bankAccount +
			", idcardNo=" + idcardNo +
			", idcardImage=" + idcardImage +
			", contact=" + contact +
			"}";
	}
}
