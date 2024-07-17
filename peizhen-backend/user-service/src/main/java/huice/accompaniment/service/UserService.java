package huice.accompaniment.service;

import huice.accompaniment.pojo.DTO.LoginDTO;
import huice.accompaniment.pojo.DTO.PatientDTO;

import java.util.Map;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/3 10:22
 */
public interface UserService {

    Map<String, Object> login(LoginDTO loginDTO);

    void logout();

    Boolean addPatient(PatientDTO patient);
}
