package huice.accompaniment.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/5 16:22
 */
@Getter
@AllArgsConstructor
public enum DocumentTypeEnum {

    IDENTITY_CARD(1,"身份证"),
    PASSPORT(2,"护照"),
    HK_MACAU_RESIDENTS(3,"港澳居民证"),
    HK_MACAU_MAINLAND_TRAVEL_PERMIT(4,"港澳来往内地通行证"),
    TAIWAN_RESIDENT_CARD(5,"台湾居民身份证"),
    HOSPITAL_PATIENT_CARD(6,"医院/门诊就诊卡");
    private final Integer code;
    private final String msg;

}
