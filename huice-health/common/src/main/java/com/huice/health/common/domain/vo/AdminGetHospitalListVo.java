package com.huice.health.common.domain.vo;

import com.huice.health.common.domain.HospitalLabel;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员获取医院list
 */
public class AdminGetHospitalListVo implements Serializable {
    private Long id;
    private String city;
    private String name;    //医院名字
    private String address;
    private String hospitalIntroduction;
    private List<HospitalLabel> label;
    private Integer status;
    private Long cityId;

    @Override
    public String toString() {
        return "AdminGetHospitalListVo{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", hospitalIntroduction='" + hospitalIntroduction + '\'' +
                ", label=" + label +
                ", status=" + status +
                ", cityId=" + cityId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHospitalIntroduction() {
        return hospitalIntroduction;
    }

    public void setHospitalIntroduction(String hospitalIntroduction) {
        this.hospitalIntroduction = hospitalIntroduction;
    }

    public List<HospitalLabel> getLabel() {
        return label;
    }

    public void setLabel(List<HospitalLabel> label) {
        this.label = label;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public AdminGetHospitalListVo() {
    }

    public AdminGetHospitalListVo(Long id, String city, String name, String address, String hospitalIntroduction, List<HospitalLabel> label, Integer status, Long cityId) {
        this.id = id;
        this.city = city;
        this.name = name;
        this.address = address;
        this.hospitalIntroduction = hospitalIntroduction;
        this.label = label;
        this.status = status;
        this.cityId = cityId;
    }
}
