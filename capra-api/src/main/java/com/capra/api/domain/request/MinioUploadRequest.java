package com.capra.api.domain.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lql
 * @date 2023/11/29
 */
@Data
@Accessors(chain = true)
public class MinioUploadRequest {
    /**
     * 文件内容
     */
    public MultipartFile file;

    /**
     * 自定义文件名
     */
    private String filename;

    /**
     * 文件桶名
     */
    private String bucketName;
}
