package com.example.pojo.vo;

import javax.validation.constraints.NotBlank;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 14:30
 */
public class CommonLoadFile {

    @NotBlank(message = "文件id不能为空")
    private String fileId;
    @NotBlank(message = "bucket不能为空")
    private String bucket;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
