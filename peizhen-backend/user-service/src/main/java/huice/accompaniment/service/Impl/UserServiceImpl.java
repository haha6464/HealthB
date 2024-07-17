package huice.accompaniment.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import huice.accompaniment.common.exception.AbstractAssert;
import huice.accompaniment.common.utils.JwtUtil;
import huice.accompaniment.common.utils.snowflake.Snowflake;
import huice.accompaniment.mapper.UserMapper;
import huice.accompaniment.pojo.DO.PatientDO;
import huice.accompaniment.pojo.DO.UserDO;
import huice.accompaniment.pojo.DTO.LoginDTO;
import huice.accompaniment.pojo.DTO.PatientDTO;
import huice.accompaniment.pojo.ResultCodeEnum;
import huice.accompaniment.service.UserService;
import huice.accompaniment.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/3 10:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    Snowflake snowflake;

    @Override
    public Map<String, Object> login(LoginDTO loginDTO) {
        String pwd = loginDTO.getPassword();
        String username = loginDTO.getUsername();
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", pwd);
        UserDO userDO = userMapper.selectOne(queryWrapper);
        AbstractAssert.notNull(userDO, ResultCodeEnum.USER_INFO_ERROR);
        HashMap<String, Object> res = new HashMap<>();
        res.put("token", JwtUtil.createJWT(userDO.getUid()));

        return res;
    }

    @Override
    public void logout() {
        TokenUtils.logout();
    }

    @Override
    public Boolean addPatient(PatientDTO patient) {
        //todo:后续考虑事务管理
        Long id = snowflake.generate();
        UserDO userDO = userMapper.selectById(patient.getUid());
        AbstractAssert.notNull(userDO, ResultCodeEnum.GET_USER_INFO_ERROR);
        PatientDO patientDO = PatientDO.wrapPatientDTO(patient);
        if (patient.getDefaultPatient()) {
            return userMapper.updateUserDefaultPatient(patientDO.getUid()) == 1 && userMapper.insertOnePatient(patientDO) == 1;
        }
        return userMapper.insertOnePatient(patientDO) == 1;
    }
}
