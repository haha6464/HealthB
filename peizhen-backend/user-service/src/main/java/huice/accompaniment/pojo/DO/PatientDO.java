package huice.accompaniment.pojo.DO;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import huice.accompaniment.common.constant.TimeConstant;
import huice.accompaniment.pojo.DTO.PatientDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/5 15:42
 */
@TableName("patient")
public class PatientDO {

    private String uid;
    private String name;
    private String phoneNumber;
    private String relative;
    private String location;
    private Boolean defaultPatient;
    private Boolean hasDelete;
    @TableField(updateStrategy = FieldStrategy.NEVER)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = TimeConstant.YMD_HMS, timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = TimeConstant.YMD_HMS, timezone = "GMT+8")
    private LocalDateTime updateTime;

    public static PatientDO wrapPatientDTO(PatientDTO patientDTO){
        PatientDO patientDO = new PatientDO();
        LocalDateTime now = LocalDateTime.now();
        patientDO.setUid(patientDTO.getUid());
        patientDO.setName(patientDTO.getName());
        patientDO.setPhoneNumber(patientDTO.getPhoneNumber());
        patientDO.setRelative(patientDTO.getRelative());
        patientDO.setLocation(patientDTO.getLocation());
        patientDO.setCreateTime(now);
        patientDO.setCreateTime(now);
        return patientDO;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getDefaultPatient() {
        return defaultPatient;
    }

    public void setDefaultPatient(Boolean defaultPatient) {
        this.defaultPatient = defaultPatient;
    }

    public Boolean getHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(Boolean hasDelete) {
        this.hasDelete = hasDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
