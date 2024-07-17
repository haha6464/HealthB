package huice.accompaniment.common.enums;

/**
 * @author Doge2077 2024/7/12
 */
public enum PatientEscortStatus {
    UNCHECK(0, "未审核"),
    CHECKED(1, "待审核"),
    UNVERIFIED(2, "未认证"),
    VERIFIED(3, "已认证"),
    INACTIVE(4, "停用"),
    ACTIVE(5, "启用");


    private int status;
    private String description;

    PatientEscortStatus(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public boolean equals(Integer status) {
        return this.status == status;
    }

    public boolean equals(PatientEscortStatus patientEscortStatus) {
        return patientEscortStatus != null && patientEscortStatus.status == this.getStatus();
    }
}