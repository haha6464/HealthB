package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.core.operator.AbstractOperator;
import com.example.pojo.vo.CommonLoadFile;
import com.example.pojo.vo.FileUploadVO;
import com.example.pojo.vo.OSSFile;
import huice.accompaniment.common.anno.apiAuth.WhiteApi;
import huice.accompaniment.common.core.ResponseVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 17:20
 */
@RestController
@RequestMapping("/file/oss")
public class OSSFileOperatorController{

    @Resource
    AbstractOperator operator;

    @WhiteApi
    @RequestMapping(method = RequestMethod.POST,value = "/picture")
    public Object upload(@Validated OSSFile ossFile){
        FileUploadVO upload = operator.upload(ossFile);
        return JSONArray.toJSON(new ResponseVo<>("200",upload,"ok"));
    }

    @WhiteApi
    @RequestMapping(method = RequestMethod.POST,value = "/download")
    public Object download(@Validated CommonLoadFile downloadFile){
        String url = operator.download(downloadFile);
        return JSONArray.toJSON(new ResponseVo<>("200",url,"ok"));
    }

    @WhiteApi
    @RequestMapping(method = RequestMethod.DELETE,value = "picture")
    public Object remove(@Validated CommonLoadFile commonLoadFile){
        return null;
    }
}
