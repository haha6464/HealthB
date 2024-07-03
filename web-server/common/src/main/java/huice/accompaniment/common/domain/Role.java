package huice.accompaniment.common.domain;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/13 12:32
 */
public enum Role {

    USER("user",0),
    ADMIN("admin",1);

    private String role;
    private Integer code;

    Role(String role, int code) {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
