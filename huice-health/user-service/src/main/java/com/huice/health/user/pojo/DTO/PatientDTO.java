package com.huice.health.user.pojo.DTO;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 13:24
 */
public class PatientDTO {
    private String uid;
    private String name;
    private String phoneNumber;
    private String relative;
    private String location;
    private Boolean defaultPatient;

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
}
