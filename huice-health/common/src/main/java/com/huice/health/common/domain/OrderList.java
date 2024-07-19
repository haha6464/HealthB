package com.huice.health.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 */
@TableName("order_list")
public class OrderList implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "[订单id]不能为空")
    private Long id;
    /**
     * 订单所属用户
     */
    @NotNull(message = "[订单所属用户]不能为空")
    private Long userId;
    /**
     * 订单所属陪诊师
     */
    @NotNull(message = "[订单所属陪诊师]不能为空")
    private Long escortId;
    /**
     * 订单所属团长
     */
    @NotNull(message = "[订单所属团长]不能为空")
    private Long adminId;
    /**
     * 服务类型id
     */
    private Long serveTypeId;
    /**
     * 服务类型名称
     */
    @Size(max = 50, message = "编码长度不能超过50")
    @Length(max = 50, message = "编码长度不能超过50")
    private String serveTypeName;
    /**
     * 服务项id
     */
    @NotNull(message = "[服务项id]不能为空")
    private Long serveItemId;
    /**
     * 服务项名称
     */
    @Size(max = 50, message = "编码长度不能超过50")
    @Length(max = 50, message = "编码长度不能超过50")
    private String serveItemName;
    /**
     * 服务项图片
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @Length(max = 255, message = "编码长度不能超过255")
    private String serveItemImg;
    /**
     * 服务id
     */
    @NotNull(message = "[服务id]不能为空")
    private Long serveId;
    /**
     * 订单状态，0：待支付，100：派单中，200：待服务，300：服务中，400：待评价，500：订单完成，600：已取消，700：已关闭
     */
    @NotNull(message = "[订单状态，0：待支付，100：派单中，200：待服务，300：服务中，400：待评价，500：订单完成，600：已取消，700：已关闭]不能为空")
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
    @NotNull(message = "[订单总金额]不能为空")
    private BigDecimal totalAmount;
    /**
     * 实际支付金额
     */
    @NotNull(message = "[实际支付金额]不能为空")
    private BigDecimal realPayAmount;
    /**
     * 优惠金额
     */
    @NotNull(message = "[优惠金额]不能为空")
    private BigDecimal discountAmount;
    /**
     * 城市id
     */
    @NotNull(message = "[城市id]不能为空")
    private Long cityId;
    /**
     * 医院id
     */
    @NotNull(message = "[医院id]不能为空")
    private Long hospitalId;
    /**
     * 联系人手机号
     */
    @NotBlank(message = "[联系人手机号]不能为空")
    @Size(max = 20, message = "编码长度不能超过20")
    @Length(max = 20, message = "编码长度不能超过20")
    private String contactsPhone;
    /**
     * 联系人姓名
     */
    @NotBlank(message = "[联系人姓名]不能为空")
    @Size(max = 255, message = "编码长度不能超过255")
    @Length(max = 255, message = "编码长度不能超过255")
    private String contactsName;
    /**
     * 服务开始时间
     */
    @NotNull(message = "[服务开始时间]不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /**
     * 评价时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date evaluationTime;
    /**
     * 支付服务交易单号
     */
    private Long tradingOrderNo;
    /**
     * 第三方支付的交易号
     */
    @Size(max = 50, message = "编码长度不能超过50")
    @Length(max = 50, message = "编码长度不能超过50")
    private String transactionId;
    /**
     * 支付服务退款单号
     */
    private Long refundNo;
    /**
     * 第三方支付的退款单号
     */
    @Size(max = 50, message = "编码长度不能超过50")
    @Length(max = 50, message = "编码长度不能超过50")
    private String refundId;
    /**
     * 支付渠道
     */
    @Size(max = 50, message = "编码长度不能超过50")
    @Length(max = 50, message = "编码长度不能超过50")
    private String tradingChannel;
    /**
     *
     */
    @NotNull(message = "[]不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     *
     */
    @NotNull(message = "[]不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 逻辑删除
     */
    private Integer delFlag;

    /**
     * 订单id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 订单id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单所属用户
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 订单所属用户
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 订单所属陪诊师
     */
    public Long getEscortId() {
        return this.escortId;
    }

    /**
     * 订单所属陪诊师
     */
    public void setEscortId(Long escortId) {
        this.escortId = escortId;
    }

    /**
     * 订单所属团长
     */
    public Long getAdminId() {
        return this.adminId;
    }

    /**
     * 订单所属团长
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    /**
     * 服务类型id
     */
    public Long getServeTypeId() {
        return this.serveTypeId;
    }

    /**
     * 服务类型id
     */
    public void setServeTypeId(Long serveTypeId) {
        this.serveTypeId = serveTypeId;
    }

    /**
     * 服务类型名称
     */
    public String getServeTypeName() {
        return this.serveTypeName;
    }

    /**
     * 服务类型名称
     */
    public void setServeTypeName(String serveTypeName) {
        this.serveTypeName = serveTypeName;
    }

    /**
     * 服务项id
     */
    public Long getServeItemId() {
        return this.serveItemId;
    }

    /**
     * 服务项id
     */
    public void setServeItemId(Long serveItemId) {
        this.serveItemId = serveItemId;
    }

    /**
     * 服务项名称
     */
    public String getServeItemName() {
        return this.serveItemName;
    }

    /**
     * 服务项名称
     */
    public void setServeItemName(String serveItemName) {
        this.serveItemName = serveItemName;
    }

    /**
     * 服务项图片
     */
    public String getServeItemImg() {
        return this.serveItemImg;
    }

    /**
     * 服务项图片
     */
    public void setServeItemImg(String serveItemImg) {
        this.serveItemImg = serveItemImg;
    }

    /**
     * 服务id
     */
    public Long getServeId() {
        return this.serveId;
    }

    /**
     * 服务id
     */
    public void setServeId(Long serveId) {
        this.serveId = serveId;
    }

    /**
     * 订单状态，0：待支付，100：派单中，200：待服务，300：服务中，400：待评价，500：订单完成，600：已取消，700：已关闭
     */
    public Integer getOrdersStatus() {
        return this.ordersStatus;
    }

    /**
     * 订单状态，0：待支付，100：派单中，200：待服务，300：服务中，400：待评价，500：订单完成，600：已取消，700：已关闭
     */
    public void setOrdersStatus(Integer ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    /**
     * 支付状态，2：待支付，4：支付成功
     */
    public Integer getPayStatus() {
        return this.payStatus;
    }

    /**
     * 支付状态，2：待支付，4：支付成功
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 退款状态 1退款中 2退款成功 3退款失败
     */
    public Integer getRefundStatus() {
        return this.refundStatus;
    }

    /**
     * 退款状态 1退款中 2退款成功 3退款失败
     */
    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * 订单总金额
     */
    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    /**
     * 订单总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 实际支付金额
     */
    public BigDecimal getRealPayAmount() {
        return this.realPayAmount;
    }

    /**
     * 实际支付金额
     */
    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    /**
     * 优惠金额
     */
    public BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }

    /**
     * 优惠金额
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * 城市id
     */
    public Long getCityId() {
        return this.cityId;
    }

    /**
     * 城市id
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * 医院id
     */
    public Long getHospitalId() {
        return this.hospitalId;
    }

    /**
     * 医院id
     */
    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * 联系人手机号
     */
    public String getContactsPhone() {
        return this.contactsPhone;
    }

    /**
     * 联系人手机号
     */
    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    /**
     * 联系人姓名
     */
    public String getContactsName() {
        return this.contactsName;
    }

    /**
     * 联系人姓名
     */
    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    /**
     * 服务开始时间
     */
    public Date getServeStartTime() {
        return this.serveStartTime;
    }

    /**
     * 服务开始时间
     */
    public void setServeStartTime(Date serveStartTime) {
        this.serveStartTime = serveStartTime;
    }

    /**
     * 经度
     */
    public Double getLon() {
        return this.lon;
    }

    /**
     * 经度
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * 纬度
     */
    public Double getLat() {
        return this.lat;
    }

    /**
     * 纬度
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * 支付时间
     */
    public Date getPayTime() {
        return this.payTime;
    }

    /**
     * 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 评价时间
     */
    public Date getEvaluationTime() {
        return this.evaluationTime;
    }

    /**
     * 评价时间
     */
    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    /**
     * 支付服务交易单号
     */
    public Long getTradingOrderNo() {
        return this.tradingOrderNo;
    }

    /**
     * 支付服务交易单号
     */
    public void setTradingOrderNo(Long tradingOrderNo) {
        this.tradingOrderNo = tradingOrderNo;
    }

    /**
     * 第三方支付的交易号
     */
    public String getTransactionId() {
        return this.transactionId;
    }

    /**
     * 第三方支付的交易号
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 支付服务退款单号
     */
    public Long getRefundNo() {
        return this.refundNo;
    }

    /**
     * 支付服务退款单号
     */
    public void setRefundNo(Long refundNo) {
        this.refundNo = refundNo;
    }

    /**
     * 第三方支付的退款单号
     */
    public String getRefundId() {
        return this.refundId;
    }

    /**
     * 第三方支付的退款单号
     */
    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    /**
     * 支付渠道
     */
    public String getTradingChannel() {
        return this.tradingChannel;
    }

    /**
     * 支付渠道
     */
    public void setTradingChannel(String tradingChannel) {
        this.tradingChannel = tradingChannel;
    }

    /**
     *
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 逻辑删除
     */
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**
     * 逻辑删除
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
