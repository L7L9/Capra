package com.capra.account.utils;

import cn.hutool.core.codec.Base64;
import com.capra.core.exception.SystemException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * 数据前缀
     */
    private static final String DATA_PREFIX = "data:image/";

    /**
     * 编码类型 => base64
     */
    private static final String CODE_TYPE = ";base64";

    /**
     * 使用base64对图片数据编码
     *
     * @param file 图片文件
     * @return 返回编码后的字符串
     */
    public static String toBase64(MultipartFile file){
        if(Objects.isNull(file)){
            throw new SystemException("文件不存在");
        }
        StringBuilder stringBuilder = new StringBuilder(DATA_PREFIX);
        try {
             stringBuilder.append(getType(file)).append(CODE_TYPE).append(Base64.encode(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("处理图片文件失败");
        }
        return stringBuilder.toString();
    }

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
    private static String getType(MultipartFile file){
        return Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf(".") + 1);
    }
}
