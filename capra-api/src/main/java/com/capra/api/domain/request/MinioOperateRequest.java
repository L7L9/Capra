package com.capra.api.domain.request;

import lombok.Data;

/**
 * minio 文件操作请求(预览、删除)
 *
 * @author lql
 * @date 2023/11/29
 */
@Data
public class MinioOperateRequest {
    /**
     * 文件标识
     */
    private String identification;

    /**
     * 文件桶名
     */
    private String bucketName;
}
