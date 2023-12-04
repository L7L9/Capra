package com.capra.file.controller;

import com.capra.api.annotation.InnerCall;
import com.capra.api.domain.request.MinioOperateRequest;
import com.capra.api.domain.request.MinioUploadRequest;
import com.capra.api.result.RemoteResult;
import com.capra.file.service.MinioService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 文件请求
 * @author lql
 * @date 2023/11/15
 */
@RestController
@RequestMapping("file")
public class FileController {
    @Resource
    private MinioService fileService;

    @InnerCall
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RemoteResult<String> upload(MinioUploadRequest minioUploadRequest) {
        return RemoteResult.successWithDetail("文件上传成功", fileService.upload(minioUploadRequest));
    }

    @InnerCall
    @GetMapping
    public RemoteResult<byte[]> download(@RequestBody MinioOperateRequest minioOperateRequest){
        return RemoteResult.successWithDetail("文件获取成功",fileService.download(minioOperateRequest));
    }

    @InnerCall
    @GetMapping("/path")
    public RemoteResult<String> getAccessPath(@RequestBody MinioOperateRequest minioOperateRequest){
        return RemoteResult.successWithDetail("获取文件访问路径成功", fileService.getAccessPath(minioOperateRequest));
    }

    @InnerCall
    @DeleteMapping
    public RemoteResult<Boolean> delete(@RequestBody MinioOperateRequest minioOperateRequest){
        return RemoteResult.successWithDetail("文件删除成功",fileService.delete(minioOperateRequest));
    }
}