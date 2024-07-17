package huice.accompaniment.common.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单(OrderList)实体类
 *
 * @author Yida Yang
 * @since 2024-06-14 14:51:34
 */
public class OrderList implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 城市id
     */
    private Long cityId;
    /**
     * 订单id
     */
    private String oderId;
    /**
     * 服务类型
     */
    private Integer serviceType;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 陪诊师
     */
    private Long patientescortId;
    /**
     * 陪诊师收益
     */
    private BigDecimal income;
    /**
     * 联系电话
     */
    private String phoneNumber;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 患者id
     */
    private Long patientId;
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

    private Long hospitalId;


    @Override
    public String toString() {
        return "OrderList{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", oderId='" + oderId + '\'' +
                ", serviceType=" + serviceType +
                ", amount=" + amount +
                ", patientescortId=" + patientescortId +
                ", income=" + income +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", patientId=" + patientId +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", hospitalId=" + hospitalId +
                '}';
    }

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

    public String getOderId() {
        return oderId;
    }

    public void setOderId(String oderId) {
        this.oderId = oderId;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getPatientescortId() {
        return patientescortId;
    }

    public void setPatientescortId(Long patientescortId) {
        this.patientescortId = patientescortId;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public OrderList() {
    }

    public OrderList(Long id, Long cityId, String oderId, Integer serviceType, BigDecimal amount, Long patientescortId, BigDecimal income, String phoneNumber, Date startTime, Date endTime, Long patientId, Long createBy, Date createTime, Long updateBy, Date updateTime, Integer status, Integer delFlag, Long hospitalId) {
        this.id = id;
        this.cityId = cityId;
        this.oderId = oderId;
        this.serviceType = serviceType;
        this.amount = amount;
        this.patientescortId = patientescortId;
        this.income = income;
        this.phoneNumber = phoneNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
        this.hospitalId = hospitalId;
    }
}

