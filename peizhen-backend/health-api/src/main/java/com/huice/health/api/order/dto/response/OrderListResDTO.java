package com.huice.health.api.order.dto.response;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 */
public class OrderListResDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 订单id
     */
    private Long id;
    /**
     * 订单所属用户
     */
    private Long userId;
    /**
     * 订单所属陪诊师
     */
    private Long escortId;
    /**
     * 订单所属团长
     */
    private Long adminId;
    /**
     * 服务类型id
     */
    private Long serveTypeId;
    /**
     * 服务类型名称
     */
    private String serveTypeName;
    /**
     * 服务项id
     */
    private Long serveItemId;
    /**
     * 服务项名称
     */
    private String serveItemName;
    /**
     * 服务项图片
     */
    private String serveItemImg;
    /**
     * 服务id
     */
    private Long serveId;
    /**
     * 订单状态，0：待支付，100：派单中，200：待服务，300：服务中，400：待评价，500：订单完成，600：已取消，700：已关闭
     */
    private Integer ordersStatus;
    /**
     * 支付状态，2：待支付，4：支付成功
     */
    private Integer payStatus;
    /**
     * 退款状态 1退款中 2退款成功 3退款失败
     */
    private Integer refundStatus;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 实际支付金额
     */
    private BigDecimal realPayAmount;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 城市id
     */
    private Long cityId;
    /**
     * 医院id
     */
    private Long hospitalId;
    /**
     * 联系人手机号
     */
    private String contactsPhone;
    /**
     * 联系人姓名
     */
    private String contactsName;
    /**
     * 服务开始时间
     */
    private Date serveStartTime;
    /**
     * 经度
     */
    private Double lon;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 评价时间
     */
    private Date evaluationTime;
    /**
     * 支付服务交易单号
     */
    private Long tradingOrderNo;
    /**
     * 第三方支付的交易号
     */
    private String transactionId;
    /**
     * 支付服务退款单号
     */
    private Long refundNo;
    /**
     * 第三方支付的退款单号
     */
    private String refundId;
    /**
     * 支付渠道
     */
    private String tradingChannel;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEscortId() {
        return escortId;
    }

    public void setEscortId(Long escortId) {
        this.escortId = escortId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getServeTypeId() {
        return serveTypeId;
    }

    public void setServeTypeId(Long serveTypeId) {
        this.serveTypeId = serveTypeId;
    }

    public String getServeTypeName() {
        return serveTypeName;
    }

    public void setServeTypeName(String serveTypeName) {
        this.serveTypeName = serveTypeName;
    }

    public Long getServeItemId() {
        return serveItemId;
    }

    public void setServeItemId(Long serveItemId) {
        this.serveItemId = serveItemId;
    }

    public String getServeItemName() {
        return serveItemName;
    }

    public void setServeItemName(String serveItemName) {
        this.serveItemName = serveItemName;
    }

    public String getServeItemImg() {
        return serveItemImg;
    }

    public void setServeItemImg(String serveItemImg) {
        this.serveItemImg = serveItemImg;
    }

    public Long getServeId() {
        return serveId;
    }

    public void setServeId(Long serveId) {
        this.serveId = serveId;
    }

    public Integer getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(Integer ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public Date getServeStartTime() {
        return serveStartTime;
    }

    public void setServeStartTime(Date serveStartTime) {
        this.serveStartTime = serveStartTime;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public Long getTradingOrderNo() {
        return tradingOrderNo;
    }

    public void setTradingOrderNo(Long tradingOrderNo) {
        this.tradingOrderNo = tradingOrderNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(Long refundNo) {
        this.refundNo = refundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getTradingChannel() {
        return tradingChannel;
    }

    public void setTradingChannel(String tradingChannel) {
        this.tradingChannel = tradingChannel;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
