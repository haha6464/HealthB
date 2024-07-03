package com.example.core.operator;

import com.example.pojo.vo.CommonFile;
import com.example.pojo.vo.CommonLoadFile;
import com.example.pojo.vo.FileUploadVO;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 14:20
 */
public interface FileOperator<K extends CommonFile,V extends CommonLoadFile> {

    FileUploadVO upload(K commonFile);

    String download(V downloadFile);

    Boolean removePicture(V file);
}
