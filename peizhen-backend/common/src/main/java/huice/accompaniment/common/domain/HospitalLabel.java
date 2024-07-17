package huice.accompaniment.common.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (HospitalLabel)实体类
 *
 * @author Yida Yang
 * @since 2024-06-03
 */
public class HospitalLabel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long hospitalId;
    /**
     * 标签id
     */
    private String labelName;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
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

    @Override
    public String toString() {
        return "HospitalLabel{" +
                "id=" + id +
                ", hospitalId=" + hospitalId +
                ", labelName='" + labelName + '\'' +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                '}';
    }

    public HospitalLabel() {
    }

    public HospitalLabel(Long id, Long hospitalId, String labelName, Long createBy, Date createTime) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.labelName = labelName;
        this.createBy = createBy;
        this.createTime = createTime;
    }
}

