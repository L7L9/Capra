package com.capra.api.client;

import com.capra.api.domain.request.MinioOperateRequest;
import com.capra.api.domain.request.MinioUploadRequest;
import com.capra.api.result.RemoteResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 文件模块远程接口
 * @author lql
 * @date 2023/11/15
 */
@FeignClient("capra-file")
public interface FileClient {
    /**
     * 上传文件
     *
     * @param minioUploadRequest minio上传文件请求
     * @return 成功返回
     */
    @PostMapping(value = "/file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    RemoteResult<String> upload(@RequestPart MinioUploadRequest minioUploadRequest);

    /**
     * 获取文件
     *
     * @param minioOperateRequest 文件下载请求
     * @return 返回文件byte
     */
    @GetMapping("/file")
    RemoteResult<byte[]> download(@RequestBody MinioOperateRequest minioOperateRequest);

    /**
     * 获取文件访问路径
     *
     * @param minioOperateRequest 文件预览请求
     * @return 访问路径
     */
    @GetMapping("/file/path")
    RemoteResult<String> getAccessPath(@RequestBody MinioOperateRequest minioOperateRequest);

    /**
     * 删除文件
     *
     * @param minioOperateRequest 文件删除请求
     * @return 成功返回true
     */
    @DeleteMapping("/file")
    RemoteResult<Boolean> delete(@RequestBody MinioOperateRequest minioOperateRequest);
}
