package com.example.pojo.vo;

import javax.validation.constraints.NotBlank;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 14:51
 */
public class OSSFile extends CommonFile{
    @NotBlank(message = "bucket不能为空")
    private String bucket;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
