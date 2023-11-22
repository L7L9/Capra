package com.capra.file.controller;

import com.capra.api.annotation.InnerCall;
import com.capra.api.result.RemoteResult;
import com.capra.file.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件请求
 * @author lql
 * @date 2023/11/15
 */
@RestController
@RequestMapping("file")
public class FileController {
    @Resource(name = "ipfsService")
    private FileService fileService;

    @InnerCall
    @PostMapping
    public RemoteResult<String> upload(MultipartFile file) throws IOException {
        return RemoteResult.successWithDetail("文件上传成功", fileService.upload(file));
    }

    @InnerCall
    @GetMapping
    public RemoteResult<byte[]> download(String uri){
        return RemoteResult.successWithDetail("文件获取成功",fileService.download(uri));
    }

    @InnerCall
    @GetMapping("/path")
    public RemoteResult<String> getAccessPath(String uri){
        return RemoteResult.successWithDetail("获取文件访问路径成功", fileService.getAccessPath(uri));
    }

    @InnerCall
    @DeleteMapping
    public RemoteResult<Boolean> delete(String uri){
        return RemoteResult.successWithDetail("文件删除成功",fileService.delete(uri));
    }
}