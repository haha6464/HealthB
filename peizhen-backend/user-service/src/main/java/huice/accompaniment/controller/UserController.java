package huice.accompaniment.controller;

import com.alibaba.fastjson.JSONArray;
import com.huice.health.common.anno.apiAuth.RoleApi;
import com.huice.health.common.anno.apiAuth.WhiteApi;
import com.huice.health.common.core.ResponseVo;
import huice.accompaniment.pojo.DTO.LoginDTO;
import huice.accompaniment.pojo.DTO.PatientDTO;
import huice.accompaniment.service.UserService;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/31 9:28
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Resource
    UserService service;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Object login(@Valid LoginDTO loginDTO) {
        return JSONArray.toJSON(new ResponseVo<>("200", service.login(loginDTO), "ok"));
    }

    @WhiteApi
    @RoleApi()
    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public Object logout(HttpResponse response) {
        service.logout();
        response.setHeader(new BasicHeader("welsir", "123"));
        return JSONArray.toJSON(new ResponseVo<>("200", null, "ok"));
    }

    @WhiteApi
    @RequestMapping(method = RequestMethod.POST, value = "/patient")
    public Object addPatient(PatientDTO patientDTO) {
        return JSONArray.toJSON(new ResponseVo<>("200", service.addPatient(patientDTO), "ok"));
    }
}
