package com.example.core.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.example.config.oss.AliyunConfig;
import com.example.core.operator.AbstractOSSFileOperator;
import com.example.mapper.FileMapper;
import com.example.pojo.DO.SingleFile;
import com.example.pojo.vo.CommonLoadFile;
import com.example.pojo.vo.FileUploadVO;
import com.example.pojo.vo.OSSFile;
import com.example.util.FileUtils;
import com.example.util.OSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 14:47
 */
@Component("aliyun")
public class AliyunOperator extends AbstractOSSFileOperator {
    @Resource
    FileMapper fileMapper;

    private final String[] endpoints;
    private final String[] buckets;
    private final String accessKeyId;
    private final String accessKeySecret;
    @Autowired
    public AliyunOperator(AliyunConfig aliyunConfig) {
        this.endpoints=aliyunConfig.getEndpoints();
        this.buckets=aliyunConfig.getBuckets();
        this.accessKeyId=aliyunConfig.getAccessKeyId();
        this.accessKeySecret=aliyunConfig.getAccessKeySecret();
    }

    @Override
    public FileUploadVO upload(OSSFile commonFile) {
        String type = OSSUtils.splitFileType(Objects.requireNonNull(commonFile.getFile().getOriginalFilename()));
        String prefix = OSSUtils.wrapPathValid(commonFile.getPath());
        prefix = prefix==null?"":prefix;
        String md5 = FileUtils.generatorMD5(commonFile.getFile());
        String uploadName = FileUtils.wrapParamsToString(prefix,md5,type);
        int idx = OSSUtils.getBucketIndex(buckets,commonFile.getBucket());
        Assert.isTrue(idx!=-1,"Bucket有误");
        String curBucket = buckets[idx];
        String curEndpoint = endpoints[idx];
        OSSClient ossClient = null;
        FileUploadVO res = new FileUploadVO();
        SingleFile file = new SingleFile();
        file.setBucket(curBucket);
        file.setMd5(md5);
        file.setPath(uploadName);
        if(!FileUtils.checkImageValid(commonFile.getFile().getSize())){
            //todo:自定义异常 文件大小超出上限
            throw new RuntimeException("");
        }
        try {
            ossClient = new OSSClient(curEndpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(curBucket,uploadName, commonFile.getFile().getInputStream());
            fileMapper.insert(file);
            res.setFileId(file.getId());
            res.setUrl(OSSUtils.wrapParamsToUrl(curBucket,curEndpoint,uploadName));
            return  res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(ossClient!=null)
                ossClient.shutdown();
        }
    }

    @Override
    public String download(CommonLoadFile downloadFile) {
        int idx = OSSUtils.getBucketIndex(buckets, downloadFile.getBucket());
        Assert.isTrue(idx!=-1,"bucket不正确");
        SingleFile file = fileMapper.selectById(downloadFile.getFileId());
        Assert.notNull(file,"文件不存在");
        return OSSUtils.wrapParamsToUrl(downloadFile.getBucket(),endpoints[idx],file.getPath());
    }

    @Override
    public Boolean removePicture(CommonLoadFile file) {
        return null;
    }
}
