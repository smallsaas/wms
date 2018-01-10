package com.jfeat.am.module.profile.services.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-10-24
 */
@TableName("t_profile")
public class Profile extends Model<Profile> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 姓名
     */
	private String name;
    /**
     * 昵称
     */
	private String nick;
    /**
     * 性别
     */
	private String sex;
    /**
     * 电话
     */
	private String phone;
    /**
     * 手机
     */
	private String mobile;
    /**
     * 生日
     */
	private Date birthday;
    /**
     * 地址
     */
	private String address;
	/*
	* 	身高
	* */
	private String height;
	/*
	* 	年龄
	* */
	private Integer age;
	/**
	 * 头像
	 */
	private String avatar;
    /**
     * 微信
     */
	private String wechat;
    /**
     * QQ
     */
	private String qq;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 个性签名
     */
	private String signature;
    /**
     * 身份证号码
     */
	@TableField("idcard_no")
	private Long idcardNo;
    /**
     * 身份证正面
     */
	@TableField("idcard_front")
	private String idcardFront;
    /**
     * 身份证背面
     */
	@TableField("idcard_back")
	private String idcardBack;
    /**
     * 银行卡帐户
     */
	@TableField("bankcard_no")
	private Long bankcardNo;
    /**
     * 银行卡图片
     */
	@TableField("bankcard_snapshot")
	private String bankcardSnapshot;
	/**
	 * 标签
	 */
	private String tag;
	/**
	 * 职业
	 */
	private String career;
	/**
     * 预留字段
     */
	private String field1;
    /**
     * 预留字段
     */
	private String field2;
    /**
     * 预留字段
     */
	private String field3;

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Long getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(Long idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getIdcardFront() {
		return idcardFront;
	}

	public void setIdcardFront(String idcardFront) {
		this.idcardFront = idcardFront;
	}

	public String getIdcardBack() {
		return idcardBack;
	}

	public void setIdcardBack(String idcardBack) {
		this.idcardBack = idcardBack;
	}

	public Long getBankcardNo() {
		return bankcardNo;
	}

	public void setBankcardNo(Long bankcardNo) {
		this.bankcardNo = bankcardNo;
	}

	public String getBankcardSnapshot() {
		return bankcardSnapshot;
	}

	public void setBankcardSnapshot(String bankcardSnapshot) {
		this.bankcardSnapshot = bankcardSnapshot;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	@Override
	protected Serializable pkVal() {
		return null;
	}
}
