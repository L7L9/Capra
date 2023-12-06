package com.capra.account.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * 图片工具类(仅适合小图片,如头像等等)
 *
 * @author lql
 * @date 2023/11/01
 */
public class ImgUtils {
    /**
     * 图片文件的后缀
     */
    private static final String[] FILE_SUFFIX = new String[]{"jpg","png","jpeg","pjg","pjeg","jfif"};

    /**
     * 检测是否为图片
     * @param file 文件
     * @return boolean 为图片则返回true
     */
    public static boolean checkType(MultipartFile file){
        if(!Objects.isNull(file.getOriginalFilename())){
            String suffix = getType(file);
            for(String s:FILE_SUFFIX){
                if(suffix.equals(s)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取图片文件后缀
     * @param file 文件
     * @return 返回后缀
     */
    public static String getType(MultipartFile file){
        return Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf("."));
    }
}
