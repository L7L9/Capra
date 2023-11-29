package com.capra.api.domain.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lql
 * @date 2023/11/29
 */
@Data
public class MinioUploadRequest {
    public MultipartFile file;

    private String bucketName;
}
