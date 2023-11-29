package com.capra.file.service;

import com.capra.api.domain.request.MinioOperateRequest;
import com.capra.api.domain.request.MinioUploadRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lql
 * @date 2023/11/29
 */

public interface MinioService {
    /**
     * 上传文件
     *
     * @param minioUploadRequest minio上传请求
     * @return 返回uri
     */
    String upload(MinioUploadRequest minioUploadRequest);

    /**
     * 获取文件访问路径
     *
     * @param minioOperateRequest 预览请求
     * @return 访问路径
     */
    String getAccessPath(MinioOperateRequest minioOperateRequest);

    /**
     * 下载文件
     *
     * @param minioOperateRequest 下载请求
     * @return 文件字节数组
     */
    byte[] download(MinioOperateRequest minioOperateRequest);

    /**
     * 删除
     *
     * @param minioOperateRequest 删除请求
     * @return 成功返回true
     */
    Boolean delete(MinioOperateRequest minioOperateRequest);
}
