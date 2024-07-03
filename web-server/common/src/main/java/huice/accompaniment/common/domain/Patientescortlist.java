package huice.accompaniment.common.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (Patientescortlist)实体类
 *
 * @author Yida Yang
 * @since 2024-06-11 09:50:27
 */
public class Patientescortlist implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * major key
     */
    private Long id;
    /**
     * 城市id
     */
    private Long cityId;
    /**
     * 陪诊师id
     */
    private Long userId;
    /**
     * 陪诊师名字
     */
    private String name;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * IC
     */
    private String idCard;
    /**
     * 身份证正面图片url
     */
    private String icFrontImageUrl;
    /**
     * 身份证背面图片url
     */
    private String icBackImageUrl;
    /**
     * 健康证照片
     */
    private String healthCertificateImageUrl;
    /**
     * 评分
     */
    private BigDecimal rating;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改人
     */
    private Long updateBy;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 状态
     */
    private Integer status;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIcFrontImageUrl() {
        return icFrontImageUrl;
    }

    public void setIcFrontImageUrl(String icFrontImageUrl) {
        this.icFrontImageUrl = icFrontImageUrl;
    }

    public String getIcBackImageUrl() {
        return icBackImageUrl;
    }

    public void setIcBackImageUrl(String icBackImageUrl) {
        this.icBackImageUrl = icBackImageUrl;
    }

    public String getHealthCertificateImageUrl() {
        return healthCertificateImageUrl;
    }

    public void setHealthCertificateImageUrl(String healthCertificateImageUrl) {
        this.healthCertificateImageUrl = healthCertificateImageUrl;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}

