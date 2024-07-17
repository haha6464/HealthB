package com.example.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/6 17:42
 */
public class FileUtils {

    private static final int DEFAULT_FILE_SIZE = 10;

    public static String generatorMD5(MultipartFile file){
        StringBuilder sb = new StringBuilder();
        //生成MD5实例
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            InputStream inputStream = file.getInputStream();
            int available = inputStream.available();
            byte[] bytes = new byte[available];
            md5.update(bytes);
            for (byte by : md5.digest()) {
                //将生成的字节MD5值转换成字符串
                sb.append(String.format("%02X", by));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String wrapParamsToString(String... args){
        String headStr =args[0].substring(1);
        StringBuilder sb = new StringBuilder(headStr);
        for(int i=1;i<args.length-1;i++)
            sb.append("\\").append(args[i]);
        sb.append(".").append(args[args.length-1]);
        return sb.toString().replace("\\","/");
    }

    public static boolean checkImageValid(Long b){
        return Math.toIntExact(b / (1024 * 1024))<=DEFAULT_FILE_SIZE;
    }
}
