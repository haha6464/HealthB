package com.huice.health.common.enums;

/**
 * @author Doge2077 2024/6/20
 */
public enum DelFlagEnum {
    NOT_DELETE(0, "未删除"),
    IS_DELETE(1, "已删除");

    private int status;
    private String description;

    DelFlagEnum(final int value, final String description) {
        this.status = value;
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

    public boolean equals(DelFlagEnum delFlagEnum) {
        return delFlagEnum != null && delFlagEnum.status == this.getStatus();
    }
}
