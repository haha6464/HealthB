package com.huice.health.common.domain.bo;

import java.io.Serializable;

public class AdminGetListDataBo implements Serializable {
    private String orderIdForVague;
    private Integer serviceType;     //服务类型
    private Integer cityId;     //城市id
    private String patientEscortNameForVague;//接单人 陪诊师的姓名 模糊查询使用
    private String patientEscortName; //接单人  精确查询
    private String phoneNumber;       //手机号
    private Integer page;
    private Integer size;
    private Long hospitalId;          //医院id

    @Override
    public String toString() {
        return "AdminGetListDataBo{" +
                "orderIdForVague='" + orderIdForVague + '\'' +
                ", serviceType=" + serviceType +
                ", cityId=" + cityId +
                ", patientEscortNameForVague='" + patientEscortNameForVague + '\'' +
                ", patientEscortName='" + patientEscortName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", hospitalId=" + hospitalId +
                '}';
    }

    public String getOrderIdForVague() {
        return orderIdForVague;
    }

    public void setOrderIdForVague(String orderIdForVague) {
        this.orderIdForVague = orderIdForVague;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getPatientEscortNameForVague() {
        return patientEscortNameForVague;
    }

    public void setPatientEscortNameForVague(String patientEscortNameForVague) {
        this.patientEscortNameForVague = patientEscortNameForVague;
    }

    public String getPatientEscortName() {
        return patientEscortName;
    }

    public void setPatientEscortName(String patientEscortName) {
        this.patientEscortName = patientEscortName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public AdminGetListDataBo() {
    }

    public AdminGetListDataBo(String orderIdForVague, Integer serviceType, Integer cityId, String patientEscortNameForVague, String patientEscortName, String phoneNumber, Integer page, Integer size, Long hospitalId) {
        this.orderIdForVague = orderIdForVague;
        this.serviceType = serviceType;
        this.cityId = cityId;
        this.patientEscortNameForVague = patientEscortNameForVague;
        this.patientEscortName = patientEscortName;
        this.phoneNumber = phoneNumber;
        this.page = page;
        this.size = size;
        this.hospitalId = hospitalId;
    }
}
