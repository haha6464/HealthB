package huice.accompaniment.common.domain.vo;

import huice.accompaniment.common.domain.Hospital;
import huice.accompaniment.common.domain.Patientescortlist;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Doge2077 2024/7/12
 */
public class AdminGetPatientEscortListVo {


    // 陪诊师信息
    private Patientescortlist patientescortlist;
    // 医院信息
    private Hospital hospital;

    // 注册时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Patientescortlist getPatientescortlist() {
        return patientescortlist;
    }

    public void setPatientescortlist(Patientescortlist patientescortlist) {
        this.patientescortlist = patientescortlist;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
