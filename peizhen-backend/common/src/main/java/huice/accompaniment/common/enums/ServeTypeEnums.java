package huice.accompaniment.common.enums;

/**
 * @author Doge2077 2024/7/18
 */
public enum ServeTypeEnums {
    ONE_TO_ONE(0, "VIP服务"),
    ONE_TO_MORE(1, "拼团服务");

    private int status;
    private String description;

    ServeTypeEnums(int status, String description) {
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

    public boolean equals(ServeTypeEnums serveSaleStatus) {
        return serveSaleStatus != null && serveSaleStatus.status == this.getStatus();
    }
}
