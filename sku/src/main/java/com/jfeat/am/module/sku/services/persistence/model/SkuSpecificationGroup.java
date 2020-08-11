package com.jfeat.am.module.sku.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 *
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-18
 */
@TableName("t_specification_group")
public class SkuSpecificationGroup extends Model<SkuSpecificationGroup> {

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

    /**
     * 列表 规格
     */
    @TableField("type")
    private String type;
    /**
     * 父及目录
     */
    private Long pid;


    public Long getId() {
        return id;
    }

    public SkuSpecificationGroup setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public SkuSpecificationGroup setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPid() {
        return pid;
    }

    public SkuSpecificationGroup setPid(Long pid) {
        this.pid = pid;
        return this;
    }

    public static final String ID = "id";

    public static final String GROUP_NAME = "group_name";


    public static final String PID = "pid";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SkuSpecificationGroup{" +
                "id=" + id +
                ", groupName=" + groupName +
                ", type=" + type +
                ", pid=" + pid +
                "}";
    }
}
