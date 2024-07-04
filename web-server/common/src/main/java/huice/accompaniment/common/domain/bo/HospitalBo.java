package huice.accompaniment.common.domain.bo;

import huice.accompaniment.common.domain.Hospital;

import java.io.Serializable;
import java.util.List;

public class HospitalBo implements Serializable {
    private Hospital hospital;
    private List<String> labelList;

    @Override
    public String toString() {
        return "HospitalBo{" +
                "hospital=" + hospital +
                ", labelList=" + labelList +
                '}';
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public HospitalBo() {
    }

    public HospitalBo(Hospital hospital, List<String> labelList) {
        this.hospital = hospital;
        this.labelList = labelList;
    }
}
