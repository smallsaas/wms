package com.jfeat.am.module.product.services.persistence.model;

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
 * @since 2018-06-19
 */
@TableName("t_specification_group")
public class SpecificationGroup extends Model<SpecificationGroup> {

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
     * 规格名称
     */
    @TableField("group_name")
    private String groupName;

    @TableField("pid")
    private Long pid;

    public Long getId() {
        return id;
    }

    public SpecificationGroup setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrgId() {
        return orgId;
    }

    public SpecificationGroup setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getOrgTag() {
        return orgTag;
    }

    public SpecificationGroup setOrgTag(String orgTag) {
        this.orgTag = orgTag;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public SpecificationGroup setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public static final String ID = "id";

    public static final String GROUP_NAME = "group_name";

    public static final String ORG_ID = "org_id";

    public static final String ORG_TAG = "org_tag";

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SpecificationGroup{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", orgTag='" + orgTag + '\'' +
                ", groupName='" + groupName + '\'' +
                ", pid=" + pid +
                '}';
    }
}
