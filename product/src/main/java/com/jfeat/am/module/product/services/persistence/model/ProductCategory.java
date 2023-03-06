package com.jfeat.am.module.product.services.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-19
 */
@TableName("wms_product_category")
public class ProductCategory extends Model<ProductCategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
     * 类型名称
     */
    @TableField("category_name")
    private String categoryName;
    /**
     * 类型code
     */
    @TableField("category_code")
    private String categoryCode;
    /**
     * 父级ID
     */
    private Long pid;
    /**
     * 类型说明
     */
    @TableField("category_description")
    private String categoryDescription;


    public Long getId() {
        return id;
    }

    public ProductCategory setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrgId() {
        return orgId;
    }

    public ProductCategory setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getOrgTag() {
        return orgTag;
    }

    public ProductCategory setOrgTag(String orgTag) {
        this.orgTag = orgTag;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ProductCategory setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Long getPid() {
        return pid;
    }

    public ProductCategory setPid(Long pid) {
        this.pid = pid;
        return this;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public ProductCategory setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        return this;
    }

    public static final String ID = "id";

    public static final String CATEGORY_NAME = "category_name";

    public static final String CATEGORY_CODE = "category_code";

    public static final String PID = "pid";

    public static final String CATEGORY_DESCRIPTION = "category_description";

    public static final String ORG_ID = "org_id";

    public static final String ORG_TAG = "org_tag";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", orgTag='" + orgTag + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", pid=" + pid +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}
