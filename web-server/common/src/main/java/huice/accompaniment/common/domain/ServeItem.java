package huice.accompaniment.common.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 服务项表(ServeItem)实体类
 *
 * @author Yusen
 * @since 2024-06-04 17:40:10
 */
@TableName("serve_item")
public class ServeItem implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 服务项id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 服务项编码
     */
    @TableField("code")
    private Long code;
    /**
     * 服务类型id
     */
    private Long serveTypeId;
    /**
     * 服务项名称
     */
    private String name;
    /**
     * 服务项图标
     */
    private String serveItemIcon;
    /**
     * 服务项图片
     */
    private String img;
    /**
     * 服务项库存
     */
    private Integer unit;
    /**
     * 服务项描述
     */
    private String description;
    /**
     * 服务项详情图
     */
    private String detailImg;
    /**
     * 服务项状态，0禁用，1启用
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

    public Long getServeTypeId() {
        return serveTypeId;
    }

    public void setServeTypeId(Long serveTypeId) {
        this.serveTypeId = serveTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServeItemIcon() {
        return serveItemIcon;
    }

    public void setServeItemIcon(String serveItemIcon) {
        this.serveItemIcon = serveItemIcon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
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

