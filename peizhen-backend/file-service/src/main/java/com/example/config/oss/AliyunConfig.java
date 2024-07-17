package com.example.config.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 14:34
 */
@Configuration
@Component
public class AliyunConfig {
    @Value("${aliyun.oss.endpoint}")
    private String[] endpoints;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucket}")
    private String[] buckets;

    @Value("${aliyun.oss.bucket-private}")
    private String[] bucketPrivate;

    public String[] getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(String[] endpoints) {
        this.endpoints = endpoints;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String[] getBuckets() {
        return buckets;
    }

    public void setBuckets(String[] buckets) {
        this.buckets = buckets;
    }

    public String[] getBucketPrivate() {
        return bucketPrivate;
    }

    public void setBucketPrivate(String[] bucketPrivate) {
        this.bucketPrivate = bucketPrivate;
    }
}
