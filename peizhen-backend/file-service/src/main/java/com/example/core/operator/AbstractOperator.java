package com.example.core.operator;

import com.example.pojo.vo.CommonLoadFile;
import com.example.pojo.vo.FileUploadVO;
import com.example.pojo.vo.OSSFile;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 17:13
 */
public abstract class AbstractOperator {

    public abstract FileUploadVO upload(OSSFile ossFile);

    public abstract String download(CommonLoadFile downloadFile);

    abstract CommonLoadFile remove(CommonLoadFile file);
}
