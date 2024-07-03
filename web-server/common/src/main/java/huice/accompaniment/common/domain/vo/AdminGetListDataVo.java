package huice.accompaniment.common.domain.vo;

import java.io.Serializable;

public class AdminGetListDataVo implements Serializable {
    private Long id;
    private String city;
    private String oderId;
    private Integer serviceType;
    private String amount;
    private String patientEscortName;
    private String income;
    private String phoneNumber;
    private Integer status;

    @Override
    public String toString() {
        return "AdminGetListDataVo{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", oderId='" + oderId + '\'' +
                ", serviceType=" + serviceType +
                ", amount='" + amount + '\'' +
                ", patientEscortName='" + patientEscortName + '\'' +
                ", income='" + income + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPatientEscortName() {
        return patientEscortName;
    }

    public void setPatientEscortName(String patientEscortName) {
        this.patientEscortName = patientEscortName;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AdminGetListDataVo() {
    }

    public AdminGetListDataVo(Long id, String city, String oderId, Integer serviceType, String amount, String patientEscortName, String income, String phoneNumber, Integer status) {
        this.id = id;
        this.city = city;
        this.oderId = oderId;
        this.serviceType = serviceType;
        this.amount = amount;
        this.patientEscortName = patientEscortName;
        this.income = income;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }
}
