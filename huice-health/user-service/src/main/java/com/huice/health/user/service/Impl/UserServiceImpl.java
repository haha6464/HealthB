package com.huice.health.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.huice.health.common.utils.JwtUtil;
import com.huice.health.common.utils.snowflake.Snowflake;
import com.huice.health.user.common.exception.AbstractAssert;
import com.huice.health.user.mapper.UserMapper;
import com.huice.health.user.pojo.DO.PatientDO;
import com.huice.health.user.pojo.DO.UserDO;
import com.huice.health.user.pojo.DTO.LoginDTO;
import com.huice.health.user.pojo.DTO.PatientDTO;
import com.huice.health.user.pojo.ResultCodeEnum;
import com.huice.health.user.service.UserService;
import com.huice.health.user.utils.TokenUtils;
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
