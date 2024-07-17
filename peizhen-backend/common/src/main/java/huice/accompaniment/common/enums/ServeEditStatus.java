package huice.accompaniment.common.enums;

/**
 * @author Doge2077 2024/6/20
 */
public enum ServeEditStatus {
    DISABLE(0, "禁用"),
    ENABLE(1, "启用");

    private int status;
    private String description;

    ServeEditStatus(int status, String description) {
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

    public boolean equals(ServeEditStatus serveEditStatus) {
        return serveEditStatus != null && serveEditStatus.status == this.getStatus();
    }
}
