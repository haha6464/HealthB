package huice.accompaniment.common.domain;

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
    private Long getId() {
        return this.id;
    }

    /**
     * 订单id
     */
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单所属用户
     */
    private Long getUserId() {
        return this.userId;
    }

    /**
     * 订单所属用户
     */
    private void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 订单所属陪诊师
     */
    private Long getEscortId() {
        return this.escortId;
    }

    /**
     * 订单所属陪诊师
     */
    private void setEscortId(Long escortId) {
        this.escortId = escortId;
    }

    /**
     * 订单所属团长
     */
    private Long getAdminId() {
        return this.adminId;
    }

    /**
     * 订单所属团长
     */
    private void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    /**
     * 服务类型id
     */
    private Long getServeTypeId() {
        return this.serveTypeId;
    }

    /**
     * 服务类型id
     */
    private void setServeTypeId(Long serveTypeId) {
        this.serveTypeId = serveTypeId;
    }

    /**
     * 服务类型名称
     */
    private String getServeTypeName() {
        return this.serveTypeName;
    }

    /**
     * 服务类型名称
     */
    private void setServeTypeName(String serveTypeName) {
        this.serveTypeName = serveTypeName;
    }

    /**
     * 服务项id
     */
    private Long getServeItemId() {
        return this.serveItemId;
    }

    /**
     * 服务项id
     */
    private void setServeItemId(Long serveItemId) {
        this.serveItemId = serveItemId;
    }

    /**
     * 服务项名称
     */
    private String getServeItemName() {
        return this.serveItemName;
    }

    /**
     * 服务项名称
     */
    private void setServeItemName(String serveItemName) {
        this.serveItemName = serveItemName;
    }

    /**
     * 服务项图片
     */
    private String getServeItemImg() {
        return this.serveItemImg;
    }

    /**
     * 服务项图片
     */
    private void setServeItemImg(String serveItemImg) {
        this.serveItemImg = serveItemImg;
    }

    /**
     * 服务id
     */
    private Long getServeId() {
        return this.serveId;
    }

    /**
     * 服务id
     */
    private void setServeId(Long serveId) {
        this.serveId = serveId;
    }

    /**
     * 订单状态，0：待支付，100：派单中，200：待服务，300：服务中，400：待评价，500：订单完成，600：已取消，700：已关闭
     */
    private Integer getOrdersStatus() {
        return this.ordersStatus;
    }

    /**
     * 订单状态，0：待支付，100：派单中，200：待服务，300：服务中，400：待评价，500：订单完成，600：已取消，700：已关闭
     */
    private void setOrdersStatus(Integer ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    /**
     * 支付状态，2：待支付，4：支付成功
     */
    private Integer getPayStatus() {
        return this.payStatus;
    }

    /**
     * 支付状态，2：待支付，4：支付成功
     */
    private void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 退款状态 1退款中 2退款成功 3退款失败
     */
    private Integer getRefundStatus() {
        return this.refundStatus;
    }

    /**
     * 退款状态 1退款中 2退款成功 3退款失败
     */
    private void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * 订单总金额
     */
    private BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    /**
     * 订单总金额
     */
    private void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 实际支付金额
     */
    private BigDecimal getRealPayAmount() {
        return this.realPayAmount;
    }

    /**
     * 实际支付金额
     */
    private void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    /**
     * 优惠金额
     */
    private BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }

    /**
     * 优惠金额
     */
    private void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * 城市id
     */
    private Long getCityId() {
        return this.cityId;
    }

    /**
     * 城市id
     */
    private void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * 医院id
     */
    private Long getHospitalId() {
        return this.hospitalId;
    }

    /**
     * 医院id
     */
    private void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * 联系人手机号
     */
    private String getContactsPhone() {
        return this.contactsPhone;
    }

    /**
     * 联系人手机号
     */
    private void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    /**
     * 联系人姓名
     */
    private String getContactsName() {
        return this.contactsName;
    }

    /**
     * 联系人姓名
     */
    private void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    /**
     * 服务开始时间
     */

    private Date getServeStartTime() {
        return this.serveStartTime;
    }

    /**
     * 服务开始时间
     */
    private void setServeStartTime(Date serveStartTime) {
        this.serveStartTime = serveStartTime;
    }

    /**
     * 经度
     */
    private Double getLon() {
        return this.lon;
    }

    /**
     * 经度
     */
    private void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * 纬度
     */
    private Double getLat() {
        return this.lat;
    }

    /**
     * 纬度
     */
    private void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * 支付时间
     */
    private Date getPayTime() {
        return this.payTime;
    }

    /**
     * 支付时间
     */
    private void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 评价时间
     */
    private Date getEvaluationTime() {
        return this.evaluationTime;
    }

    /**
     * 评价时间
     */
    private void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    /**
     * 支付服务交易单号
     */
    private Long getTradingOrderNo() {
        return this.tradingOrderNo;
    }

    /**
     * 支付服务交易单号
     */
    private void setTradingOrderNo(Long tradingOrderNo) {
        this.tradingOrderNo = tradingOrderNo;
    }

    /**
     * 第三方支付的交易号
     */
    private String getTransactionId() {
        return this.transactionId;
    }

    /**
     * 第三方支付的交易号
     */
    private void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 支付服务退款单号
     */
    private Long getRefundNo() {
        return this.refundNo;
    }

    /**
     * 支付服务退款单号
     */
    private void setRefundNo(Long refundNo) {
        this.refundNo = refundNo;
    }

    /**
     * 第三方支付的退款单号
     */
    private String getRefundId() {
        return this.refundId;
    }

    /**
     * 第三方支付的退款单号
     */
    private void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    /**
     * 支付渠道
     */
    private String getTradingChannel() {
        return this.tradingChannel;
    }

    /**
     * 支付渠道
     */
    private void setTradingChannel(String tradingChannel) {
        this.tradingChannel = tradingChannel;
    }

    /**
     *
     */
    private Date getCreateTime() {
        return this.createTime;
    }

    /**
     *
     */
    private void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     */
    private Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     *
     */
    private void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 逻辑删除
     */
    private Integer getDelFlag() {
        return this.delFlag;
    }

    /**
     * 逻辑删除
     */
    private void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
