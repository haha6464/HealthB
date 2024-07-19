package com.huice.health.common.enums;

/**
 * @author Doge2077 2024/6/21
 */
public enum ServeSaleStatus {
    DISABLE(0, "下架"),
    ENABLE(1, "上架");

    private int status;
    private String description;

    ServeSaleStatus(int status, String description) {
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

    public boolean equals(ServeSaleStatus serveSaleStatus) {
        return serveSaleStatus != null && serveSaleStatus.status == this.getStatus();
    }
}
