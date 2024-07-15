package huice.accompaniment.common.enums;

/**
 * @author Doge2077 2024/7/15
 */
public enum GenderEnum {
    FEMALE(0, "女性"),
    MALE(1, "男性");

    private int status;
    private String description;

    GenderEnum(final int value, final String description) {
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

    public boolean equals(GenderEnum delFlagEnum) {
        return delFlagEnum != null && delFlagEnum.status == this.getStatus();
    }
}
