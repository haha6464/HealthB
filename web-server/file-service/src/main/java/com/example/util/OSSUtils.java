package com.example.util;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description Valid
 * @Author welsir
 * @Date 2024/6/6 15:10
 */
public class OSSUtils {

    private static final String ILLEGAL_CHARACTERS = "<>:\"|?*";
    public static String wrapPathValid(String path){
        if (path == null || path.trim().isEmpty()) {
            return null;
        }
        for (char c : ILLEGAL_CHARACTERS.toCharArray()) {
            if (path.indexOf(c) != -1) {
                return null;
            }
        }
        Path res = Paths.get(path);
        return res.toString();
    }

    public static String splitFileType(String name){
        int dotIndex = name.lastIndexOf(".");
        if (dotIndex >= 0 && dotIndex < name.length() - 1) {
            return name.substring(dotIndex + 1);
        }
        return null;
    }

    public static String checkIfAbsent(String[] buckets,String target){
        for (int i = 0; i < buckets.length; i++) {
            if(buckets[i].equals(target)){
                return target;
            }
        }
        return null;
    }

    public static String wrapParamsToUrl(String bucket,String endpoint,String path){
        StringBuilder sb = new StringBuilder("https://");
        sb.append(bucket).append(".").append(endpoint).append("/").append(path);
        return sb.toString();
    }

    public static int getBucketIndex(String[] buckets,String target){
        for (int i = 0; i < buckets.length; i++) {
            if(buckets[i].equals(target)){
                return i;
            }
        }
        return -1;
    }
}
