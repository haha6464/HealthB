package com.huice.health.common.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 服务类型表(ServeType)实体类
 *
 * @author Yusen
 * @since 2024-06-04 15:30:25
 */
public class ServeType implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 服务类型id
     */
    private Long id;
    /**
     * 服务类型编码
     */
    private Long code;
    /**
     * 服务类型名称
     */
    private String name;
    /**
     * 服务类型图标
     */
    private String serveTypeIcon;
    /**
     * 服务类型图片
     */
    private String img;
    /**
     * 是否启用，0禁用，1启用
     */
    private Integer activeStatus;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 创建者
     */
    private Long createBy;
    /**
     * 更新者
     */
    private Long updateBy;
    /**
     * 逻辑删除
     */
    private Integer delFlag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServeTypeIcon() {
        return serveTypeIcon;
    }

    public void setServeTypeIcon(String serveTypeIcon) {
        this.serveTypeIcon = serveTypeIcon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}

