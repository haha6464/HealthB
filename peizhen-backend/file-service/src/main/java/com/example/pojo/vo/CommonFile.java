package com.example.pojo.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 14:28
 */
public class CommonFile {

    private String path;
    private MultipartFile file;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
