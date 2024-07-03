package com.example.core.operator;

import com.example.pojo.vo.CommonLoadFile;
import com.example.pojo.vo.FileUploadVO;
import com.example.pojo.vo.OSSFile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 14:33
 */
@Component
public class OSSFileOperator extends AbstractOperator{

    @Resource(name = "${oss.handler}")
    AbstractOSSFileOperator operator;

    @Override
    public FileUploadVO upload(OSSFile ossFile){
        return operator.upload(ossFile);
    }

    @Override
    public String download(CommonLoadFile downloadFile){
        return operator.download(downloadFile);
    }

    @Override
    CommonLoadFile remove(CommonLoadFile file) {
        return null;
    }
}
