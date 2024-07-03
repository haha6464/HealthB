package huice.accompaniment.common.domain.vo;

import java.io.Serializable;

public class GetCityListVo implements Serializable {
    private Integer value;
    private String label;

    @Override
    public String toString() {
        return "GetCityListVo{" +
                "value=" + value +
                ", label='" + label + '\'' +
                '}';
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public GetCityListVo() {
    }

    public GetCityListVo(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
